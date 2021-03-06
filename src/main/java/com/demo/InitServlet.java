package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InitServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("getRequestURI {}", req.getRequestURI());

        try {
            req.setCharacterEncoding("UTF-8");

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Expires", "0");

            HttpSession httpsession = (HttpSession) req.getSession();

//            httpsession.setAttribute("token", "sdvsdvosdvpoimspiovmosidmvoisndvoisndovinsodvinsoidvnosinv");
            req.getRequestDispatcher("/WEB-INF/pages/bpmstarter.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
