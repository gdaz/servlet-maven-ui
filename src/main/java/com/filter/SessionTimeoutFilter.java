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

public class SessionTimeoutFilter implements Filter {

    // This should be your default Home or Login page
    // "login.seam" if you use Jboss Seam otherwise "login.jsf"
    // "login.xhtml" or whatever

    private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutFilter.class);

    private String timeoutPage = "index.jsp";

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        if ((request instanceof HttpServletRequest)
                && (response instanceof HttpServletResponse)) {

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            HttpSession session = httpServletRequest.getSession(false);

//            logger.info("HttpSession {}", session);
//            logger.info("Timestamp {} IP Address {}", timestamp, httpServletRequest.getRemoteAddr());
            logger.info("User-Agent {}", httpServletRequest.getHeader("User-Agent"));

            if (isSessionControlRequiredForThisResource(httpServletRequest)) {
//                logger.info("{}", httpServletRequest.getRequestedSessionId());
//                logger.info("{}", httpServletRequest.isRequestedSessionIdValid());
                if (isSessionInvalid(httpServletRequest)) {
                    String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
                    httpServletResponse.sendRedirect(timeoutUrl);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isSessionControlRequiredForThisResource(HttpServletRequest httpServletRequest) {
        String requestPath = httpServletRequest.getRequestURI();
//        logger.info("{}", requestPath);
        boolean controlRequired = !StringUtils.contains(requestPath, getTimeoutPage());
        return controlRequired;
    }

    private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
        boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
                && !httpServletRequest.isRequestedSessionIdValid();
        return sessionInValid;
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
