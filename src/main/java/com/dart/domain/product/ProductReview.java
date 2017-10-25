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
 * Created by dart on 23.10.17.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Entity
@Table(name = "product_review")
public class ProductReview extends DomainObject {

    interface Columns {
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
