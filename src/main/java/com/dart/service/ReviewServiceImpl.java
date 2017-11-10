package com.dart.service;

import com.dart.api.repository.ReviewRepository;
import com.dart.api.service.ReviewService;
import com.dart.domain.ParseEntry;
import com.dart.domain.product.ProductReview;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:10
 *
 * @author Dmitry Artemenko
 */

@Service
public class ReviewServiceImpl extends BaseServiceImpl<ProductReview, ReviewRepository> implements ReviewService {

    interface Names {
        String TAB_REVIEW = "#tab=reviews";

        String IS_RECOMMENDED = "Polecam";
        String PUBLISHED_DATE_ATTR = "datetime";
        String DEFAULT_AUTHOR = "Anonim";

        //Selectors
        String REVIEW_BOX = ".product-reviews .review-box";
        String BENEFITS = ".pros-cell ul li";
        String DEFECTS = ".cons-cell ul li";
        String COMMENT = ".product-review-body";
        String REVIEWER = ".reviewer-name-line";
        String RECOMMENDED = ".product-review-summary em";
        String LIKES_COUNT = ".vote-yes.js_product-review-vote.js_vote-yes span";
        String DISLIKES_COUNT = ".vote-no.js_product-review-vote.js_vote-no span";
        String STARS_COUNT = ".review-score-count";
        String PUBLISHED_DATE = ".review-time time";
    }

    private static final Logger log = Logger.getLogger(ReviewServiceImpl.class.getName());

    @Override
    public List<ProductReview> findReviewsByProductCode(int productCode, Sort sort) {
        return getRepository().findByParseEntryProductCode(productCode, sort);
    }

    @Override
    public List<ProductReview> findReviewsByParseEntry(UUID uuid, Sort sort) {
        return getRepository().findByParseEntryUuid(uuid, sort);
    }

    @Override
    public Page<ProductReview> findReviewsByProductCode(int productCode, Pageable pageable) {
        return getRepository().findByParseEntryProductCode(productCode, pageable);
    }

    @Override
    public Page<ProductReview> findReviewsByParseEntry(UUID uuid, Pageable pageable) {
        return getRepository().findByParseEntryUuid(uuid, pageable);
    }

    @Override
    public void extract(ParseEntry toParse, ParseEntry existing, Document document) throws Exception {
        Elements elements = document.select(ReviewServiceImpl.Names.REVIEW_BOX);
        transform(toParse, existing, elements);
    }

    @Override
    public void transform(ParseEntry toParse, ParseEntry existing, Elements elements) {
        int parsedElementsCount = 0;

        for (Element element : elements) {
            LocalDateTime publishedDate = getPublishedDate(element, toParse);

            if (existing == null || publishedDate != null && publishedDate.isAfter(existing.getCreatedDate())) {
                ProductReview review = new ProductReview();
                review.setPublishedDate(publishedDate);
                review.setParseEntry(toParse);
                setComment(element, review);
                setBenefits(element, review);
                setDefects(element, review);
                setAuthor(element, review);
                setRecommended(element, review);
                setLikes(element, review);
                setStarsCount(element, review);
                insert(review);

                parsedElementsCount++;
            }
        }

        toParse.setParsedCount(toParse.getParsedCount() + parsedElementsCount);
    }

    private void setBenefits(Element element, ProductReview review) {
        try {
            Elements rawBenefits = element.select(ReviewServiceImpl.Names.BENEFITS);
            if (CollectionUtils.isNotEmpty(rawBenefits)) {
                review.setBenefits(new ArrayList<>(rawBenefits.stream()
                        .map(Element::text)
                        .collect(Collectors.toList())));
            }
        } catch (Exception ignored) {
            log.warn("Failed parse review benefits for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setDefects(Element element, ProductReview review) {
        try {
            Elements rawDefects = element.select(ReviewServiceImpl.Names.DEFECTS);
            if (CollectionUtils.isNotEmpty(rawDefects)) {
                review.setDefects(new ArrayList<>(rawDefects.stream()
                        .map(Element::text)
                        .collect(Collectors.toList())));
            }
        } catch (Exception ignored) {
            log.warn("Failed parse review defects for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setComment(Element element, ProductReview review) {
        try {
            review.setComment(element.select(ReviewServiceImpl.Names.COMMENT).text());
        } catch (Exception ignored) {
            log.warn("Failed parse review comment for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setAuthor(Element element, ProductReview review) {
        try {
            String reviewer = element.select(ReviewServiceImpl.Names.REVIEWER).text();
            if (StringUtils.isEmpty(reviewer)) {
                reviewer = Names.DEFAULT_AUTHOR;
            }
            review.setAuthor(reviewer);
        } catch (Exception ignored) {
            log.warn("Failed parse review author for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setRecommended(Element element, ProductReview review) {
        try {
            review.setRecommended(element.select(ReviewServiceImpl.Names.RECOMMENDED).text().equalsIgnoreCase(ReviewServiceImpl.Names.IS_RECOMMENDED));
        } catch (Exception ignored) {
            log.warn("Failed parse review recommendation for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setLikes(Element element, ProductReview review) {
        try {
            review.setLikesCount(Integer.parseInt(element.select(ReviewServiceImpl.Names.LIKES_COUNT).text()));
            review.setDislikesCount(Integer.parseInt(element.select(ReviewServiceImpl.Names.DISLIKES_COUNT).text()));
        } catch (Exception ignored) {
            log.warn("Failed parse review likes for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setStarsCount(Element element, ProductReview review) {
        try {
            review.setStarsCount(Character.getNumericValue(element.select(ReviewServiceImpl.Names.STARS_COUNT).text().charAt(0)));
        } catch (Exception ignored) {
            log.warn("Failed parse review author for product: " + review.getParseEntry().getProductCode(), ignored);
        }
    }

    private LocalDateTime getPublishedDate(Element element, ParseEntry toParse) {
        LocalDateTime publishedDate = null;
        try {
            String rawPublishedDate = element.select(ReviewServiceImpl.Names.PUBLISHED_DATE).attr(ReviewServiceImpl.Names.PUBLISHED_DATE_ATTR);
            if (StringUtils.isNotEmpty(rawPublishedDate)) {
                publishedDate = LocalDateTime.parse(rawPublishedDate.replace(" ", "T"));
            }
        } catch (Exception ignored) {
            log.warn("Failed parse review publication date for product: " + toParse.getProductCode(), ignored);
        }
        return publishedDate;
    }
}
