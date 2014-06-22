package org.kiwi.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.api.ProductsResource;
import org.kiwi.api.ResourceNotFoundException;
import org.kiwi.api.ResourceNotFoundHandler;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductsResourceTest extends JerseyTest {
    private ProductRepository mockProductRepository;
    private Product dummyProduct;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        dummyProduct = new Product(1, "apple juice", "YES", new Price(15, "2014-07-03", "admin"));
    }

    @Override
    protected Application configure() {
        mockProductRepository = mock(ProductRepository.class);
        return new ResourceConfig()
                .register(ProductsResource.class)
                .register(JacksonFeature.class)
                .register(ResourceNotFoundHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(mockProductRepository).to(ProductRepository.class);
                    }
                });
    }

    @Test
    public void should_get_all_products() {
        when(mockProductRepository.all()).thenReturn(Arrays.asList(dummyProduct));

        final Response response = target("/products")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final List products = response.readEntity(List.class);

        assertThat(products.size(), is(1));

        final Map productProperties = (Map) products.get(0);

        assertThat(productProperties.get("uri"), is("/products/1"));
        assertThat(productProperties.get("id"), is(1));
        assertThat(productProperties.get("name"), is("apple juice"));

        final Map currentPriceProperties = (Map) productProperties.get("price");
        assertThat(currentPriceProperties.get("uri"), is("/products/1/prices/current"));
        assertThat(currentPriceProperties.get("price"), is(15));
    }

    @Test
    public void should_get_product_by_id() {
        when(mockProductRepository.findProductById(1)).thenReturn(dummyProduct);

        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final Map productProperties = response.readEntity(Map.class);

        assertThat(productProperties.get("uri"), is("/products/1"));
        assertThat(productProperties.get("id"), is(1));
        assertThat(productProperties.get("name"), is("apple juice"));
        assertThat(productProperties.get("description"), is("YES"));

        final Map currentPriceProperties = (Map) productProperties.get("current_price");
        assertThat(currentPriceProperties.get("uri"), is("/products/1/prices/current"));
        assertThat(currentPriceProperties.get("price"), is(15));
    }

    @Test
    public void should_get_404_when_request_product_is_not_exist() {
        when(mockProductRepository.findProductById(1)).thenThrow(new ResourceNotFoundException());

        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_get_201_when_create_new_product() {
        when(mockProductRepository.createProduct(anyObject())).thenReturn(3);

        HashMap newProduct = new HashMap<String, String>();
        newProduct.put("name", "new juice");
        newProduct.put("description", "hard");
        HashMap newPrice = new HashMap<String, String>();
        newPrice.put("price", 120);
        newPrice.put("timestamp", "2014-03-03");
        newPrice.put("modifiedBy", "admin");
        newProduct.put("price", newPrice);

        final Response response = target("/products")
                .request()
                .post(Entity.entity(newProduct, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
    }
}
