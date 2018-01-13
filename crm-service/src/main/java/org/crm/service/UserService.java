package org.crm.service;

import org.crm.dao.UserDao;
import org.po.ResultInfo;
import org.po.Web_User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static ResultInfo<List<Web_User>> query(String sql, Object[] objects) {
        ResultInfo<List<Web_User>> ri = new ResultInfo();
        List<Web_User> res = null;
        try {
            res = UserDao.query(sql, objects);
            if (res.size() > 0) {
                ri.setStatus(1);
                ri.setMsg("用户名已存在");
                ri.setT(res);
            } else {
                ri.setStatus(0);
                ri.setMsg("用户名或密码错误");
                ri.setT(res);
            }
        } catch (SQLException e) {
            ri.setStatus(-1);
            ri.setMsg("系统异常"+e.getMessage());
            System.out.println(e.getMessage()+e.getStackTrace());
        }
        return ri;
    }

    public static ResultInfo<List<Web_User>> update(String sql, Object[] objects) {
        ResultInfo<List<Web_User>> ri = new ResultInfo<>();
        try {
            int res = UserDao.update(sql, objects);

            if (res < 1) {
                ri.setStatus(0);
                ri.setMsg("注册失败");
                ri.setT(null);
            } else {
                ri.setStatus(1);
                ri.setMsg("注册成功");
                ri.setT(null);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage()+e.getStackTrace());
            ri.setStatus(-1);
            ri.setMsg("系统异常" + e.getMessage());
            ri.setT(null);
        }
        return ri;
    }

}