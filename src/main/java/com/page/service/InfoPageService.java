//package com.page.service;
//
//import com.gen.service.BaseService;
//import com.gen.util.Utils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.sql.*;
//
//@Component("infoPageService")
//public class InfoPageService extends BaseService{
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
//			pstmt = conn.prepareStatement("select * from pages where boxid = ? ");
//			pstmt.setString(1, bean.getString("boxid"));
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("boxid",rs.getString("boxid"));
//				Blob blob = rs.getBlob("content");
//				byte[] bdata = blob.getBytes(1, (int) blob.length());
//				String s = new String(bdata,"utf-8");
//				row.put("content",s);
//				row.put("height",rs.getInt("height"));
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
//	boolean isRowExist(String id,String pageid)throws Exception {
//		String sql = "SELECT EXISTS(SELECT 1 FROM pages WHERE boxid =? and pageid=? )";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setString(1,id);
//		pstmt.setString(2,pageid);
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
//			if (!isRowExist(bean.getString("boxid"),bean.getString("pageid"))){
//
//			String sql = "INSERT INTO pages(boxid,content,createddate,createdby,height,pageid) VALUES(?,?,?,?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, Utils.ifBlank(bean.getString("boxid"), "").trim());
//			//pstmt.setString(2, Utils.ifBlank(bean.getString("formname"), "").trim());
//			byte[] bytes = bean.getString("content").getBytes("utf-8");
//			Blob blob = conn.createBlob();
//			blob.setBytes(1, bytes);
//			pstmt.setBlob(2, blob);
//			pstmt.setTimestamp(3, todate);
//			pstmt.setString(4, "admin");
//			pstmt.setInt(5,Integer.parseInt(bean.getString("height")));
//			pstmt.setString(6,  Utils.ifBlank(bean.getString("pageid"), "").trim());
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
//			String sql = "update pages set " + "content = ?, "
//					    + "createddate = ?, createdby = ?, height=? "
//						+ "where boxid = ?";
//
//			pstmt = conn.prepareStatement(sql);
//			//pstmt.setString(1, bean.getString("formdisplay"));
//			byte[] bytes = bean.getString("content").getBytes("utf-8");
//			Blob blob = conn.createBlob();
//			blob.setBytes(1, bytes);
//			pstmt.setBlob(1, blob);
//			pstmt.setTimestamp(2, todate);
//			pstmt.setString(3, "admin");
//			pstmt.setInt(4,Integer.parseInt(bean.getString("height")));
//
//
//
//
//			pstmt.setString(5, Utils.ifBlank(bean.getString("boxid"), "").trim());// bean.getString("mainMenuID")
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
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object findAll(Object object) throws Exception {
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		JSONArray list = new JSONArray();
//		HttpServletRequest request = (HttpServletRequest)object;
//		JSONArray detail = new JSONArray();
//		JSONObject bean = new JSONObject();
//		bean.put("pageid",  request.getParameter("pageid"));
//
//		try {
//			//User user = getUser();
//			HttpSession httpsession = (HttpSession) request.getSession();
//	//		User user = ((User) httpsession.getAttribute("user"));
//			pstmt = conn.prepareStatement("select * from pages where pageid = ? ");
//			pstmt.setString(1, bean.getString("pageid"));
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("boxid",rs.getString("boxid"));
//				Blob blob = rs.getBlob("content");
//				byte[] bdata = blob.getBytes(1, (int) blob.length());
//				String s = new String(bdata,"utf-8");
//				row.put("content",s);
//				row.put("height",rs.getInt("height"));
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
//}
