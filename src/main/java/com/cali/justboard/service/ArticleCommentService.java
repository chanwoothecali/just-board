package com.cali.justboard.service;

import com.cali.justboard.dto.ArticleCommentDto;
import com.cali.justboard.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public Page<ArticleCommentDto> searchArticleComments(Long articleId) {
        return Page.empty();
    }

    public void saveArticleComment(ArticleCommentDto articleCommentDto) {

    }

    public void updateArticleComment(Long articleCommentId, String updateContent) {

    }

    public void deleteArticleComment(Long articleCommentId) {

    }
}
