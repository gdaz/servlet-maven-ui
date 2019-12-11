//package com.page.servlet;
//
//import org.json.JSONObject;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.*;
//import java.awt.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CaptchaServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<Color> colors = new ArrayList<Color> ();
//        colors.add(Color.black);
//        colors.add(Color.red);
//
//        List<Font> fonts = new ArrayList<Font>();
//        fonts.add(new Font("Arial", Font.ITALIC, 40));
//
//        Captcha captcha = new Captcha.Builder(120, 50)
//                .addText(new DefaultWordRenderer(colors, fonts))
//                .addBackground(new GradiatedBackgroundProducer(Color.white, Color.GRAY))
//             //   .addNoise()
//                .gimp()
//                .addBorder()
//                .build();
//        Cookie ck=new Cookie("simpleCaptcha",captcha.getAnswer());//creating cookie object
//       // response.addCookie(ck);//adding cookie in the response
//        // display the image produced
//        CaptchaServletUtil.writeImage(response, captcha.getImage());
//     //   System.out.println("-------------------->"+captcha.getAnswer());
//        // save the captcha value on session
//        request.getSession().setAttribute("simpleCaptcha", captcha);
//
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    	response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//		response.setDateHeader("Expires", 0); // prevents caching at the
//		PrintWriter out = response.getWriter();
//		HttpSession httpsession = (HttpSession) request.getSession();
//		String result = null;
//    	try {
//
//    			String captcha = request.getParameter("captcha");
//		    	Captcha original =  (Captcha)request.getSession().getAttribute("simpleCaptcha");
//		    	if(original.getAnswer().equalsIgnoreCase(captcha)) {
//		    		/*JSONObject json = new JSONObject();
//		    		json.put("roles", "newuser");
//		    		json.put("email", "user");
//		    		json.put("userPK", "0");
//		    		json.put("userID", "999");
//		    		String token = TokenGenerator.getToken(json,1,"newuser");
//		    		result = "{\"token\":\""+token+"\",\"status\":true}";*/
//		    		 User user = (User)httpsession.getAttribute("user");
//	    		     JSONObject json = new JSONObject();
//			    		json.put("roles", "user");
//			    		json.put("email", user.getEmail());
//			    		json.put("userPK",  user.getUserPK());
//			    		json.put("username",  user.getEmpID());
//		    		result = TokenGenerator.getTemporaryToken(json);
//		    	//	System.out.println("-------------------->"+result);
//		    		out.print(result);
//
//		    	}else {
//		    		result = "{\"token\":\"\",\"status\":false}";
//
//		    		out.print(result);
//		    	}
//    	}catch(Exception ex) {}
//    }
//}