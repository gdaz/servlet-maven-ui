//package com.page.servlet;
//
//import com.page.service.AmphurService;
//import com.page.service.DistrictService;
//import com.page.service.ProvinceService;
//import com.page.service.TitleService;
//import com.service.BaseService;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//
//public class ProvinceAmphurServlet extends HttpServlet {
//	public static enum InputType {
//		add, delete, update, list, other, menu, system, view
//	}
//
//	public enum PageType {
//		other, province, amphur, district, title
//	}
//
//	private static final Logger logger = LoggerFactory.getLogger(ProvinceAmphurServlet.class);
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		process(request, response);
//
//	}
//
//	public String service(BaseService taskService, HttpServletRequest request, JSONObject jsonObject) throws Exception {
//		String result = "";
//		String jsonMode = (jsonObject == null) ? null : (String) jsonObject.get("mode");
//		String mode = (request.getParameter("mode") == null) ? "" : request.getParameter("mode");
//		mode = (jsonMode == null) ? mode : jsonMode;
//		logger.info("mode {}", mode);
//		InputType m = InputType.valueOf(((mode != null) ? mode : "other"));
//		logger.info("InputType {}", m);
//		try {
//			switch (m) {
//				case list:
//					result = (String) taskService.find_All(request);
//					break;
//				case view:
//					result = (String) taskService.findBy_PK(jsonObject);
//					break;
//				case update:
//					String taskupdate = (String) taskService.doUpdate(jsonObject);
//					result = (taskupdate.equalsIgnoreCase("0")) ? "ปรับปรุงข้อมูลสำเร็จ" : "ปรับปรุงข้อมูลไม่สำเร็จ";
//					break;
//				case add:
//					String taskadd = (String) taskService.doAdd(jsonObject);
//					result = (taskadd.equalsIgnoreCase("0")) ? "เพิ่มข้อมูลสำเร็จ" : "เพิ่มข้อมูลไม่สำเร็จ";
//					break;
//				case delete:
//					String taskdelete = (String) taskService.doDelete(request);
//					result = (taskdelete.equalsIgnoreCase("0")) ? "ลบข้อมูลสำเร็จ" : "ลบข้อมูลไม่สำเร็จ";
//					break;
//				default:
//					break;
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//
//		return result;
//	}
//
//	public JSONObject requestParamsToJSON(ServletRequest req) throws Exception {
//		JSONObject jsonObj = new JSONObject();
//		Map<String, String[]> params = req.getParameterMap();
//		for (Map.Entry<String, String[]> entry : params.entrySet()) {
//			String v[] = entry.getValue();
//			Object o = (v.length == 1) ? v[0] : v;
//			jsonObj.put(entry.getKey(), o);
//		}
//		return jsonObj;
//	}
//
//	private String process(HttpServletRequest request, HttpServletResponse response) {
//		String result = null;
//		String jsonpage = "page";
//		//JSONObject jsonObject  =null;
//		try {
//			String page = (request.getParameter("page") == null) ? null : request.getParameter("page");
//			//InputStream requestInputStream = request.getInputStream();
//			JSONObject jsonObject = requestParamsToJSON(request);
//
//
//			request.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html;charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
//			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//			response.setDateHeader("Expires", 0); // prevents caching at the
//			// proxy server
//			PrintWriter out = response.getWriter();
//			HttpSession httpsession = (HttpSession) request.getSession();
//
//			//String page = (request.getParameter("page") == null) ? null: request.getParameter("page");
//			//page = (jsonpage == null)?page:jsonpage;
//
//			PageType p = PageType.valueOf(((page != null) ? page : "other"));
//			switch (p) {
//				case province:
//					ProvinceService formService = (ProvinceService) com.service.SpringApplicationContext.getBean("provinceService");
//					result = service(formService, request, jsonObject);
//					out.print(result);
//					break;
//				case amphur:
//					AmphurService amphurService = (AmphurService) com.gen.service.SpringApplicationContext.getBean("amphurService");
//					result = service(amphurService, request, jsonObject);
//					out.print(result);
//					break;
//				case district:
//					DistrictService districtService = (DistrictService) com.gen.service.SpringApplicationContext.getBean("districtService");
//					result = service(districtService, request, jsonObject);
//					out.print(result);
//					break;
//				case title:
//					TitleService titleService = (TitleService) com.gen.service.SpringApplicationContext.getBean("titleService");
//					result = service(titleService, request, jsonObject);
//					out.print(result);
//					break;
//				default:
//					break;
//			}
//
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//
//		}
//		return result;
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		process(request, response);
//
//	}
//}
