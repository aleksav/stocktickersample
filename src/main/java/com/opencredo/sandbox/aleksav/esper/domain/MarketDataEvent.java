package com.opencredo.sandbox.aleksav.esper.domain;

import com.espertech.esper.client.EventBean;

/**
 * @author Aleksa Vukotic
 */
public class MarketDataEvent {
    private String name;
    private String symbol;

    private double price;
    private double ask;
    private double bid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
