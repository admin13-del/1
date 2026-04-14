package com.migu.pandora.search.jadeiteapi.bean;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by piguangtao on 7/2/18.
 */
public class JadeiteApiReq {

    /**
     * 按指定条件进行分面查询 时间类型范围查询格式：date:[2009-1-1T0:0:0Z TO 2009-2-1T0:0:0Z] 数值类型范围查询格式：price:[* TO 5000] 特定值查询格式：brand:联想
     * AND price:1100 多个facetQuery用,隔开。比如：date:[2009-1-1T0:0:0Z TO 2009-2-1T0:0:0Z], price:[* TO 5000], brand:联想 AND
     * price:1100
     */
    private String facetQuery;

    /**
     * tie
     */
    private String tie;

    /**
     * 应用id (如统一门户、12350为不同的应用id)
     */
    private String secretId;

    /**
     * 指的是collection
     */
    private String appId;

    /**
     * 用户信息可以是手机号、邮箱、ip等
     */
    private String endUsrInfo;

    /**
     *
     */
    private String queryString;

    /**
     * 默认1，范围1~99999
     */
    private Integer pageIndex;

    /**
     * 默认10，范围：1~200
     */
    private Integer pageNum;

    /**
     * 为空时，默认/select
     */
    private String requestHandler;

    /**
     * 查询域，可带权重 多个字段用空格或,隔开 ➢ 格式：field^x。如：docName^10 docAuthor^6 docDesc^2
     */
    private String queryField;

    /**
     * 查询时返回的字段,如果某字段为空值，将不会返回 ➢ 多个字段用空格或,隔开。格式：docName docAuthor docDesc ➢ 返回字段可别名。格式：字段别名:字段本名。比如：docName:newName
     * docAuthor docDesc
     */
    private String retField;

    /**
     * 数值和时间过滤 ➢ 范围由符号[] {}表示，开区间{}，闭区间[] ➢ 格式：field:[start TO end] ➢ 数字型：field:{0 TO 100] field:[100 TO *] field:[* TO
     * 100] field:{100 TO 200} 等 ➢ 时间型：（待定） 1）具体时间格式：2017-11-30T00:00:00Z 即：xxxx-xx-xxTxx:xx:xxZ 2）其他时间格式： 今天：NOW
     * 一天前：NOW-1DAY 一周前：NOW-7DAY 一月前：NOW-1MONTH 一年前：NOW-1YEAR ➢
     * 多个filter用英文逗号,隔开，被,隔开的filter之间为“且”关系。如果需要添加“或”关系，写在一起并用OR隔开。比如： 1）A字段范围0~100 且 B字段范围1000~1500： A:[0 TO
     * 100],B:[1000 TO 1500] 2）A字段范围0~100或200~300，同时B字段范围1000~1500： A:[0 TO 100] OR A:[200 TO 300],B:[1000 TO 1500]
     */
    // private String numFilter;

    /**
     * 分类过滤 ➢ 格式：field:value ➢ 多个filter用英文逗号,隔开，被,隔开的filter之间为“且”关系。如果需要添加“或”关系，写在一起并用OR隔开。比如： 1）A字段值为a 且 B字段值为b：
     * A:a,B:b 2）A字段值为a或c，同时B字段值为b： A:a OR A:c,B:b
     */
    private String clFilter;

    /**
     * 排序方式 ➢ asc:升序，desc:降序 ➢ 格式：field:asc ➢ 多字段排序英文逗号,隔开，此时第一个字段优先排序，以此类推。如：fieldA:asc,fieldB:desc
     */
    private String sort;

    /**
     * 按指定字段进行分面统计 比如：docType字段的值可能有0,1,2,3，传参时facet的值为docType，那么返回结果中将会呈现满足当前检索条件下，docType=0,1,2,3各有多少个结果  *格式：field
     * 或者 field:mincount  * mincount是指“至少包含多少个结果，才展示该分面”，多个facet用,隔开。比如：A:5,B:10
     * 表示A字段分面结果中，至少有5个以上才返回该分面；B字段的分面结果中，至少有10个以上的才会返回该分面  *不带mincount默认不限制
     */
    private String facetField;

    /**
     * 分类集合 ➢ 按指定字段查询分类集合 比如：docType字段的值可能有0,1,2,3，传参时group的值为docType，那么返回结果中将会呈现满足当前检索条件下，docType=0,1,2,3的分类结果 ➢
     * 格式：field 或者 field:limit ➢ limit是指“返回的集合中，包含多少个结果”，多个group用,隔开。比如：A,B:10 表示A和B字段的分类集合结果中，展示前10个结果 ➢
     * 不带limit，默认limit=1
     */
    private String group;

    /**
     * 提升查询串 ➢ 加强查询，查询串可多值且支持查询权重配置 ➢ 若该字段不为空时，放弃使用query_string，改用该字段值进行查询，但query_string仍然必传，值为用户查询原串 ➢
     * 可带多个搜索词，用空格隔开。比如：咪咕 米谷 ➢ 多词时，可带权重，数值越大权重越高。比如：咪咕^10 米谷^5 ➢ 可带前中后缀匹配，但中后缀较影响性能。比如： 前缀：咪咕* 中缀：*咪咕* 后缀：*咪咕
     * 同时也可以带权重：咪咕*^10 ➢ 带引号，该词不分词搜索，如："咪咕" ➢ 可带查询域，带上后，无需使用queryField，比如：name:咪咕 name:咪咕*
     */
    private String boostQuery;

    /**
     * 提升函数
     */
    private String boostFunc;

    /**
     * 最小匹配数
     */
    private String minMatch;

    /**
     * 是否开启竞价排名 0关闭，1打开，默认是1 ➢ 该功能开启后，可支持竞价排名功能，即特定关键词，置顶指定的文档（一个或多个） ➢ 特定关键词和置顶文档，在统一搜索管理平台上进行配置 ➢
     * 将queryString与配置关键词进行匹配，若匹配成功，置顶指定文档 ➢ 如果添加了filter规则，仅在召回文档中包含配置的置顶文档时，才可置顶
     */
    private int enableElevate = 1;

    /**
     * 是否强制执行竞价排名 0关闭，1打开，默认是1 ➢ 竞价排名功能尊重sorter规则。原则上，根据sorter进行排序后，竞价排名功能失效 ➢
     * 强制执行竞价排名，即：忽视sorter规则，返回结果前几名依然为配置的置顶文档，剩余结果按sorter规则排序
     */
    private int forceElevate = 1;

    /**
     * 是否开启高红标亮 0关闭，1打开，默认是0
     */
    //private int enableHighlight = 0;

    /**
     * 高亮字段 高亮字段配置，多个字段用,隔开。例如：name,desc
     */
    //private String highlightField;

    private int highlightRequireMatch = 0;

    /**
     * 高亮样式 高亮前后缀格式，前缀和后缀之间用,隔开。例如：<font color='red'>,</font>¬
     */
    private String highlightStyle;
    private String phraseBoostField;
    // SortClause使用#分隔，顺序使用:分隔
    private String sortWithFunc;

    private List<String> orginalString;

    private boolean splitFlag = false;

    /**
     * 查询超时配置
     */
    private Integer timeAllowed;
    /**
     * http调用方式
     */
    private String httpMethod;

    private boolean isPageQuery = false;


    /**
     * 提升查询串 ➢ 加强查询，查询串可多值且支持查询权重配置 ➢ 若该字段不为空时，放弃使用query_string，改用该字段值进行查询，但query_string仍然必传，值为用户查询原串 ➢
     * 可带多个搜索词，用空格隔开。比如：咪咕 米谷 ➢ 多词时，可带权重，数值越大权重越高。比如：咪咕^10 米谷^5 ➢ 可带前中后缀匹配，但中后缀较影响性能。比如： 前缀：咪咕* 中缀：*咪咕* 后缀：*咪咕
     * 同时也可以带权重：咪咕*^10 ➢ 带引号，该词不分词搜索，如："咪咕" ➢ 可带查询域，带上后，无需使用queryField，比如：name:咪咕 name:咪咕*
     */
    private List<String> boostQueryList;

    public boolean isPageQuery() {
        return isPageQuery;
    }

    public void setPageQuery(boolean isPageQuery) {
        this.isPageQuery = isPageQuery;
    }

    public boolean isSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(boolean splitFlag) {
        this.splitFlag = splitFlag;
    }

    public List<String> getOrginalString() {
        return orginalString;
    }

    public void setOrginalString(List<String> orginalString) {
        this.orginalString = orginalString;
    }

    public String getSortWithFunc() {
        return sortWithFunc;
    }

    public void setSortWithFunc(String sortWithFunc) {
        this.sortWithFunc = sortWithFunc;
    }

    public String getPhraseBoostField() {
        return phraseBoostField;
    }

    public int getHighlightRequireMatch() {
        return highlightRequireMatch;
    }

    public void setHighlightRequireMatch(int highlightRequireMatch) {
        this.highlightRequireMatch = highlightRequireMatch;
    }

    public void setPhraseBoostField(String phraseBoostField) {
        this.phraseBoostField = phraseBoostField;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEndUsrInfo() {
        return endUsrInfo;
    }

    public void setEndUsrInfo(String endUsrInfo) {
        this.endUsrInfo = endUsrInfo;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(String requestHandler) {
        this.requestHandler = requestHandler;
    }

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public String getRetField() {
        return retField;
    }

    public void setRetField(String retField) {
        this.retField = retField;
    }

    /*
     * public String getNumFilter() { return numFilter; }
     *
     * public void setNumFilter(String numFilter) { this.numFilter = numFilter; }
     */

    public String getClFilter() {
        return clFilter;
    }

    public void setClFilter(String clFilter) {
        this.clFilter = clFilter;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFacetField() {
        return facetField;
    }

    public void setFacetField(String facetField) {
        this.facetField = facetField;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBoostQuery() {
        return boostQuery;
    }

    public void setBoostQuery(String boostQuery) {
        this.boostQuery = boostQuery;
    }

    public String getBoostFunc() {
        return boostFunc;
    }

    public void setBoostFunc(String boostFunc) {
        this.boostFunc = boostFunc;
    }

    public String getMinMatch() {
        return minMatch;
    }

    public void setMinMatch(String minMatch) {
        this.minMatch = minMatch;
    }

    public int getEnableElevate() {
        return enableElevate;
    }

    public void setEnableElevate(int enableElevate) {
        this.enableElevate = enableElevate;
    }

    public int getForceElevate() {
        return forceElevate;
    }

    public void setForceElevate(int forceElevate) {
        this.forceElevate = forceElevate;
    }

//    public int getEnableHighlight() {
//        return enableHighlight;
//    }
//
//    public void setEnableHighlight(int enableHighlight) {
//        this.enableHighlight = enableHighlight;
//    }
//
//    public String getHighlightField() {
//        return highlightField;
//    }
//
//    public void setHighlightField(String highlightField) {
//        this.highlightField = highlightField;
//    }

//    public String getHighlightStyle() {
//        return highlightStyle;
//    }
//
//    public void setHighlightStyle(String highlightStyle) {
//        this.highlightStyle = highlightStyle;
//    }

    public String getTie() {
        return tie;
    }

    public void setTie(String tie) {
        this.tie = tie;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public List<String> getBoostQueryList() {
        return boostQueryList;
    }

    public void setBoostQueryList(List<String> boostQueryList) {
        this.boostQueryList = boostQueryList;
    }

    @Override
    public String toString() {
        return "JadeiteApiReq [facetQuery=" + facetQuery + ", tie=" + tie + ", secretId=" + secretId + ", appId="
                + appId + ", endUsrInfo=" + endUsrInfo + ", queryString=" + queryString + ", pageIndex=" + pageIndex
                + ", pageNum=" + pageNum + ", requestHandler=" + requestHandler + ", queryField=" + queryField
                + ", retField=" + retField + ", clFilter=" + clFilter + ", sort=" + sort + ",sortWithFunc="
                + sortWithFunc + ", facetField=" + facetField + ", group=" + group + ", boostQuery=" + boostQuery
                + ", boostFunc=" + boostFunc + ", minMatch=" + minMatch + ", enableElevate=" + enableElevate
                + ", forceElevate=" + forceElevate + ", highlightStyle=" + highlightStyle + ",phraseBoostField=" + phraseBoostField
                + ",timeAllowed=" + timeAllowed + ", orginalString="+ ((orginalString == null || orginalString.isEmpty()) ? null : StringUtils.join(orginalString, " OR "))
                + ", boostQueryList="+ ((boostQueryList == null || boostQueryList.isEmpty()) ? null : StringUtils.join(boostQueryList, " OR "))+"]";
    }

    public JadeiteApiReq() {
    }

    public JadeiteApiReq(String secretId, String appId, String endUsrInfo, String queryString, Integer pageIndex,
            Integer pageNum, String requestHandler, String queryField, String retField, String numFilter,
            String clFilter, String sort, String facetField, String group, String boostQuery, String boostFunc,
            String minMatch, int enableElevate, int forceElevate, int enableHighlight, String highlightField,
            String highlightStyle, String tie, int facetMinCount, String facetQuery) {
        this.secretId = secretId;
        this.appId = appId;
        this.endUsrInfo = endUsrInfo;
        this.queryString = queryString;
        this.pageIndex = pageIndex;
        this.pageNum = pageNum;
        this.requestHandler = requestHandler;
        this.queryField = queryField;
        this.retField = retField;
        // this.numFilter = numFilter;
        this.clFilter = clFilter;
        this.sort = sort;
        this.facetField = facetField;
        this.group = group;
        this.boostQuery = boostQuery;
        this.boostFunc = boostFunc;
        this.minMatch = minMatch;
        this.enableElevate = enableElevate;
        this.forceElevate = forceElevate;
//        this.enableHighlight = enableHighlight;
//        this.highlightField = highlightField;
        this.highlightStyle = highlightStyle;
        this.tie = tie;
        this.facetQuery = facetQuery;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String secretId;
        private String appId;
        private String endUsrInfo;
        private String queryString;
        private Integer pageIndex;
        private Integer pageNum;
        private String requestHandler;
        private String queryField;
        private String retField;
        private String numFilter;
        private String clFilter;
        private String sort;
        private String facetField;
        private String group;
        private String boostQuery;
        private String boostFunc;
        private String minMatch;
        private int enableElevate;
        private int forceElevate;
        private int enableHighlight;
        private String highlightField;
        private String highlightStyle;
        private String tie;
        private int facetMinCount;
        private String facetQuery;

        public Builder setFacetQuery(final String facetQuery) {
            this.facetQuery = facetQuery;
            return this;
        }

        public Builder setFacetMinCount(final int facetMinCount) {
            this.facetMinCount = facetMinCount;
            return this;
        }

        public Builder setTie(final String tie) {
            this.tie = tie;
            return this;
        }

        public Builder setSecretId(final String secretId) {
            this.secretId = secretId;
            return this;
        }

        public Builder setAppId(final String appId) {
            this.appId = appId;
            return this;
        }

        public Builder setEndUsrInfo(final String endUsrInfo) {
            this.endUsrInfo = endUsrInfo;
            return this;
        }

        public Builder setQueryString(final String queryString) {
            this.queryString = queryString;
            return this;
        }

        public Builder setPageIndex(final Integer pageIndex) {
            this.pageIndex = pageIndex;
            return this;
        }

        public Builder setPageNum(final Integer pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public Builder setRequestHandler(final String requestHandler) {
            this.requestHandler = requestHandler;
            return this;
        }

        public Builder setQueryField(final String queryField) {
            this.queryField = queryField;
            return this;
        }

        public Builder setRetField(final String retField) {
            this.retField = retField;
            return this;
        }

        public Builder setNumFilter(final String numFilter) {
            this.numFilter = numFilter;
            return this;
        }

        public Builder setClFilter(final String clFilter) {
            this.clFilter = clFilter;
            return this;
        }

        public Builder setSort(final String sort) {
            this.sort = sort;
            return this;
        }

        public Builder setFacet(final String facetField) {
            this.facetField = facetField;
            return this;
        }

        public Builder setGroup(final String group) {
            this.group = group;
            return this;
        }

        public Builder setBoostQuery(final String boostQuery) {
            this.boostQuery = boostQuery;
            return this;
        }

        public Builder setBoostFunc(final String boostFunc) {
            this.boostFunc = boostFunc;
            return this;
        }

        public Builder setMinMatch(final String minMatch) {
            this.minMatch = minMatch;
            return this;
        }

        public Builder setEnableElevate(final int enableElevate) {
            this.enableElevate = enableElevate;
            return this;
        }

        public Builder setForceElevate(final int forceElevate) {
            this.forceElevate = forceElevate;
            return this;
        }

        public Builder setEnableHighlight(final int enableHighlight) {
            this.enableHighlight = enableHighlight;
            return this;
        }

        public Builder setHighlightField(final String highlightField) {
            this.highlightField = highlightField;
            return this;
        }

        public Builder setHighlightStyle(final String highlightStyle) {
            this.highlightStyle = highlightStyle;
            return this;
        }

        public JadeiteApiReq build() {
            return new JadeiteApiReq(this.secretId, this.appId, this.endUsrInfo, this.queryString, this.pageIndex,
                    this.pageNum, this.requestHandler, this.queryField, this.retField, this.numFilter, this.clFilter,
                    this.sort, this.facetField, this.group, this.boostQuery, this.boostFunc, this.minMatch,
                    this.enableElevate, this.forceElevate, this.enableHighlight, this.highlightField,
                    this.highlightStyle, this.tie, this.facetMinCount, this.facetQuery);
        }
    }

    public void andFilterQuery(String filterQ) {
        if (StringUtils.isNotBlank(filterQ)) {
            if (StringUtils.isNotBlank(clFilter)) {
                if (!clFilter.contains(filterQ)) {
                    clFilter = String.format("%s,%s", clFilter, filterQ);
                }
            } else {
                clFilter = filterQ;
            }
        }
    }

    public void orFilterQuery(String filterQ) {
        if (StringUtils.isNotBlank(filterQ)) {
            if (StringUtils.isNotBlank(clFilter)) {
                if (!clFilter.contains(filterQ)) {
                    clFilter = String.format("%s OR %s", clFilter, filterQ);
                }
            } else {
                clFilter = filterQ;
            }
        }
    }

    public String getFacetQuery() {
        return facetQuery;
    }

    public void setFacetQuery(String facetQuery) {
        this.facetQuery = facetQuery;
    }

    public Integer getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(Integer timeAllowed) {
        this.timeAllowed = timeAllowed;
    }
}
