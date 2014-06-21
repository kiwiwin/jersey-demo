package org.kiwi.domain;

public class Price {

    private int id;
    private int price;
    private String timestamp;
    private String modifiedBy;


    public String getTimestamp() {
        return timestamp;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public int getPrice() {
        return price;
    }


    public Price() {

    }

    public Price(int id, int price, String timestamp, String modifiedBy) {
        this.id = id;
        this.price = price;
        this.timestamp = timestamp;
        this.modifiedBy = modifiedBy;
    }

    public Price(int price, String timestamp, String modifiedBy) {
        this.price = price;
        this.timestamp = timestamp;
        this.modifiedBy = modifiedBy;
    }

    public int getId() {
        return id;
    }
}
