package com.dart.service;

import com.dart.api.service.ExportService;
import com.dart.model.ProductReviewDTO;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 03.11.17
 * Time: 18:17
 *
 * @author Dmitry Artemenko
 */
@Service
public class ExportServiceImpl implements ExportService {

    private static final String[] headers = new String[]{
            "Product code",
            "Author",
            "Comment",
            "Benefits",
            "Defects",
            "Publication date",
            "Stars count",
            "Likes count",
            "Dislikes count",
            "Recommended"
    };

    @Override
    public void writeReviewsToCsv(PrintWriter writer, List<ProductReviewDTO> reviews) {
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(headers);
        for (ProductReviewDTO review : reviews) {
            csvWriter.writeNext(prepareColumnsValues(review));
        }
    }

    @Override
    public void writeReviewToCsv(PrintWriter writer, ProductReviewDTO review) {
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(headers);
        csvWriter.writeNext(prepareColumnsValues(review));
    }

    private String[] prepareColumnsValues(ProductReviewDTO review) {
        List<String> values = new ArrayList<>();
        values.add(review.getProductCode());
        values.add(review.getAuthor());
        values.add(review.getComment());
        values.add(prepareListColumn(review.getBenefits()));
        values.add(prepareListColumn(review.getDefects()));
        values.add(review.getPublishedDate() != null ? review.getPublishedDate().toString() : null);
        values.add(Integer.toString(review.getStarsCount()));
        values.add(Integer.toString(review.getLikesCount()));
        values.add(Integer.toString(review.getStarsCount()));
        values.add(prepareRecommendedColumn(review.isRecommended()));
        return values.toArray(new String[]{});
    }

    private String prepareListColumn(List<String> values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (i != values.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private String prepareRecommendedColumn(boolean recommended) {
        return recommended ? "yes" : "no";
    }
}
