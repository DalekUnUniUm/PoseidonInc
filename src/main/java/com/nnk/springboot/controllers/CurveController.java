package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.runtime.Log;

import javax.validation.Valid;

@Controller
public class CurveController {

    @Autowired
    private CurveService curveService ;


    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        Logger.info("Receive curve point list");
        model.addAttribute("curvePoints",curveService.getCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        Logger.info("Load add curve point page");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(!result.hasErrors()){
            curveService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoints",curveService.getCurvePoints());
            Logger.debug("Curve point saved with success");
            return "redirect:/curvePoint/list";
        }
        Logger.warn("Curve point save failed");
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load curve point update page");
        CurvePoint curvePoint = curveService.getCurvePoint(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point id:" + id));
        model.addAttribute("curvePoint",curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.warn("Curve point update failed ");
            return "curvePoint/update" ;
        }

        curvePoint.setCurveId(curvePoint.getCurveId());
        curvePoint.setTerm(curvePoint.getTerm());
        curvePoint.setValue(curvePoint.getValue());
        curvePoint.setId(id);
        curveService.saveCurvePoint(curvePoint);
        model.addAttribute("curvePoints",curveService.getCurvePoints());
        Logger.debug("Curve point saved with success");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curveService.getCurvePoint(id).orElseThrow(() -> new IllegalArgumentException("Invalid curve point id:" + id));
        curveService.deleteCurvePoint(id);
        model.addAttribute("curvePoints",curveService.getCurvePoints());
        Logger.debug("Curve point deleted with success");
        return "redirect:/curvePoint/list";
    }
}
