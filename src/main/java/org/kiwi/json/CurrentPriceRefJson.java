package org.kiwi.json;

public class CurrentPriceRefJson {
    private String uri;
    private int price;

    //for jackson
    public CurrentPriceRefJson() {

    }

    public CurrentPriceRefJson(int productId, int price) {
        this.price = price;
        this.uri = "/products/" + productId + "/current";
    }

    public int getPrice() {
        return price;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentPriceRefJson that = (CurrentPriceRefJson) o;

        if (price != that.price) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + price;
        return result;
    }
}
