package com.dart.api.repository;

import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Author: Dmitry Artemenko
 * Date: 24.11.17
 * Time: 2:20
 *
 * @author Dmitry Artemenko
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParseEntryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParseEntryRepository parseEntryRepository;

    private static final int PRODUCT_CODE = 111;
    private static final int PARSED_COUNT = 3;

    @Test
    public void findFirstByProductCodeOrderByCreatedDateDesc_thenReturnProduct() {
        ParseEntry first = new ParseEntry()
                .setProductCode(PRODUCT_CODE)
                .setParsedCount(PARSED_COUNT)
                .setStatus(ParseStatus.PARSED);
        entityManager.persist(first);

        ParseEntry second = new ParseEntry()
                .setProductCode(PRODUCT_CODE)
                .setParsedCount(PARSED_COUNT)
                .setStatus(ParseStatus.FAILED);
        second.setCreatedDate(LocalDateTime.now().plusSeconds(1));
        entityManager.persist(second);

        ParseEntry third = new ParseEntry()
                .setProductCode(PRODUCT_CODE)
                .setParsedCount(PARSED_COUNT)
                .setStatus(ParseStatus.FAILED);
        third.setCreatedDate(LocalDateTime.now().plusSeconds(2));
        entityManager.persist(third);
        entityManager.flush();

        ParseEntry found = parseEntryRepository.findFirstByProductCodeOrderByCreatedDateDesc(PRODUCT_CODE);
        Assert.assertEquals(third, found);
        Assert.assertNotEquals(first, found);
        Assert.assertNotEquals(second, found);
    }
}
