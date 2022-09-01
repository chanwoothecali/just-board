package com.cali.justboard.service;

import com.cali.justboard.domain.type.SearchType;
import com.cali.justboard.dto.ArticleDto;
import com.cali.justboard.dto.ArticleUpdateDto;
import com.cali.justboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType type, String search_keyword) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(Long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto articleDto) {
//        articleRepository.save()
    }

    public void updateArticle(Long articleId, ArticleUpdateDto articleUpdateDto) {

    }

    public void deleteArticle(Long articleId) {
    }
}
