//package com.page.service;
//
//import com.gen.service.BaseService;
//import com.gen.util.Utils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//
//@Component("answerPageService")
//public class AnswerPageService extends BaseService{
//
//	private String checkAnswer(String userAnswer,String correctAnswer) throws Exception {
//		JSONObject rightAnswer = new JSONObject(correctAnswer);
//		JSONObject yourAnswer = new JSONObject(userAnswer);
//		JSONObject result = new JSONObject();
//		int right  =0;
//		int wrong = 0;
//		if(yourAnswer.getString("groupid").equalsIgnoreCase(rightAnswer.getString("groupid"))){
//			  JSONArray yourArray = yourAnswer.getJSONArray("answers");
//			  JSONArray rightArray = rightAnswer.getJSONArray("answers");
//			  for (int i = 0; i < yourArray.length(); i++){
//				  String yourChoice = yourArray.getJSONObject(i).getString("value");
//				  String yourQuestionNo = yourArray.getJSONObject(i).getString("name");
//				  boolean isCorrect = false;
//				  for (int j = 0; j < rightArray.length(); j++){
//					  String rightChoice = rightArray.getJSONObject(i).getString("value");
//					  String questionNo = rightArray.getJSONObject(i).getString("name");
//					  if(questionNo.equalsIgnoreCase(yourQuestionNo)&& rightChoice.equalsIgnoreCase(yourChoice)){
//						  isCorrect = true;
//						  break;
//					  }
//
//				  }
//				  if(isCorrect)
//					  right++;
//				  else
//					  wrong++;
//
//			  }
//			  result.put("right", right);
//			  result.put("wrong", wrong);
//
//		}
//		return result.toString();
//	}
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
//			if (rs.next()) {
//				JSONObject row = new JSONObject();
//
//				row.put("groupid",rs.getString("groupid"));
//				Blob blob = rs.getBlob("answer");
//				byte[] bdata = blob.getBytes(1, (int) blob.length());
//				String s = new String(bdata,"utf-8");
//
//				//checkAnswer(bean.getString("content"),s);
//
//				row.put("result",checkAnswer(bean.getString("content"),s));
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
//	public Object add(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
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
//			String sql = "update pages_question set " + "answer = ?, "
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
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object findAll(Object object) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
