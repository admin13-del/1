package com.migu.pandora.search.constant;

/**
 * Created by piguangtao on 23/1/18.
 */
public interface HttpConstant {
    /**
     * 请求签名
     * 由统一搜索平台上申请的标识身份的appId和appKey生成。
     */
    String REQ_HEAD_SIGNATURE = "signature";

    /**
     * 当前时间
     * 当前时间戳（毫秒级） 如：
     * 1511924097000
     */
    String REQ_HEAD_TIMESTAMP = "timeStamp";


    /**
     * 成功
     */
    String RESP_STATUS_CODE_SUCCESS = "0";

    /**
     * 鉴权失败
     */
    String RESP_STATUS_CODE_FIAL_AUTH = "01401";

    /**
     * 参数校验失败
     */
    String RESP_STATUS_CODE_WRONG_PARAMETER = "01402";


}
