package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository ;

    public List<BidList> getBidLists(){
        return bidListRepository.findAll();
    }

    public BidList saveBidList(BidList bid){
        BidList savedBidList = bidListRepository.save(bid);
        return savedBidList ;
    }

    public Optional<BidList> getBidList(Integer id){
        return bidListRepository.findById(id);
    }

    public void deleteBidList(Integer id){
        bidListRepository.deleteById(id);
    }

    public String saveBidListOrUpdate(Integer id, BidList bidList, BindingResult result, Model model){

        if(id == null){
            Logger.info("Add bid list");
            if(!result.hasErrors()){
                saveBidList(bidList);
                model.addAttribute("bidLists",getBidLists());
                Logger.debug("Bid list saved with success");
                return "redirect:/bidList/list" ;
            }
        }
        else{
            if(result.hasErrors()){
                Logger.warn("Updated bid list failed");
                return "bidList/update" ;
            }
            Logger.debug("Bid list updated with success");
            bidList.setAccount(bidList.getAccount());
            bidList.setType(bidList.getType());
            bidList.setBidQuantity(bidList.getBidQuantity());
            bidList.setBidListId(bidList.getBidListId());
            saveBidList(bidList);
            model.addAttribute("bidLists",getBidLists());
            return "redirect:/bidList/list";
        }

        Logger.warn("Failed");
        return "bidList/add" ;
    }

}
