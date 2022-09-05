package com.cali.justboard.repository;

import com.cali.justboard.domain.ArticleComment;
import com.cali.justboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); // 리스팅 하지 않은 결과는 검색에서 제외
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 시분초까지 동일하게 검색해야 해서 좋은 조건은 아님. 추후 수정 필요
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

    // 연관관계 매핑 되어있는 article의 id에 접근하기 위해 스네이크케이스 사용
    List<ArticleComment> findByArticle_Id(Long articleId);
}
