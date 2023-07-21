package com.server.wishlist.controller;

import com.server.dto.ResponseDto;
import com.server.utils.UriCreator;
import com.server.wishlist.dto.WishlistDto;
import com.server.wishlist.entity.Wishlist;
import com.server.wishlist.mapper.WishlistMapper;
import com.server.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlists")
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class WishlistController {

    private final static String WISHLIST_DEFAULT_URL = "/wishlists";
    private final WishlistService wishlistService;
    private final WishlistMapper wishlistMapper;

    @PostMapping("/{memberId}")
    public ResponseEntity postWishlist(@PathVariable("memberId") @Positive Long memberId,
                                       @Valid @RequestBody WishlistDto.Post requestBody) {
        if (requestBody == null) {
            throw new IllegalArgumentException("Request body cannot be null.");
        }

        requestBody.setMemberId(memberId); // memberId 설정
        Wishlist wishlist = wishlistMapper.wishlistPostDtoToWishlist(requestBody);
        Wishlist createWishlist = wishlistService.createWishlist(wishlist);
        URI location = UriCreator.createUri(WISHLIST_DEFAULT_URL, createWishlist.getWishlistId());

        return new ResponseEntity<>(WishlistDto.Response.response(createWishlist), HttpStatus.CREATED);
    }

    @PatchMapping("/{wishlist-id}/{memberId}")
    public ResponseEntity putWishlist(@PathVariable("wishlist-id") @Positive Long wishlistId,
                                      @PathVariable("memberId") @Positive Long memberId,
                                      @Valid @RequestBody WishlistDto.Patch requestBody) {
        Wishlist wishlist =
                wishlistService.updateWishlist(wishlistMapper.wishlistPutDtoToWishlist(requestBody.addwishlistId(wishlistId)), memberId);

        return new ResponseEntity<>(
                new ResponseDto.SingleResponseDto<>(wishlistMapper.wishlistToWishlistResponseDto(wishlist)),
                HttpStatus.OK);
    }

    @GetMapping("/{wishlist-id}/{memberId}")
    public ResponseEntity getMember(@PathVariable("wishlist-id") @Positive Long wishlistId,
                                    @PathVariable("memberId") @Positive Long memberId) {
        Wishlist wishlist = wishlistService.findWishlist(wishlistId);
        return new ResponseEntity<>(
                new ResponseDto.SingleResponseDto<>(wishlistMapper.wishlistToWishlistResponseDto(wishlist))
                , HttpStatus.OK);
    }
    @GetMapping("/{memberId}")
    public ResponseEntity<List<WishlistDto.Response>> getWishlists(@RequestParam String tab,
                                                                   @PathVariable("memberId") @Positive Long memberId) {
        List<Wishlist> wishlists;
        if (tab.equals("latest")) {
            wishlists = wishlistService.findWishlistsByLatest(memberId);
        } else if (tab.equals("lowPrice")) {
            wishlists = wishlistService.findWishlistsByLowPrice(memberId);
        } else {
            throw new IllegalArgumentException("Invalid tab parameter");
        }

        List<WishlistDto.Response> filteredWishlists = wishlists.stream()
                .map(wishlistMapper::wishlistToWishlistResponseDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(filteredWishlists, HttpStatus.OK);
    }

    @DeleteMapping("/{wishlistId}/{memberId}")
    public ResponseEntity deleteWishlist(@PathVariable("wishlistId") @Positive Long wishlistId,
                                         @PathVariable("memberId") @Positive Long memberId) {
        wishlistService.deleteWishlist(wishlistId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}