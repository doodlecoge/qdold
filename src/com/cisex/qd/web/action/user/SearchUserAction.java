package com.cisex.qd.web.action.user;

//import com.sun.deploy.net.HttpRequest;
import com.cisex.qd.vo.User;
import com.cisex.qd.web.action.ApiAction;

import com.cisex.qd.web.api.ApiResult;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-9-10
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 */
public class SearchUserAction extends ApiAction {
    private List<User> users;

    @Override
    protected ApiResult executeApi() {
        HttpServletRequest req = ServletActionContext.getRequest();
        String term = req.getParameter("term");

        users = this.dao.getUserDao().searchUser(term);


        HttpServletResponse resp = ServletActionContext.getResponse();
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int len = users.size();

        out.write("[");

        for(int i = 0; i < len; i++) {
            if(i != 0) out.write(",");
            out.write("{");
            out.write("\"username\":\"" + users.get(i).getUsername() + "\",");
            out.write("\"fullname\":\"" + users.get(i).getFullname() + "\",");
            out.write("\"id\":" + users.get(i).getId() + "");
            out.write("}");
        }

        out.write("]");
        out.close();

        return ApiResult.SUCCESS;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
