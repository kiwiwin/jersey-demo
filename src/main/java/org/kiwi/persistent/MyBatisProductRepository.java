package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
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

}
