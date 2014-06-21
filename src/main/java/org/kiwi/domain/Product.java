package org.kiwi.domain;

import java.util.List;

public class Product {

    private int id;
    private String name;
    private String description;
    private Price currentPrice;
    private List<Price> historyPrices;

    public Product(int id, String name, String description, Price currentPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }

    public Product(String name, String description, Price currentPrice) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Price getCurrentPrice() {
        return currentPrice;
    }

    //for jackson
    public Product() {

    }

    public List<Price> getHistoryPrices() {
        return historyPrices;
    }
}
