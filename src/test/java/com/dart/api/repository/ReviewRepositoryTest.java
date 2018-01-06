package com.dart.api.repository;

import com.dart.domain.DomainObject;
import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import com.dart.domain.product.ProductReview;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Author: Dmitry Artemenko
 * Date: 24.11.17
 * Time: 10:51
 *
 * @author Dmitry Artemenko
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository reviewRepository;

    private static final int PRODUCT_CODE = 111;
    private static final int PARSED_COUNT = 1;
    private UUID RANDOM_UUID;
    private static final PageRequest PAGE = new PageRequest(0, 10);
    private static final Sort SORT = new Sort(Sort.Direction.DESC, DomainObject.Fields.CREATED_DATE);

    @Before
    public void setUp() {
        ParseEntry parseEntry = new ParseEntry()
                .setStatus(ParseStatus.PARSED)
                .setProductCode(PRODUCT_CODE)
                .setParsedCount(PARSED_COUNT);

        entityManager.persist(parseEntry);

        ProductReview review = new ProductReview()
                .setParseEntry(parseEntry)
                .setAuthor("author")
                .setBenefits(Collections.singletonList("useful"))
                .setDefects(Collections.singletonList("sometimes work wrong"))
                .setComment("comment message")
                .setDislikesCount(1)
                .setLikesCount(10)
                .setPublishedDate(LocalDateTime.now().minusDays(1))
                .setStarsCount(4);

        entityManager.persist(review);
        entityManager.flush();
        RANDOM_UUID = parseEntry.getUuid();
    }

    @Test
    public void findByParseEntryProductCode__thenReturnListOfProductReviews() throws Exception {
        List<ProductReview> found = reviewRepository.findByParseEntryProductCode(PRODUCT_CODE, SORT);
        assertEquals(found.get(0).getParseEntry().getProductCode(), PRODUCT_CODE);
    }

    @Test
    public void findByParseEntryUuid_thenReturnListOfProductReviews() throws Exception {
        List<ProductReview> found = reviewRepository.findByParseEntryUuid(RANDOM_UUID, SORT);
        assertEquals(found.get(0).getParseEntry().getUuid(), RANDOM_UUID);
    }

    @Test
    public void findByParseEntryProductCode_thenReturnPageOfProductReviews() throws Exception {
        Page<ProductReview> found = reviewRepository.findByParseEntryProductCode(PRODUCT_CODE, PAGE);
        assertEquals(found.getTotalPages(), 1);
        assertEquals(found.getTotalElements(), 1);
    }

    @Test
    public void findByParseEntryUuid_thenReturnPageOfProductReviews() throws Exception {
        Page<ProductReview> found = reviewRepository.findByParseEntryUuid(RANDOM_UUID, PAGE);
        assertEquals(found.getTotalPages(), 1);
        assertEquals(found.getTotalElements(), 1);
    }

}