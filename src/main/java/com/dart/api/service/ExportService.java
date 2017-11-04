package com.dart.api.service;

import com.dart.model.ProductReviewDTO;

import java.io.PrintWriter;
import java.util.List;

/**
 * Author: Dmitry Artemenko
 * Date: 03.11.17
 * Time: 17:59
 *
 * @author Dmitry Artemenko
 */
public interface ExportService {

    void writeReviewsToCsv(PrintWriter writer, List<ProductReviewDTO> reviews);

    void writeReviewToCsv(PrintWriter writer, ProductReviewDTO review);
}
