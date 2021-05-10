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

    public void saveBidListOrUpdate(Integer id, BidList bidList){
        /**If id equal null, so it's a new Bid List**/
        if(id == null){
            saveBidList(bidList);
        }
        /**If id not null, so it's a Updated Bid List**/
        else{
            bidList.setAccount(bidList.getAccount());
            bidList.setType(bidList.getType());
            bidList.setBidQuantity(bidList.getBidQuantity());
            bidList.setBidListId(bidList.getBidListId());
            saveBidList(bidList);
        }
    }
}
