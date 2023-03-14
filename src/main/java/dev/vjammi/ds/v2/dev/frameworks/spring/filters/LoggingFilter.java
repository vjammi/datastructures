package dev.vjammi.ds.v2.dev.frameworks.spring.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component // to recognize a filter, we define it as a bean with the @Component annotation.
@Order(1)  // to have the filters fire in the right order, we need to use the @Order annotation.
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //logger.info("Logging Request  {} : {}", req.getMethod(),  req.getRequestURI()); chain.doFilter(request, response);

        // ...

        //logger.info("Logging Response :{}", res.getContentType());
    }

    @Override
    public void destroy() {

    }

}