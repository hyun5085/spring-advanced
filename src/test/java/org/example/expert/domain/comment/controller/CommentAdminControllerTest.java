package org.example.expert.domain.comment.controller;

import org.example.expert.config.LoggingInterceptor;
import org.example.expert.config.WebConfig;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentAdminController.class)
@Import({LoggingInterceptor.class, WebConfig.class}) // Interceptor 및 WebConfig 등록
class CommentAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentAdminService commentAdminService;

    @Test
    void deleteComment_정상작동() throws Exception {
        long commentId = 1L;

        mockMvc.perform(delete("/admin/comments/{commentId}", commentId))
                .andExpect(status().isOk()); // 또는 isNoContent() depending on 실제 구현
        // 로그는 콘솔에서 확인 가능
    }
}