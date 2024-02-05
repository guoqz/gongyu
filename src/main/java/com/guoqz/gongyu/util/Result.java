package com.guoqz.gongyu.util;

public class Result {
    private Integer code;    //结果码  0表示成功, 100表示用户名为空,101表示用户名已经存在 ,200表示密码为空, 300表示昵称为空
    private String msg;        //结果描述
    private Long count;
    private Object data;


    public Result(Integer code, String msg, Long count, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result() {
        super();
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + "]";
    }


    public static Result ok() {
        return new Result(0, "获取数据成功!");
    }

    public static Result ok(String msg) {
        return new Result(0, msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }
}
