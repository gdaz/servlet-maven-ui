package com.page.servlet;

import com.model.User;
import com.util.TokenGenerator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TokenServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TokenServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // prevents caching at the
        HttpSession httpsession = (HttpSession) request.getSession();
        PrintWriter out = response.getWriter();
        String result = null;
        try {
            User user = (User) httpsession.getAttribute("user");
            JSONObject json = new JSONObject();
            if (user != null) {
                json.put("roles", "user");
                json.put("email", user.getEmail());
                json.put("userPK", user.getUserPK());
                json.put("username", user.getEmpID());
            } else {
                // JSONObject json = new JSONObject();
                json.put("roles", "dummy");
                json.put("email", "dummy@mail.com");
                json.put("userPK", "0");
                json.put("username", "999");
            }
            result = TokenGenerator.getTemporaryToken(json);
            out.print(result);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
