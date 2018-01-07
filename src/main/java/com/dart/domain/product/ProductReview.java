package com.dart.domain.product;

import com.dart.domain.DomainObject;
import com.dart.domain.ParseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representation of Product Review domain object
 *
 * Author: Dmitry Artemenko
 * Date: 23.10.17
 * Time: 14:08
 *
 * @author Dmitry Artemenko
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "product_review")
public class ProductReview extends DomainObject {

    public interface Columns {
        String PARSED_ENTRY_ID = "parsed_entry_uuid";
        String COMMENT = "comment";
        String DEFECTS = "defects";
        String BENEFITS = "benefits";
        String STARS_COUNT = "stars_count";
        String AUTHOR = "author";
        String RECOMMENDED = "recommended";
        String LIKES_COUNT = "likes_count";
        String DISLIKES_COUNT = "dislikes_count";
        String PUBLISHED_DATE = "published_date";
    }

    public interface Fields {
        String PARSE_ENTRY_ID = "parseEntryId";
        String PRODUCT_CODE = "productCode";
        String COMMENT = "comment";
        String AUTHOR = "author";
        String DEFECTS = "defects";
        String BENEFITS = "benefits";
        String PUBLISHED_DATE = "publishedDate";
        String STARS_COUNT = "starsCount";
        String LIKES_COUNT = "likesCount";
        String DISLIKES_COUNT = "dislikesCount";
        String RECOMMENDED = "recommended";
    }

    @ManyToOne
    @JoinColumn(name = Columns.PARSED_ENTRY_ID, nullable = false)
    private ParseEntry parseEntry;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = Columns.COMMENT)
    private String comment;

    @ElementCollection
    @Column(name = Columns.DEFECTS)
    private List<String> defects;

    @ElementCollection
    @Column(name = Columns.BENEFITS)
    private List<String> benefits;

    @Column(name = Columns.STARS_COUNT)
    private int starsCount;

    @Column(name = Columns.AUTHOR)
    private String author;

    @Column(name = Columns.RECOMMENDED)
    private boolean recommended;

    @Column(name = Columns.LIKES_COUNT)
    private int likesCount;

    @Column(name = Columns.DISLIKES_COUNT)
    private int dislikesCount;

    @Column(name = Columns.PUBLISHED_DATE)
    private LocalDateTime publishedDate;

}
