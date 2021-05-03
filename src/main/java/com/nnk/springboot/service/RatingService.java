package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository ;

    public List<Rating> getRatings(){
        return ratingRepository.findAll();
    }

    public Rating saveRating(Rating rating){
        Rating savedRating = ratingRepository.save(rating);
        return savedRating ;
    }

    public Optional<Rating> getRating(Integer id){
        return ratingRepository.findById(id);
    }

    public void deleteRating(Integer id){
        ratingRepository.deleteById(id);
    }

}
