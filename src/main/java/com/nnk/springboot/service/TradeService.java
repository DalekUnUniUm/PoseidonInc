package com.nnk.springboot.service;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository ;

    public List<Trade> getTrades(){
        return tradeRepository.findAll();
    }

    public Trade saveTrade(Trade trade){
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade ;
    }

    public Optional<Trade> getTrade(Integer id){
        return tradeRepository.findById(id);
    }

    public void deleteTrade(Integer id){
        tradeRepository.deleteById(id);
    }

    public void saveTradeOrUpdate(Integer id, Trade trade){
        /**If id equal null, so it's a new Trade**/
        if(id == null){
            saveTrade(trade);
        }
        /**If id not null, so it's a Updated Trade**/
        else{
            trade.setAccount(trade.getAccount());
            trade.setType(trade.getType());
            trade.setBuyQuantity(trade.getBuyQuantity());
            trade.setTradeId(id);
            saveTrade(trade);
        }
    }

}
