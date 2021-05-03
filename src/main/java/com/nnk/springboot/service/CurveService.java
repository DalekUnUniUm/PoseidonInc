package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
