package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;
import org.kiwi.persistent.MyBatisConnectionFactory;
import org.kiwi.persistent.ProductMapper;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ProductMapperTest {

    private SqlSession sqlSession;
    private ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
        sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        productMapper = sqlSession.getMapper(ProductMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }

    @Test
    public void should_create_new_product() {
        final Product product = new Product("new juice", "i don't know", new Price(120, "2014-07-03", "admin"));
        productMapper.createProduct(product);

        final Product newProduct = productMapper.findProductById(product.getId());

        assertThat(newProduct.getName(), is("new juice"));
        assertThat(newProduct.getDescription(), is("i don't know"));
        assertThat(newProduct.getCurrentPrice().getPrice(), is(120));
    }

    @Test
    public void should_get_product_by_id() {
        final Product product = productMapper.findProductById(1);
        assertThat(product.getId(), is(1));
        assertThat(product.getName(), is("apple juice"));
        assertThat(product.getDescription(), is("good"));

        assertThat(product.getHistoryPrices().size(), is(2));
    }

    @Test
    public void should_get_all_products() {
        final List<Product> products = productMapper.all();
        assertThat(products.size(), is(2));

        final Product product1 = products.get(0);
        assertThat(product1.getId(), is(1));
        assertThat(product1.getName(), is("apple juice"));
        assertThat(product1.getDescription(), is("good"));
        assertThat(product1.getCurrentPrice().getPrice(), is(120));
        assertThat(product1.getCurrentPrice().getTimestamp(), is("2013-05-10"));
        assertThat(product1.getCurrentPrice().getModifiedBy(), is("admin"));

        final Product product2 = products.get(1);
        assertThat(product2.getId(), is(2));
        assertThat(product2.getName(), is("banana juice"));
        assertThat(product2.getDescription(), is("not good"));
    }
}
