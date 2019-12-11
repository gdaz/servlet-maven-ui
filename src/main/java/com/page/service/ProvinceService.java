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
//@Component("provinceService")
//public class ProvinceService extends BaseService {
//
//	private static final Logger logger = LoggerFactory.getLogger(ProvinceService.class);
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
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		JSONArray detail = new JSONArray();
//		try {
//			//pstmt = conn.prepareStatement("SELECT PROVINCE_ID,PROVINCE_NAME FROM province ");
//			pstmt = conn.prepareStatement("SELECT PRV_CODE,PRV_NAME FROM ref_province ");
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//				row.put("id",rs.getString("PRV_CODE"));
//				row.put("name",rs.getString("PRV_NAME"));
//				detail.put(row);
//			}
//
//			logger.info("{}", result);
//			result = detail.toString()	;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw e;
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
