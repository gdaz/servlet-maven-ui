package com.page.service;

import com.service.BaseService;
import com.util.GenerateBCryptPasswordUtil;
import com.util.Utils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Component("registerUserService")
public class RegisterUserService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(RegisterUserService.class);

    @Override
    public Object findByPK(Object object) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object add(Object object) throws Exception {
        // TODO Auto-generated method stub
        logger.debug("RegisterUserService add {} ", object);

        StringBuilder sqlStatementAuthInfo = new StringBuilder();
        sqlStatementAuthInfo.append("insert into authmuserinfo(userid, changestamp, name, password, status, surname");
        sqlStatementAuthInfo.append(", updatedate, usercode, branchcode, companycode, email, position)");
        sqlStatementAuthInfo.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        String result = "";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlStatementAuthInfo.toString())) {
            JSONObject bean = (JSONObject) object;
            Timestamp toDate = Utils.getCurrentTimeStamp();

            preparedStatement.setString(1, bean.getString("userID").trim());
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, bean.getString("userName").trim());
            preparedStatement.setString(4, GenerateBCryptPasswordUtil.generateJdbcBCryptPassword(bean.getString("password").trim()));
            preparedStatement.setString(5, "A");
            preparedStatement.setString(6, bean.getString("userSurname").trim());
            preparedStatement.setTimestamp(7, toDate);
            preparedStatement.setString(8, bean.getString("userID").trim());
            preparedStatement.setString(9, bean.getString("branchID"));
            preparedStatement.setString(10, bean.getString("companyID"));
            preparedStatement.setString(11, bean.getString("email").trim());
            preparedStatement.setString(12, bean.getString("position"));
            preparedStatement.executeUpdate();

            String[] userGroupSelected = (String[]) bean.get("userGroupSelected");
            String[] userGroupID = (String[]) bean.get("userGroupID");
            for (int i = 0; i < userGroupSelected.length; i++) {
                if (userGroupSelected[i].equalsIgnoreCase("true")) {
                    StringBuilder sqlStatementAuthInfoDebt = new StringBuilder();
                    sqlStatementAuthInfoDebt.append("insert into authmuserinfodet(usergroupcode, userid, changestamp, status, updatedate, usercode)");
                    sqlStatementAuthInfoDebt.append("values(?,?,?,?,?,?)");

                    try (PreparedStatement preparedStatement2 = conn.prepareStatement(sqlStatementAuthInfoDebt.toString())) {
                        preparedStatement2.setString(1, userGroupID[i]);
                        preparedStatement2.setString(2, bean.getString("userID").trim());
                        preparedStatement2.setInt(3, 1);
                        preparedStatement2.setString(4, "A");
                        preparedStatement2.setTimestamp(5, toDate);
                        preparedStatement2.setString(6, bean.getString("userID").trim());
                        result = (preparedStatement2.executeUpdate() > 0) ? "0" : "-1";
                    }

                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
            throw e;
        }

        return result;
    }

    @Override
    public Object update(Object object) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object delete(Object object) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object view(Object object) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object findAll(Object object) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
