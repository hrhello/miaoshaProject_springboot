package com.miaoshaProject.response;

public class CommonReturnType {
    //表明对应请求的返回处理结果"success"或者"error"
    private String staus;
    //若staus等于success,则data内返回前端需要的json数据
    private Object data;

    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String staus){
        CommonReturnType type = new CommonReturnType();
        type.setStaus(staus);
        type.setData(result);
        return type;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
