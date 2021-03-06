package org.kiwi.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceResourceTest extends JerseyTest {

    private ProductRepository mockProductRepository;
    private Product mockProduct;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected Application configure() {
        mockProductRepository = mock(ProductRepository.class);
        mockProduct = mock(Product.class);
        return new ResourceConfig()
                .register(ProductsResource.class)
                .register(JacksonFeature.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(mockProductRepository).to(ProductRepository.class);
                    }
                });
    }


    @Test
    public void should_get_product_price() {

        when(mockProductRepository.findProductById(1)).thenReturn(mockProduct);
        when(mockProduct.getHistoryPrices()).thenReturn(Arrays.asList(new Price(1, 13, "2014-03-03", "admin")));

        final Response response = target("/products/1/prices/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final Map price = response.readEntity(Map.class);
        assertThat(price.get("price"), is(13));
        assertThat(price.get("timestamp"), is("2014-03-03"));
        assertThat(price.get("modifiedBy"), is("admin"));
    }

    @Test
    public void should_get_product_current_price() {
        when(mockProductRepository.findProductById(1)).thenReturn(mockProduct);
        when(mockProduct.getCurrentPrice()).thenReturn(new Price(13, "2014-03-03", "admin"));

        final Response response = target("/products/1/prices/current")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final Map price = response.readEntity(Map.class);
        assertThat(price.get("price"), is(13));
        assertThat(price.get("timestamp"), is("2014-03-03"));
        assertThat(price.get("modifiedBy"), is("admin"));
    }

    @Test
    public void should_get_product_history_prices() {
        when(mockProductRepository.findProductById(1)).thenReturn(mockProduct);
        when(mockProduct.getHistoryPrices()).thenReturn(Arrays.asList(new Price(1, 13, "2014-03-03", "admin")));


        final Response response = target("/products/1/prices")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final List prices = response.readEntity(List.class);

        assertThat(prices.size(), is(1));

        final Map price = (Map) prices.get(0);
        assertThat(price.get("price"), is(13));
        assertThat(price.get("timestamp"), is("2014-03-03"));
        assertThat(price.get("modifiedBy"), is("admin"));
    }

    @Test
    public void should_add_new_price() {
        when(mockProductRepository.findProductById(1)).thenReturn(mockProduct);
        when(mockProduct.getId()).thenReturn(1);

        HashMap newPrice = new HashMap<String, String>();
        newPrice.put("price", 120);
        newPrice.put("timestamp", "2014-03-03");
        newPrice.put("modifiedBy", "admin");


        final Response response = target("/products/1/prices")
                .request()
                .post(Entity.entity(newPrice, MediaType.APPLICATION_JSON));

        assertThat(response.getStatus(), is(201));
    }
}
