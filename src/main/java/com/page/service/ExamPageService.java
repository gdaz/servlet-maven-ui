//package com.page.service;
//
//import com.service.BaseService;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//
//@Component("examPageService")
//public class ExamPageService extends BaseService {
//
//	@Override
//	public Object findByPK(Object object) throws Exception {
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		JSONArray list = new JSONArray();
//
//		String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//		JSONObject bean = new JSONObject(jsonstring);
//	//	HttpServletRequest request = (HttpServletRequest)object;
//		JSONArray detail = new JSONArray();
//	//	JSONObject bean = new JSONObject();
//	//	bean.put("pageid",  request.getParameter("pageid"));
//
//		try {
//			//User user = getUser();
//		//	HttpSession httpsession = (HttpSession) request.getSession();
//	//		User user = ((User) httpsession.getAttribute("user"));
//			pstmt = conn.prepareStatement("select * from pages_question where groupid = ? ");
//			pstmt.setString(1, bean.getString("groupid"));
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("groupid",rs.getString("groupid"));
//				Blob blob = rs.getBlob("question");
//				byte[] bdata = blob.getBytes(1, (int) blob.length());
//				String s = new String(bdata,"utf-8");
//				row.put("content",s);
//				//row.put("height",rs.getInt("height"));
//				detail.put(row);
//
//			}
//			result = detail.toString()	;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
//	boolean isRowExist(String id)throws Exception {
//		String sql = "SELECT EXISTS(SELECT 1 FROM pages_question WHERE groupid =?  )";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1,id);
//		ResultSet rs = pstmt.executeQuery();
//		if (rs.next()) {
//			if(rs.getString(1).equals("1")){
//	            return true;
//	        }else{
//	            return false;
//	        }
//
//		}
//		return false;
//	}
//	@Override
//	public Object add(Object object) throws Exception {
//		String result ="";
//		PreparedStatement pstmt = null;
//		//HttpServletRequest request = (HttpServletRequest)object;
//		try{
//			String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//			JSONObject bean = new JSONObject(jsonstring);//Utils.requestParamsToJSON(request);
//		//	HttpSession httpsession = (HttpSession) request.getSession();
//		//	User user = ((User) httpsession.getAttribute("user"));
//			Timestamp todate = Utils.getCurrentTimeStamp();
//
//			if (!isRowExist(bean.getString("groupid"))){
//
//			String sql = "INSERT INTO pages_question(groupid,question,createddate,createdby,updateddate,updatedby) VALUES(?,?,?,?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Utils.ifBlank(bean.getString("groupid"), "").trim());
//			//pstmt.setString(2, Utils.ifBlank(bean.getString("formname"), "").trim());
//			byte[] bytes = bean.getString("content").getBytes("utf-8");
//			Blob blob = conn.createBlob();
//			blob.setBytes(1, bytes);
//			pstmt.setBlob(2, blob);
//			pstmt.setTimestamp(3, todate);
//			pstmt.setString(4, "admin");
//			pstmt.setTimestamp(5, todate);
//			pstmt.setString(6, "admin");
//			result = (pstmt.executeUpdate() > 0)?"0":"-1";
//			}else{
//				result = (String)update(object);
//			}
//
//
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public Object update(Object object) throws Exception {
//		String result ="";
//		PreparedStatement pstmt = null;
//		//HttpServletRequest request = (HttpServletRequest)object;
//		try{
//			String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//			JSONObject bean = new JSONObject(jsonstring);//Utils.requestParamsToJSON(request);
//			//JSONObject bean = Utils.requestParamsToJSON(request);
//			//HttpSession httpsession = (HttpSession) request.getSession();
//			//User user = ((User) httpsession.getAttribute("user"));
//			Timestamp todate = Utils.getCurrentTimeStamp();
//
//			String sql = "update pages_question set " + "question = ?, "
//					    + "updateddate = ?, updatedby = ? "
//						+ "where groupid = ?";
//
//			pstmt = conn.prepareStatement(sql);
//			//pstmt.setString(1, bean.getString("formdisplay"));
//			byte[] bytes = bean.getString("content").getBytes("utf-8");
//			Blob blob = conn.createBlob();
//			blob.setBytes(1, bytes);
//			pstmt.setBlob(1, blob);
//			pstmt.setTimestamp(2, todate);
//			pstmt.setString(3, "admin");
//		//	pstmt.setInt(4,Integer.parseInt(bean.getString("height")));
//
//
//			pstmt.setString(4, Utils.ifBlank(bean.getString("groupid"), "").trim());// bean.getString("mainMenuID")
//
//
//
//
//			result = (pstmt.executeUpdate() > 0)?"0":"-1";
//
//
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public Object delete(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object view(Object object) throws Exception {
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		JSONArray list = new JSONArray();
//
//		String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//		JSONObject bean = new JSONObject(jsonstring);
//	//	HttpServletRequest request = (HttpServletRequest)object;
//		JSONArray detail = new JSONArray();
//	//	JSONObject bean = new JSONObject();
//	//	bean.put("pageid",  request.getParameter("pageid"));
//
//		try {
//			//User user = getUser();
//		//	HttpSession httpsession = (HttpSession) request.getSession();
//	//		User user = ((User) httpsession.getAttribute("user"));
//			pstmt = conn.prepareStatement("select * from pages_question where groupid = ? ");
//			pstmt.setString(1, bean.getString("groupid"));
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("groupid",rs.getString("groupid"));
//				Blob blob = rs.getBlob("content");
//				byte[] bdata = blob.getBytes(1, (int) blob.length());
//				String s = new String(bdata,"utf-8");
//				row.put("content",s);
//				//row.put("height",rs.getInt("height"));
//				detail.put(row);
//
//			}
//			result = detail.toString()	;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public Object findAll(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
