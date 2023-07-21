package com.server.total.controller;

import com.server.dto.ResponseDto;
import com.server.total.entity.Total;
import com.server.total.mapper.TotalMapper;
import com.server.total.service.TotalService;
import com.server.total.dto.TotalDto;
import com.server.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("totals")
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class TotalController {

    private final static String TOTALS_URL = "totals";
    private final TotalService totalService;
    private final TotalMapper mapper;


    @PostMapping("/{memberId}")
    public ResponseEntity postTotal(@PathVariable("memberId") @Positive Long memberId,
                                    @Valid @RequestBody TotalDto.Post requestBody) {
        Total total = totalService.createTotal(mapper.totalPostDtoToTotal(requestBody), memberId);
        URI location = UriCreator.createUri(TOTALS_URL, total.getTotalId());
        return new ResponseEntity<>(TotalDto.Response.response(total), HttpStatus.CREATED);
    }

    @PatchMapping("/{totalId}/{memberId}")
    public ResponseEntity putTrade(@PathVariable("totalId") @Positive Long totalId,
                                   @PathVariable("memberId") @Positive Long memberId,
                                   @Valid @RequestBody TotalDto.Patch requestBody) {
        Total total = totalService.updateTotal(mapper.totalPatchDtoToTotal(requestBody.addTotalId(totalId)), memberId);
        return new ResponseEntity(new ResponseDto.SingleResponseDto<>(mapper.totalToResponseDto(total)),
                HttpStatus.OK);
    }

    @GetMapping("/{totalId}/{memberId}")
    public ResponseEntity getTotal(@PathVariable("totalId") @Positive long totalId,
                                   @PathVariable("memberId") @Positive Long memberId){
        Total total = totalService.findTotal(totalId, memberId);
        return new ResponseEntity<>(new ResponseDto.SingleResponseDto<>(mapper.totalToResponseDto(total)),
                HttpStatus.OK);
    }



}
