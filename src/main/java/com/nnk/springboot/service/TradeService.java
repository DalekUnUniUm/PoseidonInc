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

    public String saveTradeOrUpdate(Integer id, Trade trade, BindingResult result, Model model){

        if(id == null){
            if(!result.hasErrors()){
                Logger.debug("Trade saved with success");
                saveTrade(trade);
                model.addAttribute("trades",getTrades());
                return "redirect:/trade/list" ;
            }
        }
        else{
            if(result.hasErrors()){
                Logger.warn("Trade update failed");
                return "trade/update" ;
            }

            trade.setAccount(trade.getAccount());
            trade.setType(trade.getType());
            trade.setBuyQuantity(trade.getBuyQuantity());
            trade.setTradeId(id);
            saveTrade(trade);
            model.addAttribute("trades",getTrades());
            Logger.debug("Trade updated with success");
            return "redirect:/trade/list";
        }

        Logger.warn("Trade save failed");
        return "trade/add";
    }

}
