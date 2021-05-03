package com.nnk.springboot.service;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
