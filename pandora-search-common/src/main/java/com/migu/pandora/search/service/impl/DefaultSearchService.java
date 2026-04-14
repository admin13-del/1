package com.migu.pandora.search.service.impl;

import com.migu.pandora.search.bean.SearchReq;
import com.migu.pandora.search.bean.SearchResp;
import com.migu.pandora.search.service.AbstractSearchService;

/**
 * Created by piguangtao on 28/2/18.
 */
public class DefaultSearchService extends AbstractSearchService {
    @Override
    public Boolean filterByShields(SearchReq searchReq) {
        return Boolean.FALSE;
    }

    @Override
    public SearchResp search(SearchReq searchReq) {
        return null;
    }
}
