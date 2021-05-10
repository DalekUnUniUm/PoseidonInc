package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    @Autowired
    private BidListService bidListService ;


    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        Logger.info("Receive Bid List");
        model.addAttribute("bidLists",bidListService.getBidLists());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {

        Logger.info("Load bid list add page");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        return bidListService.saveBidListOrUpdate(null,bid,result,model);
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load bid list update page");
        BidList bidList = bidListService.getBidList(id).orElseThrow(() -> new IllegalArgumentException("Invalid Bid List Id:" + id));
        model.addAttribute("bidList",bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        return bidListService.saveBidListOrUpdate(id,bidList,result,model);
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        Logger.debug("Bid list deleted with success");
        BidList bidList = bidListService.getBidList(id).orElseThrow(() -> new IllegalArgumentException("Invalid Bid List Id:" + id));
        bidListService.deleteBidList(id);
        model.addAttribute("bidLists",bidListService.getBidLists());
        return "redirect:/bidList/list";
    }
}
