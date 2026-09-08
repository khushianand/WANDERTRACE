package com.wandertrace.security;
import com.wandertrace.exception.AccessDeniedException;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import org.springframework.stereotype.Component;
@Component public class CurrentUser { public static final String SESSION_KEY="wandertrace.userId"; public UUID require(HttpSession session){Object value=session.getAttribute(SESSION_KEY);if(value instanceof UUID id)return id;throw new AccessDeniedException();} }
