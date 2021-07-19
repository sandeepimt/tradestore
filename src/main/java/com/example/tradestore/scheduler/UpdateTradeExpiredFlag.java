package com.example.tradestore.scheduler;

import com.example.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UpdateTradeExpiredFlag {

    private static final Logger log = LoggerFactory.getLogger(UpdateTradeExpiredFlag.class);
    @Autowired
    TradeService tradeService;

    @Scheduled(fixedDelay = 300000)
    public void updateTradeExpiryFlag() {
        log.info("Scheduled job for updating expiry flag is running");
        tradeService.updateExpiryFlagOfTrade();
        log.info("Scheduled job for updating expiry flag is completed");
    }
}

