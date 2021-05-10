package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class CurveService {

    @Autowired
    private CurvePointRepository curvePointRepository ;

    public List<CurvePoint> getCurvePoints(){
        return curvePointRepository.findAll();
    }

    public CurvePoint saveCurvePoint(CurvePoint curvePoint){
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return savedCurvePoint ;
    }

    public Optional<CurvePoint> getCurvePoint(Integer id){
        return curvePointRepository.findById(id);
    }

    public void deleteCurvePoint(Integer id){
        curvePointRepository.deleteById(id);
    }

    public String saveCurvePointOrUpdate(Integer id, CurvePoint curvePoint, BindingResult result, Model model){

        if(id == null){
            if(!result.hasErrors()){
                saveCurvePoint(curvePoint);
                model.addAttribute("curvePoints",getCurvePoints());
                Logger.debug("Curve point saved with success");
                return "redirect:/curvePoint/list";
            }
        }
        else{
            if(result.hasErrors()){
                Logger.warn("Curve point update failed ");
                return "curvePoint/update" ;
            }

            curvePoint.setCurveId(curvePoint.getCurveId());
            curvePoint.setTerm(curvePoint.getTerm());
            curvePoint.setValue(curvePoint.getValue());
            curvePoint.setId(id);
            saveCurvePoint(curvePoint);
            model.addAttribute("curvePoints",getCurvePoints());
            Logger.debug("Curve point saved with success");
            return "redirect:/curvePoint/list";
        }


        Logger.warn("Curve point save failed");
        return "curvePoint/add";
    }
}
