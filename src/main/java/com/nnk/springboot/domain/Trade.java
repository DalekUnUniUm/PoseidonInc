package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.function.DoubleBinaryOperator;


@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Trade_Id")
    private Integer tradeId ;
    @NotBlank(message = "Account is mandatory")
    private String account ;
    @NotBlank(message = "Account is mandatory")
    private String type ;

    @Column(name = "buy_Quantity")
    private Double buyQuantity ;

    @Column(name = "sell_Quantity")
    private Double sellQuantity ;
    @Column(name = "buy_Price")
    private Double buyPrice ;
    @Column(name = "sell_Price")
    private Double sellPrice ;

    private String benchmark ;
    @Column(name = "trade_Date")
    private Timestamp tradeDate ;

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

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
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

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
