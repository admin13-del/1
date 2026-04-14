//package com.migu.pandora.search.trace;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.actuate.trace.TraceProperties;
//import org.springframework.boot.actuate.trace.TraceRepository;
//import org.springframework.boot.actuate.trace.WebRequestTraceFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Created by piguangtao on 3/8/17.
// */
//public class CustomerWebRequestTraceFilter extends WebRequestTraceFilter {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerWebRequestTraceFilter.class);
//
//    /**
//     * Create a new {@link WebRequestTraceFilter} instance.
//     *
//     * @param repository the trace repository
//     * @param properties the trace properties
//     */
//    public CustomerWebRequestTraceFilter(TraceRepository repository, TraceProperties properties) {
//
//        super(repository, properties);
//        if (null != properties) {
//            properties.getInclude().add(TraceProperties.Include.PARAMETERS);
//        }
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        Map<String, Object> trace = getTrace(request);
//        logReq(trace, request);
//
//        if (isContentJson(response.getContentType())) {
//            //打印请求
//            AuthenticationResponseWrapper authenticationResponseWrapper = new AuthenticationResponseWrapper(response);
//
//            super.doFilterInternal(request, authenticationResponseWrapper, filterChain);
//            //打印响应
//            logResp(authenticationResponseWrapper);
//        } else {
//            super.doFilterInternal(request, response, filterChain);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private void logReq(Map<String, Object> reqTrace, HttpServletRequest request) {
//        Object parameters = reqTrace.get("parameters");
//        String strParameters = "";
//        if (null != parameters) {
//            try {
//                Map<String, String[]> reqParameters = (Map<String, String[]>) parameters;
//                StringBuilder sb = new StringBuilder();
//                reqParameters.entrySet().forEach(entry -> {
//                    String key = entry.getKey();
//                    String[] value = entry.getValue();
//                    sb.append(String.format("%s:%s ", key, Arrays.toString(value)));
//                });
//                strParameters = sb.toString();
//            } catch (Exception e) {
//
//            }
//        }
//        LOGGER.info(String.format("url:%s,method:%s,headers:%s,parameters:%s", request.getRequestURI(), request.getMethod(), reqTrace.get("headers"), strParameters));
//
//
//    }
//
//    private void logResp(AuthenticationResponseWrapper response) {
//        try {
//            Map<String, String> responseHeadMap = getResponseHeaders((response));
//            StringBuilder sb = new StringBuilder();
//            if (null != responseHeadMap && responseHeadMap.size() > 0) {
//                responseHeadMap.entrySet().forEach(entry -> sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue())));
//            }
//            if (isContentJson(response.getContentType())) {
//                LOGGER.info(String.format("response=== 【head %s】 【body %s】", sb.toString(), response.getContent()));
//            }
//        } catch (Exception e) {
//            LOGGER.error("fails to parse response", e);
//        }
//
//    }
//
//    private boolean isContentJson(String contentType) {
//        if (StringUtils.isNotBlank(contentType)) {
//            return contentType.startsWith("application/json");
//        }
//        return false;
//    }
//
//
//    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
//        Map<String, String> headers = new LinkedHashMap<String, String>();
//        for (String header : response.getHeaderNames()) {
//            String value = response.getHeader(header);
//            headers.put(header, value);
//        }
//        headers.put("status", "" + response.getStatus());
//        return headers;
//    }
//
//}
