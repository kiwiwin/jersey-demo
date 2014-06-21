package org.kiwi;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Product;
import org.kiwi.persistent.MyBatisConnectionFactory;
import org.kiwi.persistent.MyBatisProductRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class MyBatisProductRepositoryTest {
    private MyBatisProductRepository productRepository;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
        productRepository = new MyBatisProductRepository(sqlSessionFactory);
    }

    @Test
    public void should_get_product_by_id() {
        final Product product = productRepository.findByProductId(1);
        assertThat(product.getId(), is(1));
        assertThat(product.getName(), is("apple juice"));
        assertThat(product.getDescription(), is("good"));

        assertThat(product.getHistoryPrices().size(), is(2));
    }

    @Test
    public void should_get_all_products() {
        final List<Product> products = productRepository.all();
        assertThat(products.size(), is(2));

        final Product product1 = products.get(0);
        assertThat(product1.getId(), is(1));
        assertThat(product1.getName(), is("apple juice"));
        assertThat(product1.getDescription(), is("good"));

        final Product product2 = products.get(1);
        assertThat(product2.getId(), is(2));
        assertThat(product2.getName(), is("banana juice"));
        assertThat(product2.getDescription(), is("not good"));
    }
}
