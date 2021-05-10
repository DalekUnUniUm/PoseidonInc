package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {

    @Autowired
    private RatingService ratingService ;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        Logger.info("Received rating list");
        model.addAttribute("ratings",ratingService.getRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        Logger.info("Load rating add page");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        return ratingService.saveRatingOrUpdate(null,rating,result,model);
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load rating update page");
        Rating rating = ratingService.getRating(id).orElseThrow(()-> new IllegalArgumentException("Invalid rating id:" + id));
        model.addAttribute("rating",rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        return ratingService.saveRatingOrUpdate(id,rating,result,model);
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRating(id).orElseThrow(()-> new IllegalArgumentException("Invalid rating id:" + id));
        ratingService.deleteRating(id);
        model.addAttribute("ratings",ratingService.getRatings());
        Logger.debug("Rating deleted with success");
        return "redirect:/rating/list";
    }
}
