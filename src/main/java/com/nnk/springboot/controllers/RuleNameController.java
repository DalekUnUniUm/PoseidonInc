package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService ;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        Logger.info("Receive rule name list");
        model.addAttribute("ruleNames",ruleNameService.getRulesNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        Logger.info("Load rule name add page");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if(!result.hasErrors()){
            ruleNameService.saveRuleName(ruleName);
            model.addAttribute("ruleNames",ruleNameService.getRulesNames());
            Logger.debug("Rule name saved with success");
            return "redirect:/ruleName/list" ;
        }
        Logger.warn("Rule name save failed");
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load rule name update page");
        RuleName ruleName = ruleNameService.getRuleName(id).orElseThrow(()-> new IllegalArgumentException("Invalid Rule Name Id:" + id));
        model.addAttribute("ruleName",ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
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

        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames",ruleNameService.getRulesNames());
        Logger.debug("Rule name updated with success");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleName(id).orElseThrow(()-> new IllegalArgumentException("Invalid Rule Name Id:" + id));
        ruleNameService.getRuleName(id);
        model.addAttribute("ruleNames",ruleNameService.getRulesNames());
        Logger.debug("Rule name deleted with success");
        return "redirect:/ruleName/list";
    }
}
