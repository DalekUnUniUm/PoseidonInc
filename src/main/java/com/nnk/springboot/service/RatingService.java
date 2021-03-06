package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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

    public void saveRatingOrUpdate(Integer id, Rating rating) {
        /**If id equal null, so it's a new Rating **/
        if (id == null) {
            saveRating(rating);
        }
        /**If id not null, so it's a Updated Rating**/
        else {
            rating.setMoodysRating(rating.getMoodysRating());
            rating.setSandPRating(rating.getSandPRating());
            rating.setFitchRating(rating.getFitchRating());
            rating.setOrderNumber(rating.getOrderNumber());
            rating.setId(id);
            saveRating(rating);
        }
    }
}
