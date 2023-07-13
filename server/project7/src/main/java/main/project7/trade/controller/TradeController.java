package main.project7.trade.controller;



import main.project7.trade.mapper.TradeMapper;
import main.project7.trade.service.TradeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("trades")
@Validated
public class TradeController {
    private final static String TRADES_URL = "trades";
    private TradeService tradeService;
    private TradeMapper mapper;

    public TradeController(TradeService tradeService, TradeMapper mapper) {
        this.tradeService = tradeService;
        this.mapper = mapper;
    }

//    @PostMapping
//    public ResponseEntity postTrade(@Valid @RequestBody TradeDto.Post requestBody) {
//        Trade trade = mapper.tradePostDtoToTrade(requestBody);
//        Trade createTrade = tradeService.createTrade(trade);
//        URI location = UriCreator.createUri(TRADES_URL, createTrade.getTradeId());
//        return new ResponseEntity<>(TradeDto.Response.response(trade), HttpStatus.CREATED);
//    }
//
//    @PatchMapping("/{tradeId}")
//    public ResponseEntity putTrade(@PathVariable("tradeId") @Positive Long tradeId,
//                                   @Valid @RequestBody TradeDto.Put requestBody) {
//        Trade trade = tradeService.updateTrade(mapper.tradePutDtoToTrade(requestBody.addTradeId(tradeId)));
//        return new ResponseEntity<>(new ResponseDto.SingleResponseDto<>(mapper.tradeToResponseDto(trade)),
//                HttpStatus.OK);
//    }
//
//    @GetMapping("/{tradeId}")
//    public ResponseEntity getTrade(@PathVariable("tradeId") @Positive Long tradeId) {
//        Trade trade = tradeService.findTrade(tradeId);
//        return new ResponseEntity<>(new ResponseDto.SingleResponseDto<>(mapper.tradeToResponseDto(trade)),
//                HttpStatus.OK);
//    }
//
//
//    @GetMapping("/trades")
//    public ResponseEntity<List<Trade>> getTrades(
//            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//
//        List<Trade> trades = tradeService.findTrades(startDate, endDate);
//
//        if (trades.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(trades, HttpStatus.OK);
//        }
//    }
//
//    @DeleteMapping("/{tradeId}")
//    public ResponseEntity deleteTag(@PathVariable("tradeId") @Positive long tradeId) {
//        tradeService.deleteTrade(tradeId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
