package org.kiwi.api;

import org.kiwi.domain.Price;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.json.CreatePriceRefJson;
import org.kiwi.json.PriceRefJson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceResource {
    private final ProductRepository productRepository;
    private final Product product;

    public PriceResource(ProductRepository productRepository, Product product) {
        this.productRepository = productRepository;
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
        return new PriceRefJson(product, product.getCurrentPrice());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceRefJson> historyPrices() {
        return product.getHistoryPrices().stream()
                .map(price -> new PriceRefJson(product, price))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewPrice(CreatePriceRefJson createPriceRefJson) {
        final int priceId = productRepository.addNewPrice(product, createPriceRefJson.getPrice());
        return Response.status(201).header("location", "/products/" + product.getId() + "/prices/" + priceId).build();
    }
}
