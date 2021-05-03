package com.nnk.springboot.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_List_Id")
    private Integer bidListId ;
    @NotBlank(message = "Account is mandatory")
    private String account ;
    @NotBlank(message = "Type is mandatory")
    private String type ;
    @Column(name = "bid_Quantity")
    private Double bidQuantity ;

    @Column(name = "ask_Quantity")
    private Double askQuantity ;

    private Double bid ;

    private Double ask ;

    private String benchmark ;

    @Column(name = "bid_List_Date")
    private Timestamp bidListDate ;

    private String commentary ;

    private String security ;

    private String status ;

    private String trader ;

    private String book ;

    @Column(name = "creation_Name")
    private String creationName ;

    @Column(name = "creation_Date")
    private Timestamp creationDate ;

    @Column(name = "revision_Name")
    private String revisionName ;

    @Column(name = "revision_Date")
    private Timestamp revisionDate ;

    @Column(name = "deal_Name")
    private String dealName ;
    @Column(name = "deal_Type")
    private String dealType ;

    @Column(name = "source_List_Id")
    private String sourceListId ;

    private String side ;

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
