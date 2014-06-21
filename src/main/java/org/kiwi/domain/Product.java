package org.kiwi.domain;

import java.util.List;

public class Product {

    private int id;
    private String name;
    private String description;
    private int currentPrice;
    private List<Price> historyPrices;

    public Product(int id, String name, String description, int currentPrice) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }

    public Product(String name, String description, int currentPrice) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (currentPrice != product.currentPrice) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + currentPrice;
        return result;
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

    public int getCurrentPrice() {
        return currentPrice;
    }



    //for jackson
    public Product() {

    }

    public List<Price> getHistoryPrices() {
        return historyPrices;
    }
}
