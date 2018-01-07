package com.dart.api.service;

import com.dart.model.ProductReviewDTO;

import java.io.PrintWriter;
import java.util.List;

/**
 * Service for export data to CSV files
 *
 * Author: Dmitry Artemenko
 * Date: 03.11.17
 * Time: 17:59
 *
 * @author Dmitry Artemenko
 */
public interface ExportService {

    /**
     * Write collection of reviews to CSV file
     *
     * @param writer definition of PrintWriter
     * @param reviews list of Reviews
     */
    void writeReviewsToCsv(PrintWriter writer, List<ProductReviewDTO> reviews);

    /**
     * Write single review to CSV file
     *
     * @param writer definition of PrintWriter
     * @param review single review
     */
    void writeReviewToCsv(PrintWriter writer, ProductReviewDTO review);
}
