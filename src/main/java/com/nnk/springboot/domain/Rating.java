package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id ;
    @Column(name = "moodys_Rating")
    @NotBlank(message = "Moodys Rating is mandatory")
    private String moodysRating ;
    @Column(name = "sand_P_Rating")
    @NotBlank(message = "Sand P Rating is mandatory")
    private String sandPRating ;
    @Column(name = "fitch_Rating")
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating ;
    @Column(name = "order_Number")
    @NotNull(message = "Must be not null")
    private Integer orderNumber ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }
}
