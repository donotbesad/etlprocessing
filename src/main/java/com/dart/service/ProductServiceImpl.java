package com.dart.service;

import com.dart.api.repository.ProductRepository;
import com.dart.api.service.ProductService;
import com.dart.domain.ParseEntry;
import com.dart.domain.product.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Author: Dmitry Artemenko
 * Date: 29.10.17
 * Time: 10:47
 *
 * @author Dmitry Artemenko
 */

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository> implements ProductService {

    interface Names {
        String BODY = "#body";
        String PRODUCT_INFORMATION_SECTION = "#productTechSpecs";
        String BRAND_TD = "th:contains(Producent)";
        String TR = "tr";
        String BRAND = ".attr-value a";
        String STRONG = "strong";
        String SPAN = "span";
    }

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());

    @Override
    public Product findByProductCode(int productCode) {
        return getRepository().findByParseEntryProductCode(productCode);
    }

    @Override
    public void extract(ParseEntry toParse, ParseEntry existing, Document document) throws Exception {
        if (existing != null) return;
        Elements elements = document.select(Names.BODY);
        transform(toParse, null, elements);
    }

    @Override
    public void transform(ParseEntry toParse, ParseEntry existing, Elements elements) {
        Product product = new Product();
        product.setParseEntry(toParse);
        setBrand(product, elements);
        setModel(product, elements);
        setType(product, elements);
        save(product);
    }


    private void setBrand(Product product, Elements elements) {
        try {
            Element productInformationSection = elements.select(Names.PRODUCT_INFORMATION_SECTION).get(0);
            Elements parents = productInformationSection.select(Names.BRAND_TD).parents();
            Element brandElement = parents.select(Names.TR).get(0);
            if (brandElement != null) {
                String brand = brandElement.select(Names.BRAND).text();
                product.setBrand(brand);
            }
        } catch (Exception ignored) {
            log.warn("Failed parse brand for product: " + product.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setModel(Product product, Elements elements) {
        try {
            Element productTitle = elements.select(Names.STRONG).get(0);
            if (productTitle != null) {
                String rawName = productTitle.text();
                if (StringUtils.isNotEmpty(rawName)) {
                    product.setModel(productTitle.text().replaceAll("(?i)" + product.getBrand(), "").trim());
                }
            }
        } catch (Exception ignored) {
            log.warn("Failed parse model for product: " + product.getParseEntry().getProductCode(), ignored);
        }
    }

    private void setType(Product product, Elements elements) {
        try {
            Element productType = elements.select(Names.SPAN).get(3);
            if (productType != null) {
                product.setType(productType.text());
            }
        } catch (Exception ignored) {
            log.warn("Failed parse type for product: " + product.getParseEntry().getProductCode(), ignored);
        }
    }
}
