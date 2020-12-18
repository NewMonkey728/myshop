package com.qf.myshop.common;

/**
 * 常量类
 */
public interface Const {
    //ctrl+shift+x  大写/小写
    //servlet使用的常量
    String FORWARD = "forward:";
    String REDIRECT = "redirect:";
    String COLON = ":";

    /**
     * 内部内 接口 专门处理用户相关的常量
     */
    interface UserConst {
        //激活
        int IS_ACTIVE = 1;
        //未激活
        int NOT_ACTIVE = 0;
        //管理员
        int IS_ADMIN = 1;
        //普通用户
        int NOT_ADMIN = 0;
        //验证码
        String VALI_CODE="vali_code";
        //用户登录信息
        String IS_LOGIN="loginUser";
        //用户的自动登录
        String AUTO_LOGIN="auto_login";
    }
    interface OderConst{
        //设置是否默认地址  初始值为0  非默认
        int DEFAULT_NOT_ADDRESS=0;
        //设置默认地址
        int DEFAULT_ADDRESS=1;

        //订单状态

        int ORDER_NOT_PAY=0;// 0 未付款，
        int ORDER_PAY=1;// 1已经付款未发货
        int ORDER_SEND=2;// 2发货待收货
        int ORDER_REC=3;// 3 收货待评价
        int ORDER_CLOSE=4;// 4订单完成
        int ORDER_RETURN=5;// 5 退货状态

    }

    //枚举订单状态
    enum OrderCode{
        ORDER_NOT_PAY(0,"未付款"),
        ORDER_PAY(1,"已经付款未发货"),
        ORDER_SEND(2,"发货待收货"),
        ORDER_REC(3,"收货待评价"),
        ORDER_CLOSE(4,"订单完成"),
        ORDER_RETURN(5,"退货状态");

        private int code;
        private String comment;

        OrderCode() {
        }

        OrderCode(int code, String comment) {
            this.code = code;
            this.comment = comment;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
