package org.example.carshop.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JWTEntrypoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JWTEntrypoint.class);
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error Message: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Sai tài khoản hoặc mật khẩu");
    }
}
