package com.cali.justboard.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class ArticleComment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;

    @Setter @ManyToOne(optional = false) private Article article; // 원글

    @Setter @Column(nullable = false, length = 500) private String content; // 본문

    private ArticleComment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new ArticleComment(article, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
