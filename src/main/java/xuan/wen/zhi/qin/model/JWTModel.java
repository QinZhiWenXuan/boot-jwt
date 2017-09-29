package xuan.wen.zhi.qin.model;

import java.util.HashMap;
import java.util.Map;

public class JWTModel {
    private String loginName;
    private String userName;

    public JWTModel() {
    }

    public JWTModel(String loginName, String userName) {
        this.loginName = loginName;
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, String> asMap() {
        Map<String, String> map = new HashMap<>(2);
        map.put("loginName", this.loginName);
        map.put("userName", this.userName);
        return map;
    }

    @Override
    public String toString() {
        return "JWTModel{" +
                "loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
