package com.example.tradestore.controller;

import com.example.tradestore.model.TradeEntity;
import com.example.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    TradeService tradeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTrade(@RequestBody TradeEntity trade) {
        tradeService.saveTrade(trade);
        return new ResponseEntity("Trade Saved Successfully", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<TradeEntity> findAllTrades() {
        return tradeService.findAll();
    }

}

