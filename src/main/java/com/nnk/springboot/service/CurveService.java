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

    public void saveCurvePointOrUpdate(Integer id, CurvePoint curvePoint) {
        /**If id equal null, so it's a new Curve**/
        if (id == null) {
            saveCurvePoint(curvePoint);
        }
        /**If id not null, so it's a Updated Curve**/
        else {
            curvePoint.setCurveId(curvePoint.getCurveId());
            curvePoint.setTerm(curvePoint.getTerm());
            curvePoint.setValue(curvePoint.getValue());
            curvePoint.setId(id);
            saveCurvePoint(curvePoint);
        }
    }
}
