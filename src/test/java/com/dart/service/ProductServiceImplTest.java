package com.dart.service;

import com.dart.api.repository.ProductRepository;
import com.dart.api.service.ProductService;
import com.dart.domain.ParseEntry;
import com.dart.domain.product.Product;
import com.dart.utils.ProductParseUtil;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 25.11.17
 * Time: 16:51
 *
 * @author Dmitry Artemenko
 */

@RunWith(SpringRunner.class)
@Ignore
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    private static final int FIRST_PRODUCT_ID = 24690722;
    private static final int SECOND_PRODUCT_ID = 53529799;
    private static final int THIRD_PRODUCT_ID = 45002653;

    private static final List<Document> pages;

    static {
        pages = new ArrayList<>();
        pages.add(ProductParseUtil.retrieveProductPage(FIRST_PRODUCT_ID));
        pages.add(ProductParseUtil.retrieveProductPage(SECOND_PRODUCT_ID));
        pages.add(ProductParseUtil.retrieveProductPage(THIRD_PRODUCT_ID));
    }

    @BeforeClass
    public void setUp() {
        Mockito.when(productRepository.save(new Product().setParseEntry(new ParseEntry()).setBrand("brand")));
    }


    @Test
    public void findByProductCode() throws Exception {
    }

    @Test
    public void extract() throws Exception {
        for (Document page : pages) {
            ParseEntry toParse = new ParseEntry();
            ParseEntry existing = new ParseEntry();

            productService.extract(toParse, existing, page);




        }

    }

    @Test
    public void transform() throws Exception {
    }

}