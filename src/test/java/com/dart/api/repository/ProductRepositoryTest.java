package com.dart.api.repository;

import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import com.dart.domain.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


/**
 * Author: Dmitry Artemenko
 * Date: 24.11.17
 * Time: 1:30
 *
 * @author Dmitry Artemenko
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String TYPE = "type";
    private static final int PRODUCT_CODE = 111;
    private static final int PARSED_COUNT = 10;

    @Test
    public void whenFindByParseEntryProductCode_thenReturnProduct() {
        Product product = new Product()
                .setBrand(BRAND)
                .setModel(MODEL)
                .setType(TYPE);

        ParseEntry parseEntry = new ParseEntry()
                .setParsedCount(PARSED_COUNT)
                .setProductCode(PRODUCT_CODE)
                .setStatus(ParseStatus.PARSED);
        product.setParseEntry(parseEntry);

        entityManager.persist(parseEntry);
        entityManager.persist(product);
        entityManager.flush();

        Product found = productRepository.findByParseEntryProductCode(PRODUCT_CODE);

        assertEquals(product.getParseEntry().getProductCode(), found.getParseEntry().getProductCode());
    }
}
