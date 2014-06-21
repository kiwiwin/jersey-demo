package org.kiwi.domain;

public class Price {

    private int id;
    private int price;
    private String date;
    private String modifiedBy;


    public String getDate() {
        return date;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public int getPrice() {
        return price;
    }


    public Price() {

    }

    public Price(int id, int price, String date, String modifiedBy) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.modifiedBy = modifiedBy;
    }

    public Price(int price, String date, String modifiedBy) {
        this.price = price;
        this.date = date;
        this.modifiedBy = modifiedBy;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (price != price1.price) return false;
        if (date != null ? !date.equals(price1.date) : price1.date != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(price1.modifiedBy) : price1.modifiedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
