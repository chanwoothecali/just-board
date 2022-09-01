package com.cali.justboard.service;

import com.cali.justboard.domain.Article;
import com.cali.justboard.domain.type.SearchType;
import com.cali.justboard.dto.ArticleDto;
import com.cali.justboard.dto.ArticleUpdateDto;
import com.cali.justboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면 검색결과를 반환한다.")
    @Test
    public void search_articles() throws Exception {
        // given

        // when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "test");

        // then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회한다.")
    @Test
    public void get_article_detail() throws Exception {
        // given

        // when
        ArticleDto article = sut.searchArticle(1L);

        // then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보가 입력되면 게시글을 생성한다.")
    @Test
    public void write_article() throws Exception {
        // given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Cali", "title", "test", "#test");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.saveArticle(dto);

        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 id와 수정사항을 입력하면 게시글을 수정한다.")
    @Test
    public void modify_article() throws Exception {
        // given
        ArticleUpdateDto dto = ArticleUpdateDto.of("updateTitle", "updateContent", "#update");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // when
        sut.updateArticle(1L, dto);

        // then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 id가 넘어오면 해당 게시글을 삭제한다.")
    @Test
    public void delete_article() throws Exception {
        // given
        willDoNothing().given(articleRepository).delete(any(Article.class)); // willDoNothing 코드는 명시적일뿐인 코드

        // when
        sut.deleteArticle(1L);

        // then
        then(articleRepository).should().delete(any(Article.class));
    }
}