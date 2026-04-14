package com.migu.pandora.search.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.migu.pandora.search.bean.SearchReq;
import com.migu.pandora.search.bean.SearchResp;
import com.migu.pandora.search.constant.HttpConstant;
import com.migu.pandora.search.exception.SystemException;

/**
 * Created by piguangtao on 28/2/18.
 */
public abstract class AbstractSearchService implements ISearchService {

    /**
     * 搜索流程类
     *
     * @param searchReq
     * @param bindingResult
     * @return
     * @throws SystemException
     */
    public SearchResp search(SearchReq searchReq, BindingResult bindingResult) throws SystemException {

        // 搜索参数检验
        if (null != bindingResult && bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(fieldError.getDefaultMessage());
                i++;
            }
            throw new SystemException(HttpConstant.RESP_STATUS_CODE_WRONG_PARAMETER, sb.toString());
        }

        // 屏蔽词校验
        if (filterByShields(searchReq)) {
            throw new SystemException(HttpConstant.RESP_STATUS_CODE_SUCCESS, "shield");
        }

        // 执行搜索
        SearchResp searchDataResp = search(searchReq);

        return searchDataResp;
    }

    public abstract Boolean filterByShields(SearchReq searchReq);

    public abstract SearchResp search(SearchReq searchReq);

}
