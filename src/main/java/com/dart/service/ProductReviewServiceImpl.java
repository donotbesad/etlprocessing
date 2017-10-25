package com.dart.service;

import com.dart.api.repository.ProductReviewRepository;
import com.dart.api.service.ProductReviewService;
import com.dart.api.service.ServiceFacade;
import com.dart.domain.ParseEntry;
import com.dart.domain.ParseStatus;
import com.dart.domain.product.ProductReview;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by dart on 23.10.17.
 */

@Service
public class ProductReviewServiceImpl extends BaseServiceImpl<ProductReview, ProductReviewRepository> implements ProductReviewService {

    interface Names {
        String CENEO_URL = "https://www.ceneo.pl/";
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

    @Override
    public void extract(ParseEntry toParse, ParseEntry existing) throws Exception {
        StringBuilder url = new StringBuilder(ProductReviewServiceImpl.Names.CENEO_URL)
                .append(toParse.getProductCode())
                .append(ProductReviewServiceImpl.Names.TAB_REVIEW);
        Document document = Jsoup.connect(url.toString()).get();

        if (document == null) {
            toParse.setStatus(ParseStatus.NOT_FOUND);
            return;
        }

        Elements elements = document.select(ProductReviewServiceImpl.Names.REVIEW_BOX);

        if (CollectionUtils.isNotEmpty(elements)) {
            toParse.setParsedCount(transform(toParse, existing, elements));
        }
    }

    @Override
    public int transform(ParseEntry toParse, ParseEntry existing, Elements elements) {
        int parsedElementsCount = 0;

        for (Element element : elements) {
            LocalDateTime publishedDate = getPublishedDate(element);

            if (existing == null || publishedDate.isAfter(existing.getCreatedDate())) {
                ProductReview review = new ProductReview();
                review.setPublishedDate(publishedDate);
                review.setParseEntry(toParse);
                setComment(element, review);
                setBenefits(element, review);
                setDefects(element, review);
                setReviewer(element, review);
                setRecommended(element, review);
                setLikes(element, review);
                setStarsCount(element, review);
                insert(review);

                parsedElementsCount++;
            }
        }

        return parsedElementsCount;

    }

    private void setBenefits(Element element, ProductReview review) {
        Elements rawBenefits = element.select(ProductReviewServiceImpl.Names.BENEFITS);
        if (CollectionUtils.isNotEmpty(rawBenefits)) {
            review.setBenefits(new ArrayList<>(rawBenefits.stream()
                    .map(Element::text)
                    .collect(Collectors.toList())));
        }
    }

    private void setDefects(Element element, ProductReview review) {
        Elements rawDefects = element.select(ProductReviewServiceImpl.Names.DEFECTS);
        if (CollectionUtils.isNotEmpty(rawDefects)) {
            review.setDefects(new ArrayList<>(rawDefects.stream()
                    .map(Element::text)
                    .collect(Collectors.toList())));
        }
    }

    private void setComment(Element element, ProductReview review) {
        review.setComment(element.select(ProductReviewServiceImpl.Names.COMMENT).text());
    }

    private void setReviewer(Element element, ProductReview review) {
        String reviewer = element.select(ProductReviewServiceImpl.Names.REVIEWER).text();
        if (StringUtils.isEmpty(reviewer)) {
            reviewer = Names.DEFAULT_AUTHOR;
        }
        review.setAuthor(reviewer);
    }

    private void setRecommended(Element element, ProductReview review) {
        review.setRecommended(element.select(ProductReviewServiceImpl.Names.RECOMMENDED).text().equalsIgnoreCase(ProductReviewServiceImpl.Names.IS_RECOMMENDED));
    }

    private void setLikes(Element element, ProductReview review) {
        review.setLikesCount(Integer.parseInt(element.select(ProductReviewServiceImpl.Names.LIKES_COUNT).text()));
        review.setDislikesCount(Integer.parseInt(element.select(ProductReviewServiceImpl.Names.DISLIKES_COUNT).text()));
    }

    private void setStarsCount(Element element, ProductReview review) {
        review.setStarsCount(Character.getNumericValue(element.select(ProductReviewServiceImpl.Names.STARS_COUNT).text().charAt(0)));
    }

    private LocalDateTime getPublishedDate(Element element) {
        LocalDateTime publishedDate = null;
        String rawPublishedDate = element.select(ProductReviewServiceImpl.Names.PUBLISHED_DATE).attr(ProductReviewServiceImpl.Names.PUBLISHED_DATE_ATTR);
        if (StringUtils.isNotEmpty(rawPublishedDate)) {
            publishedDate = LocalDateTime.parse(rawPublishedDate.replace(" ", "T"));
        }
        return publishedDate;
    }
}
