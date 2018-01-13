package org.po;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


public class ResultInfo<T> {
    private int status;
    private String msg;
    private T t;

    public ResultInfo() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public ResultInfo(int status, String msg, T t) {
        this.status = status;
        this.msg = msg;
        this.t = t;
    }
}
