package org.kiwi;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.kiwi.api.ProductsResource;
import org.kiwi.domain.ProductRepository;
import org.kiwi.persistent.MyBatisConnectionFactory;
import org.kiwi.persistent.MyBatisProductRepository;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JacksonFeature.class);

        register(ProductsResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new MyBatisProductRepository(MyBatisConnectionFactory.getSqlSessionFactory())).to(ProductRepository.class);
            }
        });

    }
}