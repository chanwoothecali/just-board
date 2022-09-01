package com.cali.justboard.service;

import com.cali.justboard.domain.Article;
import com.cali.justboard.domain.ArticleComment;
import com.cali.justboard.dto.ArticleCommentDto;
import com.cali.justboard.repository.ArticleCommentRepository;
import com.cali.justboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글 댓글을 조회한다.")
    @Test
    public void search_article_comment() throws Exception {
        // given
        Long articleId = 1L;
        Article article = Article.of(null, "title", "content", "#java");
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // when
        Page<ArticleCommentDto> articleComments = sut.searchArticleComments(1L);

        // then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    @Test
    public void save_article_comment() throws Exception {
        // given
        ArticleCommentDto articleCommentDto = ArticleCommentDto.of(LocalDateTime.now(), "Cali", "test content");
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // when
        sut.saveArticleComment(articleCommentDto);
        
        // then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @DisplayName("댓글 id와 수정사항이 입력되면 댓글을 수정한다.")
    @Test
    public void modify_article_comment() throws Exception {
        // given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // when
        sut.updateArticleComment(1L, "testContent");

        // then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @DisplayName("댓글의 id가 주어지면 댓글을 삭제한다.")
    @Test
    public void delete_article_comment() throws Exception {
        // given
        willDoNothing().given(articleCommentRepository).delete(any(ArticleComment.class));

        // when
        sut.deleteArticleComment(1L);

        // then
        then(articleCommentRepository).should().delete(any(ArticleComment.class));
    }
}