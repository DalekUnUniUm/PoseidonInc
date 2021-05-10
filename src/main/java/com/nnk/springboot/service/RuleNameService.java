package com.nnk.springboot.service;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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

    public String saveRuleNameOrUpdate(Integer id, RuleName ruleName, BindingResult result, Model model){

        if(id == null){
            if(!result.hasErrors()){
                saveRuleName(ruleName);
                model.addAttribute("ruleNames",getRulesNames());
                Logger.debug("Rule name saved with success");
                return "redirect:/ruleName/list" ;
            }
        }
        else{
            if(result.hasErrors()){
                Logger.warn("Rule name update failed");
                return "ruleName/update" ;
            }

            ruleName.setName(ruleName.getName());
            ruleName.setDescription(ruleName.getDescription());
            ruleName.setJson(ruleName.getJson());
            ruleName.setTemplate(ruleName.getTemplate());
            ruleName.setSqlStr(ruleName.getSqlStr());
            ruleName.setSqlPart(ruleName.getSqlPart());
            ruleName.setId(id);

            saveRuleName(ruleName);
            model.addAttribute("ruleNames",getRulesNames());
            Logger.debug("Rule name updated with success");
            return "redirect:/ruleName/list";
        }
        Logger.warn("Rule name save failed");
        return "ruleName/add";
    }

}
