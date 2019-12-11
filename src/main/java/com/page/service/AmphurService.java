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
//@Component("amphurService")
//public class AmphurService extends BaseService {
//
//	private static final Logger logger = LoggerFactory.getLogger(AmphurService.class);
//
//	@Override
//	public Object findByPK(Object object) throws Exception {
//
//		logger.info("AmphurService.findByPK");
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		//String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//		JSONObject bean = (JSONObject)object;
//		JSONArray detail = new JSONArray();
//
//		try {
//			//pstmt = conn.prepareStatement("SELECT AMPHUR_ID,AMPHUR_NAME FROM amphur WHERE PROVINCE_ID = ? ");
//			pstmt = conn.prepareStatement("SELECT DIS_CODE, DIS_NAME, DIS_POSTCODE FROM ref_district WHERE dis_code LIKE ? ");
//			pstmt.setString(1, bean.getString("province_id")+"%");
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//				row.put("id",rs.getString("DIS_CODE"));
//				row.put("name",rs.getString("DIS_NAME"));
//				row.put("postcode",rs.getString("DIS_POSTCODE"));
//				detail.put(row);
//			}
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
//	@Override
//	public Object findAll(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
