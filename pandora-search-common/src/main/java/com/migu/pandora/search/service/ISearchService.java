package com.migu.pandora.search.service;

import com.migu.pandora.search.bean.SearchReq;
import com.migu.pandora.search.bean.SearchResp;
import com.migu.pandora.search.exception.SystemException;
import org.springframework.validation.BindingResult;

/**
 * Created by piguangtao on 28/2/18.
 */
public interface ISearchService {
    SearchResp search(SearchReq searchReq, BindingResult bindingResult) throws SystemException;

}
