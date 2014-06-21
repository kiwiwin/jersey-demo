package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;

import java.util.List;

public class MyBatisProductRepository implements ProductRepository {
    @Override
    public Product findProductById(int id) {
        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.getMapper(ProductMapper.class).findProductById(id);
        }
    }

    @Override
    public List<Product> all() {
        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            return sqlSession.getMapper(ProductMapper.class).all();
        }
    }

    @Override
    public int createProduct(Product product) {
        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            sqlSession.getMapper(ProductMapper.class).createProduct(product);
            sqlSession.getMapper(PriceMapper.class).createPrice(product, product.getCurrentPrice());
            sqlSession.commit();
            return product.getId();
        }
    }

    @Override
    public int addNewPrice(Product product, Price price) {
        try (SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory().openSession()) {
            sqlSession.getMapper(PriceMapper.class).createPrice(product, price);
            sqlSession.commit();
            return price.getId();
        }
    }

}
