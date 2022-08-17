package com.cali.justboard.repository;

import com.cali.justboard.config.JpaConfig;
import com.cali.justboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    public void select_test() throws Exception {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @DisplayName("inset 테스트")
    @Test
    public void insert_test() throws Exception {
        // given
        long previousArticleCount = articleRepository.count();

        // when
        Article article = articleRepository.save(Article.of("new Article", "test", null));

        // then
        assertThat(articleRepository.count()).isEqualTo(124);
    }

    @DisplayName("update 테스트")
    @Test
    public void update_test() throws Exception {
        // given
        Article findArticle = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        findArticle.setHashtag(updatedHashtag);

        // when
        Article savedArticle = articleRepository.saveAndFlush(findArticle);

        // then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    public void delete_test() throws Exception {
        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();

        // when
        articleRepository.delete(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);
    }
}