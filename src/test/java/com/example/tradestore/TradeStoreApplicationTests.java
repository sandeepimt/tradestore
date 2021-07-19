package com.example.tradestore;

import com.example.tradestore.controller.TradeController;
import com.example.tradestore.customexception.BusinessException;
import com.example.tradestore.model.TradeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TradeStoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TradeController tradeController;

	@Test
	void testSaveTrade_successful() {
	ResponseEntity responseEntity = tradeController.saveTrade(createTrade("T1", "B1",1,
			"CP-1", LocalDate.now(),true));
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}

	@Test
	void testSaveTradeWhenMaturityDateIsBeforeTodayDate() {
		try {
			LocalDate localDate = LocalDate.of(2015, 05, 21);
			ResponseEntity responseEntity = tradeController.saveTrade(createTrade("T2", "B1", 1, "CP-1", localDate, true));
		}
		catch(BusinessException be) {
			Assertions.assertEquals("Trade with tradeID::T2 has outdated Maturity Date", be.getErrorMessage());
		}
	}

	@Test
	void testSaveTradeWhenOldVersionReceived() {
		ResponseEntity responseEntity = tradeController.saveTrade(createTrade("T3", "B1",2,
				"CP-1", LocalDate.now(),true));
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

		try {
			ResponseEntity responseEntity1 = tradeController.saveTrade(createTrade("T3", "B1",1,
					"CP-1", LocalDate.now(),true));


		}catch (BusinessException be){
			Assertions.assertEquals("Trade with tradeID::T3 already exist with lover version", be.getErrorMessage());
		}

	}

	@Test
	void testSaveTradeWhenSameVersionReceived(){
		ResponseEntity responseEntity = tradeController.saveTrade(createTrade("T4", "B1",2,
				"CP-1", LocalDate.now(),true));
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());


		ResponseEntity responseEntity1 = tradeController.saveTrade(createTrade("T4", "B1New",2,
				"CP-1", LocalDate.now(),true));
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

	}

	private TradeEntity createTrade(String id, String bookId, int version, String partyId, LocalDate maturityDate, Boolean expired){
		TradeEntity trade = new TradeEntity();
		trade.setTradeId(id);
		trade.setBookId(bookId);
		trade.setVersion(version);
		trade.setCounterPartyId(partyId);
		trade.setMaturityDate(maturityDate);
		trade.setExpired(expired);
		return trade;
	}


}
