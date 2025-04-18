package org.example.expert.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public void logBefore() {
        logger.info("[AOP] 메서드 실행 전: UserRole 변경 요청");
    }

    @After("execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public void logAfter() {
        logger.info("[AOP] 메서드 실행 후: UserRole 변경 완료");
    }

    @Around("execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 이름
        String methodName = joinPoint.getSignature().toShortString();

        // 파라미터 정보 (요청 본문 포함)
        Object[] args = joinPoint.getArgs();

        logger.info("[AOP] 메서드 실행 전(around): {} 시작", methodName);
        for (Object arg : args) {
            logger.info("[AOP] 요청 파라미터: {}", arg); // 여기서 요청 본문(JSON 객체 등 포함 가능)
        }

        Object result = joinPoint.proceed(); // 실제 메서드 실행 (응답 객체 나옴)

        logger.info("[AOP] 응답 결과: {}", result); // 응답 본문
        logger.info("[AOP] 메서드 실행 후(around): {} 완료", methodName);

        return result;
    }
}
