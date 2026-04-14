package com.migu.pandora.search.exception;

/**
 * Created by piguangtao on 28/2/18.
 */
public class SystemException extends Exception {
    private static final long serialVersionUID = 5325606754390600884L;
    private String code;
    private String desc;

    public SystemException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }


}
