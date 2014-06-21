package org.kiwi.api;

import org.kiwi.domain.Price;
import org.kiwi.domain.Product;
import org.kiwi.json.PriceRefJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceResource {
    private final Product product;

    public PriceResource(Product product) {
        this.product = product;
    }

    @GET
    @Path("{priceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PriceRefJson getPrice(@PathParam("priceId") int priceId) {
        final List<Price> historyPrices = product.getHistoryPrices();
        for (Price historyPrice : historyPrices) {
            if (historyPrice.getId() == priceId) {
                return new PriceRefJson(product, historyPrice);
            }
        }
        return null;
    }

    @GET
    @Path("current")
    @Produces(MediaType.APPLICATION_JSON)
    public PriceRefJson getPrice() {
        final List<Price> historyPrices = product.getHistoryPrices();
        final Price currentPrice = historyPrices.stream()
                .min((price1, price2) -> price1.getId() - price2.getId())
                .get();

        return new PriceRefJson(product, currentPrice);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceRefJson> historyPrices() {
        return product.getHistoryPrices().stream()
                .map(price -> new PriceRefJson(product, price))
                .collect(Collectors.toList());
    }
}
