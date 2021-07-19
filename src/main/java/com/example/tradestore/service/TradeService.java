package com.example.tradestore.service;

import com.example.tradestore.customexception.BusinessException;
import com.example.tradestore.model.TradeEntity;
import com.example.tradestore.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeRepository tradeRepository;

    private boolean isValidTrade(TradeEntity tradeEntity) {
        if (validateMaturityDate(tradeEntity)) {
            Optional<TradeEntity> existingTrade = tradeRepository.findById(tradeEntity.getTradeId());
            if (existingTrade.isPresent()) {
                return validateVersion(tradeEntity, existingTrade.get());
            } else {
                return true;
            }
        } else {
            log.info("Trade with tradeID::" + tradeEntity.getTradeId() + " has outdated Maturity Date");
            throw new BusinessException("Trade with tradeID::" + tradeEntity.getTradeId() + " has outdated Maturity Date");
        }
    }

    private boolean validateVersion(TradeEntity tradeEntity, TradeEntity oldTradeEntity) {
        if (tradeEntity.getVersion() >= oldTradeEntity.getVersion()) {
            return true;
        } else {
            log.info("Trade with tradeID::" + tradeEntity.getTradeId() + " already exist with lover version");
            throw new BusinessException("Trade with tradeID::" + tradeEntity.getTradeId() + " already exist with lover version");
        }

    }

    private boolean validateMaturityDate(TradeEntity tradeEntity) {
        return tradeEntity.getMaturityDate().isBefore(LocalDate.now()) ? false : true;
    }

    public void saveTrade(TradeEntity tradeEntity) {
        if (isValidTrade(tradeEntity)) {
            tradeEntity.setCreatedDate(LocalDate.now());
            tradeRepository.save(tradeEntity);
            log.info("Trade with tradeID::" + tradeEntity.getTradeId() + " Saved Successfully");
        }
    }

    public List<TradeEntity> findAll() {
        List<TradeEntity> trades = tradeRepository.findAll();
        if(trades.isEmpty()) {
            throw new BusinessException("Currently there is no trade to show.");
        }
        else {
            return tradeRepository.findAll();
        }
    }

    public void updateExpiryFlagOfTrade() {
        tradeRepository.findAll().stream().forEach(t -> {
            if (!validateMaturityDate(t)) {
                t.setExpired(Boolean.TRUE);
                tradeRepository.save(t);
                log.info("Expired flag is now set for tradeID::" + t.getTradeId());

            }
        });
    }


}

