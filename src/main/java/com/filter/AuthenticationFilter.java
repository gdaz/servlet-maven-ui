package com.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

public class AuthenticationFilter implements Filter {

    // This should be your default Home or Login page
    // "login.seam" if you use Jboss Seam otherwise "login.jsf"
    // "login.xhtml" or whatever

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private String timeoutPage = "index.jsp";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        logger.info("AuthenticationFilter");
        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }

    private String getTimeoutPage() {
        return timeoutPage;
    }

    public void setTimeoutPage(String timeoutPage) {
        this.timeoutPage = timeoutPage;
    }
}
