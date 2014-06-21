package org.kiwi.api;

import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.kiwi.json.CreateProductRefJson;
import org.kiwi.json.ProductRefJson;
import org.kiwi.json.ProductsItemRefJson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductRepository productRepository;


    @Path("{id}/prices")
    public PriceResource getPriceResource(@PathParam("id") int id) {
        return new PriceResource(productRepository.findProductById(id));
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsItemRefJson> getAllProducts() {
        return productRepository.all().stream()
                .map(product -> new ProductsItemRefJson(product))
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRefJson getProductById(@PathParam("id") int id) {
        final Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ResourceNotFoundException();
        }
        return new ProductRefJson(product);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(CreateProductRefJson createProductRefJson) {
        final int newProductId = productRepository.createProduct(createProductRefJson.getProduct());
        return Response.status(201).header("location", "/products/" + newProductId).build();
    }
}
