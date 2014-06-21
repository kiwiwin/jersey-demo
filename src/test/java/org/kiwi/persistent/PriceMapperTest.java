package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriceMapperTest {
    private SqlSession sqlSession;
    private PriceMapper priceMapper;
    private ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
        sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
        priceMapper = sqlSession.getMapper(PriceMapper.class);
        productMapper = sqlSession.getMapper(ProductMapper.class);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
        sqlSession.close();
    }

    @Test
    public void should_create_price_mapper() {
        final Product product = productMapper.findProductById(1);
        final Price newPrice = new Price(100, "2014-09-03", "admin");
        priceMapper.createPrice(product, newPrice);

        final Product updatedProduct = productMapper.findProductById(product.getId());
        assertThat(updatedProduct.getCurrentPrice().getPrice(), is(100));
    }
}
