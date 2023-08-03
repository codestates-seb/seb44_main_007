package com.server.trade.controller;

import com.server.dto.ResponseDto;
import com.server.member.repository.MemberRepository;
import com.server.trade.dto.TradeDto;
import com.server.trade.entity.Trade;
import com.server.trade.mapper.TradeMapper;
import com.server.trade.service.TradeService;
import com.server.utils.UriCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trades")
@Validated
@CrossOrigin
public class TradeController {
    private final static String TRADES_URL = "trades";
    private TradeService tradeService;
    private MemberRepository memberRepository;
    private TradeMapper mapper;

    public TradeController(TradeService tradeService, TradeMapper mapper) {
        this.tradeService = tradeService;
        this.mapper = mapper;
    }

    @PostMapping("/{memberId}")
    public ResponseEntity postTrade(@PathVariable("memberId") @Positive Long memberId,
                                    @Valid @RequestBody TradeDto.Post requestBody) {

        if (requestBody == null) {
            throw new IllegalArgumentException("Request body cannot be null.");
        }

        requestBody.setMemberId(memberId); // memberId 설정
        Trade trade = mapper.tradePostDtoToTrade(requestBody);
        Trade createTrade = tradeService.createTrade(trade);
        URI location = UriCreator.createUri(TRADES_URL, createTrade.getTradeId());
        return new ResponseEntity<>(TradeDto.Response.response(createTrade), HttpStatus.CREATED);
    }

    @PatchMapping("/{tradeId}/{memberId}")
    public ResponseEntity patchTrade(@PathVariable("tradeId") @Positive Long tradeId,
                                     @PathVariable("memberId") @Positive Long memberId,
                                     @Valid @RequestBody TradeDto.Patch requestBody) {

        Trade trade = tradeService.updateTrade(mapper.tradePatchDtoToTrade(requestBody.addTradeId(tradeId)), memberId);

        return new ResponseEntity<>(new ResponseDto.SingleResponseDto<>(
                mapper.tradeToResponseDto(trade)), HttpStatus.OK);
    }

    @GetMapping("/{tradeId}/{memberId}")
    public ResponseEntity getTrade(@PathVariable("tradeId") @Positive Long tradeId,
                                   @PathVariable("memberId") @Positive Long memberId) {
        Trade trade = tradeService.findTrade(tradeId, memberId);
        return new ResponseEntity<>(new ResponseDto.SingleResponseDto<>(mapper.tradeToResponseDto(trade)),
                HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity getTrades(@PathVariable("memberId") @Positive Long memberId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Trade> trades = tradeService.findTrades(startDate, endDate, memberId);

        if (trades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(trades, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{tradeId}/{memberId}")
    public ResponseEntity deleteTag(@PathVariable("tradeId") @Positive Long tradeId,
                                    @PathVariable("memberId") @Positive Long memberId) {
        tradeService.deleteTrade(tradeId, memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
