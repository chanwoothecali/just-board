package com.cali.justboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class DataRestControllerTest {

    private final MockMvc mvc;

    public DataRestControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("api 게시글 리스트 조회")
    @Test
    public void api_get_articles_list() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                // hal json은 MeiaType에 정의된 것이 없기때문에 아래처럼 새로 정의
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 게시글 단건 조회")
    @Test
    public void api_get_article() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 게시글 댓글 리스트 조회")
    @Test
    public void api_get_articles_comments() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 댓글 리스트 조회")
    @Test
    public void api_get_article_comments() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 댓글 단건 조회")
    @Test
    public void api_get_article_comment() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
