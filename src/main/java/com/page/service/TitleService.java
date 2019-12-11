//package com.page.service;
//
//import com.gen.service.BaseService;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component("titleService")
//public class TitleService extends BaseService {
//
//	private static final Logger logger = LoggerFactory.getLogger(TitleService.class);
//
//	@Override
//	public Object findByPK(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object add(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object update(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
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
//		// TODO Auto-generated method stub
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		JSONArray detail = new JSONArray();
//
//		try {
////			pstmt = conn.prepareStatement("SELECT title_id,title_long FROM thai_title ");
//			pstmt = conn.prepareStatement("SELECT ttl_code,ttl_name FROM ref_title ");
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//				row.put("id",rs.getString("ttl_code"));
//				row.put("name",rs.getString("ttl_name"));
//				detail.put(row);
//			}
//			result = detail.toString();
//			logger.info("{}", result);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new RuntimeException();
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException ex) {
//				logger.error(ex.getMessage(), ex);
//			}
//		}
//		return result;
//	}
//
//}
