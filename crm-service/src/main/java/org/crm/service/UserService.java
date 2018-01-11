package org.crm.service;

import org.crm.dao.User;
import org.po.ResultInfo;
import org.po.Tb_user;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static ResultInfo<List<Tb_user>> query(String sql, Object[] objects) {
        ResultInfo<List<Tb_user>> ri = new ResultInfo();
        List<Tb_user> res = User.query(sql, objects);

        if (res == null) {
            ri.setStatus(-1);
            ri.setMsg("系统异常");
        }
        if (res.size() > 0) {
            ri.setStatus(1);
            ri.setMsg("用户名已存在");
            ri.setT(res);
        } else {
            ri.setStatus(0);
            ri.setMsg("用户名或密码错误");
            ri.setT(res);
        }
        return ri;
    }

    public static ResultInfo<List<Tb_user>> update(String sql, Object[] objects) {
        ResultInfo<List<Tb_user>> ri = new ResultInfo<>();
        try {
            int res = User.update(sql, objects);

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
            ri.setStatus(-1);
            ri.setMsg("系统异常" + e.getMessage());
            ri.setT(null);
        }
        return ri;
    }

}