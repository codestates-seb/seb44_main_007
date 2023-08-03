package com.server.wishlist.mapper;

import com.server.wishlist.dto.WishlistDto;
import com.server.wishlist.entity.Wishlist;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T13:57:12+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class WishlistMapperImpl implements WishlistMapper {

    @Override
    public Wishlist wishlistPostDtoToWishlist(WishlistDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Wishlist wishlist = new Wishlist();

        wishlist.setWishlistName( requestBody.getWishlistName() );
        wishlist.setPrice( requestBody.getPrice() );
        wishlist.setCategory( requestBody.getCategory() );
        wishlist.setPriority( requestBody.getPriority() );
        wishlist.setMemberId( requestBody.getMemberId() );
        wishlist.setAvailable( requestBody.getAvailable() );

        return wishlist;
    }

    @Override
    public Wishlist wishlistPutDtoToWishlist(WishlistDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Wishlist wishlist = new Wishlist();

        wishlist.setWishlistId( requestBody.getWishlistId() );
        wishlist.setWishlistName( requestBody.getWishlistName() );
        wishlist.setPrice( requestBody.getPrice() );
        wishlist.setCategory( requestBody.getCategory() );
        wishlist.setPriority( requestBody.getPriority() );
        wishlist.setAvailable( requestBody.getAvailable() );

        return wishlist;
    }

    @Override
    public WishlistDto.Response wishlistToWishlistResponseDto(Wishlist wishlist) {
        if ( wishlist == null ) {
            return null;
        }

        WishlistDto.Response.ResponseBuilder response = WishlistDto.Response.builder();

        response.wishlistId( wishlist.getWishlistId() );
        response.wishlistName( wishlist.getWishlistName() );
        response.price( wishlist.getPrice() );
        response.category( wishlist.getCategory() );
        response.priority( wishlist.getPriority() );
        response.available( wishlist.getAvailable() );

        return response.build();
    }

    @Override
    public List<WishlistDto.Response> wishlistsToWishlistResponseDtos(List<Wishlist> wishlists) {
        if ( wishlists == null ) {
            return null;
        }

        List<WishlistDto.Response> list = new ArrayList<WishlistDto.Response>( wishlists.size() );
        for ( Wishlist wishlist : wishlists ) {
            list.add( wishlistToWishlistResponseDto( wishlist ) );
        }

        return list;
    }
}
