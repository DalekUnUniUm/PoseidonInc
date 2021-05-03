package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
