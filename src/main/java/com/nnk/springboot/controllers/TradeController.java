package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService ;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        Logger.info("Receive trade list");
        model.addAttribute("trades",tradeService.getTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        Logger.info("Load trade add page");
        return "trade/add";
    }
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if(!result.hasErrors()){
            Logger.debug("Trade saved with success");
            tradeService.saveTradeOrUpdate(null,trade);
            model.addAttribute("trades",tradeService.getTrades());
            return "redirect:/trade/list" ;
        }
        Logger.warn("Rule name save failed");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load trade update page");
        Trade trade = tradeService.getTrade(id).orElseThrow(()-> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.warn("Trade update failed");
            return "trade/update" ;
        }
        tradeService.saveTradeOrUpdate(id,trade);
        model.addAttribute("trades",tradeService.getTrades());
        Logger.debug("Trade updated with success");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTrade(id).orElseThrow(()-> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.deleteTrade(id);
        model.addAttribute("trades",tradeService.getTrades());
        Logger.debug("Trade deleted with success");
        return "redirect:/trade/list";
    }
}
