package org.kiwi.persistent;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class MyBatisProductRepository implements ProductRepository {
    private SqlSessionFactory sqlSessionFactory;

    @Inject
    public MyBatisProductRepository(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findByProductId(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.selectProductById(id);
        }
    }

    @Override
    public List<Product> all() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.all();
        }
    }

}
