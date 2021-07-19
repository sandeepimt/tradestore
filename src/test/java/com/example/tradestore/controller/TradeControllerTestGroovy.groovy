//package com.example.tradestore.controller
//
//import com.example.tradestore.model.TradeEntity
//import com.example.tradestore.repository.TradeRepository
//import com.example.tradestore.service.TradeService
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc
//
//import java.time.LocalDate
//
//import static org.springframework.http.RequestEntity.post
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
//
//class TradeControllerTestGroovy extends spock.lang.Specification {
//
//    def tradeController = new TradeController()
//    def tradeService = Mock(TradeService)
//
//    MockMvc mvc = standaloneSetup(tradeController).build()
//
//    def "SaveTrade"() {
//        when: 'When POST request received for trade'
//        def response = mvc.perform(post('/trade/save').contentType(MediaType.APPLICATION_JSON).body(getTradeObj("T1", "B1",1,
//                "CP-1", LocalDate.now(),true))).andReturn().response
//
//
//        then: 'tradeService correctly returned account in JSON'
//        1 * tradeService.saveTrade(_)
//
//        //Testing the HTTP Status code
//        response.status == OK.value()
//
//
//    }
//
//    def "FindAllTrades"() {
//    }
//
//    def getTradeObj(String id, String bookId, int version, String partyId, LocalDate maturityDate, boolean expired)
//    {
//           TradeEntity trade = new TradeEntity();
//            trade.setTradeId(id);
//            trade.setBookId(bookId);
//            trade.setVersion(version);
//            trade.setCounterPartyId(partyId);
//            trade.setMaturityDate(maturityDate);
//            trade.setExpired(expired);
//            return trade;
//        }
//
//    }
//
//
//
