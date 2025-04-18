package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String method = request.getMethod(); // 요청 메서드 (DELETE)
        String uri = request.getRequestURI(); // 요청 URI (/admin/comments/{commentId})
        String ip = request.getRemoteAddr(); // 클라이언트 IP (옵션)

        // 요청 시작 로그
        log.info("[요청 시작] {} {} from {}", method, uri, ip);
        request.setAttribute("startTime", System.currentTimeMillis()); // 시작 시간 기록
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        // 요청 종료 로그
        log.info("[요청 종료] {} {} → {} (처리 시간: {}ms)",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration
        );
    }

}
