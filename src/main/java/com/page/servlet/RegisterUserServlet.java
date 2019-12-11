//package com.page.servlet;
//
//import com.gen.service.BaseService;
//import com.gen.util.TokenGenerator;
//import com.page.service.RegisterUserService;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/registerUser")
//public class RegisterUserServlet extends HttpServlet {
//
//    private static final Logger logger = LoggerFactory.getLogger(RegisterUserServlet.class);
//
//    public enum InputType {
//        add, delete, update, list, other, menu, system, view
//    }
//
//    public enum PageType {
//        other, register
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        process(request, response);
//    }
//
//    private String process(HttpServletRequest request, HttpServletResponse response) {
//        String result = null;
//        String jsonpage = "page";
//        //JSONObject jsonObject  =null;
//        try {
//            String page = (request.getParameter("page") == null) ? null : request.getParameter("page");
//
//            request.setCharacterEncoding("UTF-8");
//
//            response.setContentType("text/html");
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
//            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//            response.setDateHeader("Expires", 0); // prevents caching at the
//
//            PrintWriter out = response.getWriter();
//            JSONObject jsonObject = getData(request);
//            if (jsonObject.length() > 0) {
//                PageType p = PageType.valueOf(((page != null) ? page : "other"));
//                switch (p) {
//                    case register:
//                        RegisterUserService formService = (RegisterUserService) com.gen.service.SpringApplicationContext.getBean("registerUserService");
//                        result = service(formService, request, jsonObject);
//                        out.print(result);
//                        break;
//                    default:
//                        break;
//                }
//            } else {
//                result = "ดำเนินการไม่สำเร็จ";
//                out.print(result);
//            }
//
//        } catch (Exception ex) {
//            logger.debug(ex.getMessage(), ex);
//        }
//        return result;
//    }
//
//    public String service(BaseService taskService, HttpServletRequest request, JSONObject jsonObject) {
//        String result = "-1";
//        String mode = (request.getParameter("mode") == null) ? "" : request.getParameter("mode");
//        logger.debug("RegisterUserServlet service {}", mode);
//        InputType m = InputType.valueOf(((mode != null) ? mode : "other"));
//        try {
//            switch (m) {
//                case list:
//                    result = (String) taskService.find_All(request);
//                    break;
//                case view:
//                    result = (String) taskService.findBy_PK(jsonObject);
//                    break;
//                case update:
//                    String taskupdate = (String) taskService.doUpdate(jsonObject);
//                    result = (taskupdate.equalsIgnoreCase("0")) ? "ปรับปรุงข้อมูลสำเร็จ" : "ปรับปรุงข้อมูลไม่สำเร็จ";
//                    break;
//                case add:
//                    result = (String) taskService.doAdd(jsonObject);
////                    result = (taskadd.equalsIgnoreCase("0")) ? "เพิ่มข้อมูลสำเร็จ" : "เพิ่มข้อมูลไม่สำเร็จ";
//                    break;
//                case delete:
//                    String taskdelete = (String) taskService.doDelete(request);
//                    result = (taskdelete.equalsIgnoreCase("0")) ? "ลบข้อมูลสำเร็จ" : "ลบข้อมูลไม่สำเร็จ";
//                    break;
//                default:
//                    break;
//            }
//        } catch (Exception ex) {
////            result = "ดำเนินการไม่สำเร็จ";
//            logger.error(ex.getMessage(), ex);
//        }
//        return result;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        process(request, response);
//    }
//
//    private JSONObject getData(HttpServletRequest request) {
//        JSONObject newuser = new JSONObject();
//        try {
//            String username = request.getParameter("username");
//            String password = request.getParameter("userpassword");
//            String token = request.getParameter("token");
//            JSONObject data = TokenGenerator.getDataFromToken(token);
//            if (data.length() > 0
//                    && username.equals(data.get("userId"))
//                    && password.equals(data.get("password"))) {
//
//                newuser.put("userID", data.get("userId"));
//                newuser.put("password", data.get("password"));
//                newuser.put("userName", data.get("firstname"));
//                newuser.put("userSurname", data.get("lastname"));
//                newuser.put("email", data.get("email"));
//                newuser.put("branchID", "");
//                newuser.put("companyID", "");
//                newuser.put("position", "");
//                String[] userGroupSelected = {"true"};
//                String[] userGroupID = {"newuser"};
//                newuser.put("userGroupSelected", userGroupSelected);
//                newuser.put("userGroupID", userGroupID);
//            } else {
//
//            }
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//        }
//
//        return newuser;
//    }
//
//}
