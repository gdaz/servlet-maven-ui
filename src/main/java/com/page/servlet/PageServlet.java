//package com.page.servlet;
//
//import com.page.service.AnswerPageService;
//import com.page.service.ExamPageService;
//import com.page.service.InfoPageService;
//import com.service.BaseService;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class PageServlet extends HttpServlet {
//	private Logger logger = LoggerFactory.getLogger(PageServlet.class);
//
//	public static enum InputType {
//		add, delete, update, list, other ,menu ,system,view
//	};
//	public enum PageType {
//		other,procedure,course,registration,enrollment,car,motorcycle,page,exam,answer
//	};
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		process(request, response);
//	}
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		process(request, response);
//	}
//	public String service(BaseService taskService, HttpServletRequest request, JSONObject jsonObject) throws Exception{
//		logger.info("PageServlet.service");
//		String result = "";
//		String jsonmode = (jsonObject== null)?null:(String)jsonObject.get("mode");
//		String mode = (request.getParameter("mode") == null) ? "" : request.getParameter("mode");
//		mode = (jsonmode == null) ? mode : jsonmode;
//		InputType m = InputType.valueOf(((mode!=null)?mode:"other"));
//		try{
//			switch(m){
//				case list:
//					result = (String)taskService.find_All(request);
//					break;
//				case view:
//					result = (String)taskService.findBy_PK(jsonObject);
//					break;
//				case update:
//					String taskupdate = (String)taskService.doUpdate(jsonObject);
//					result = (taskupdate.equalsIgnoreCase("0"))?"ปรับปรุงข้อมูลสำเร็จ":"ปรับปรุงข้อมูลไม่สำเร็จ";
//					break;
//				case add:
//					String taskadd = (String)taskService.doAdd(jsonObject);
//					result = (taskadd.equalsIgnoreCase("0"))?"เพิ่มข้อมูลสำเร็จ":"เพิ่มข้อมูลไม่สำเร็จ";
//					break;
//				case delete:
//					String taskdelete = (String)taskService.doDelete(request);
//					result = (taskdelete.equalsIgnoreCase("0"))?"ลบข้อมูลสำเร็จ":"ลบข้อมูลไม่สำเร็จ";
//					break;
//				default:
//					break;
//			}
//		}catch(Exception ex){
//			logger.error(ex.getMessage(), ex);
//		}
//		return result;
//	}
//	/*public String examService(BaseService taskService,HttpServletRequest request,JSONObject jsonObject) throws Exception{
//		String result = "";
//		String jsonmode = (jsonObject== null)?null:(String)jsonObject.get("mode");
//		String mode = (request.getParameter("mode") == null) ? "": request.getParameter("mode");
//		mode = (jsonmode == null) ? mode:jsonmode;
//		InputType m = InputType.valueOf(((mode!=null)?mode:"other"));
//		try{
//			switch(m){
//				case list:
//					result = (String)taskService.find_All(request);
//
//					break;
//				case view:
//					result = (String)taskService.findBy_PK(jsonObject);
//
//					break;
//				case update:
//					String taskupdate = (String)taskService.doUpdate(request);
//					result = (taskupdate.equalsIgnoreCase("0"))?"ปรับปรุงข้อมูลสำเร็จ":"ปรับปรุงข้อมูลไม่สำเร็จ";
//
//					break;
//				case add:
//					String taskadd = (String)taskService.doAdd(jsonObject);
//					result = (taskadd.equalsIgnoreCase("0"))?"เพิ่มข้อมูลสำเร็จ":"เพิ่มข้อมูลไม่สำเร็จ";
//
//					break;
//				case delete:
//					String taskdelete = (String)taskService.doDelete(request);
//					result = (taskdelete.equalsIgnoreCase("0"))?"ลบข้อมูลสำเร็จ":"ลบข้อมูลไม่สำเร็จ";
//
//					break;
//				default:
//					break;
//			}
//		}catch(Exception ex){
//			result = "ดำเนินการไม่สำเร็จ";
//			//throw ex;
//		}
//		return result;
//	}*/
//	public String process(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//		String result = null;
//		JSONObject jsonObject  =null;
//		try {
//			String page = (request.getParameter("page") == null) ? null: request.getParameter("page");
//
//			request.setCharacterEncoding( "UTF-8" );
//			response.setContentType("text/html;charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
//			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//			response.setDateHeader("Expires", 0); // prevents caching at the proxy server
//			PrintWriter out = response.getWriter();
//
//			PageType p = PageType.valueOf(((page!=null)?page:"other"));
//			switch(p){
//				case page :
//					InfoPageService formService = (InfoPageService) com.gen.service.SpringApplicationContext.getBean("infoPageService");
//					result = service(formService,request,jsonObject);
//					out.print(result);
//					break;
//				case exam :
//					ExamPageService examService = (ExamPageService) com.gen.service.SpringApplicationContext.getBean("examPageService");
//					result = service(examService,request,jsonObject);
//					out.print(result);
//					break;
//				case answer :
//					AnswerPageService answerService = (AnswerPageService) com.gen.service.SpringApplicationContext.getBean("answerPageService");
//					result = service(answerService,request,jsonObject);
//					out.print(result);
//					break;
//				default:
//					break;
//			}
//		} catch (Exception ex) {
//			logger.error(ex.getMessage(), ex);
//		}
//		return result;
//	}
//}
