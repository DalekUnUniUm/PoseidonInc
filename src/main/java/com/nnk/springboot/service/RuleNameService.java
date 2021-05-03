package com.nnk.springboot.service;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository ;

    public List<RuleName> getRulesNames(){
        return ruleNameRepository.findAll();
    }

    public RuleName saveRuleName(RuleName ruleName){
        RuleName savedRuleName = ruleNameRepository.save(ruleName);
        return savedRuleName ;
    }

    public Optional<RuleName> getRuleName(Integer id){
        return ruleNameRepository.findById(id);
    }

    public void deleteRuleName(Integer id){
        ruleNameRepository.deleteById(id);
    }

}
