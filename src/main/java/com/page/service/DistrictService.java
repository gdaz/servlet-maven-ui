//package com.page.service;
//
//import com.gen.service.BaseService;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component("districtService")
//public class DistrictService extends BaseService {
//
//	@Override
//	public Object findByPK(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		String result ="";
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		//String jsonstring = ((org.json.simple.JSONObject)object).toJSONString();
//		JSONObject bean = (JSONObject)object;
//		JSONArray detail = new JSONArray();
//
//		try {
//			//pstmt = conn.prepareStatement("SELECT DISTRICT_CODE,DISTRICT_NAME FROM district WHERE AMPHUR_ID  = ? ");
//			pstmt = conn.prepareStatement("SELECT SDI_CODE, SDI_NAME FROM ref_subdistrict WHERE sdi_code LIKE ? ");
//
//			pstmt.setString(1, bean.getString("amphur_id")+"%");
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("id",rs.getString("SDI_CODE"));
//
//				row.put("name",rs.getString("SDI_NAME"));
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
//
