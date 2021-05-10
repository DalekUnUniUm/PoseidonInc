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

    public String saveRatingOrUpdate(Integer id, Rating rating, BindingResult result, Model model){
        /**If id equal null, so it's a new Rating **/
        if(id == null){
            if(!result.hasErrors()){
                saveRating(rating);
                model.addAttribute("ratings",getRatings());
                Logger.debug("Rating saved with success");
                return "redirect:/rating/list" ;
            }
        }
        /**If id not null, so it's a Updated Rating**/
        else{
            if(result.hasErrors()){
                Logger.warn("Rating update failed");
                return "rating/update" ;
            }

            rating.setMoodysRating(rating.getMoodysRating());
            rating.setSandPRating(rating.getSandPRating());
            rating.setFitchRating(rating.getFitchRating());
            rating.setOrderNumber(rating.getOrderNumber());
            rating.setId(id);
            saveRating(rating);
            model.addAttribute("ratings",getRatings());
            Logger.debug("Rating updated with success");
            return "redirect:/rating/list";
        }
        Logger.warn("Rating save failed");
        return "rating/add";
    }

}
