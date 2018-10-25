package com.phoenix.ecom.filter;

import com.phoenix.ecom.config.Authentication;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@WebFilter
public class JwtAuthorizationTokenFilter implements Filter {

    private static final String ADMIN="admin";

    private static final Set<String> AUTHORIZED_PATHS= Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/","/category", "/product")));

    private static final Set<String> UNAUTHORIZED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "/register")));

    public static final String CUSTOMER="customer";

    @Autowired
    private Authentication authentication;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest serverReq = (HttpServletRequest) request;
//        HttpServletResponse serverRes = (HttpServletResponse)response;
//        String token  = serverReq.getHeader("Authorization");
//
//        String api = serverReq.getRequestURI();
//        String[] finalRoute = api.split("/");
//
//        if(finalRoute.length!=0 && !UNAUTHORIZED_PATHS.contains("/" + finalRoute[finalRoute.length - 1])) {
//            Claims claims=authentication.parseJWT(token);
//            String path=serverReq.getRequestURI().substring(serverReq.getContextPath().length()).replaceAll("[/]+$", "");
//            String[] apiRoute = path.split("/");
//            boolean allowedPath=AUTHORIZED_PATHS.contains("/" + apiRoute[apiRoute.length-1]);
//
//            if (claims.getSubject().equals(ADMIN) && allowedPath) {
//                chain.doFilter(request, response);
//                } else if (claims.getSubject().equals(CUSTOMER) && serverReq.getMethod().equalsIgnoreCase("GET") && allowedPath) {
//                    chain.doFilter(request, response);
//                } else {
//                    serverRes.sendError(403, "Forbidden");
//                }
//            }else{
//            chain.doFilter(request, response);
//            }
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
