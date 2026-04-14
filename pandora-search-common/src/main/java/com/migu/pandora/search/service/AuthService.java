package com.migu.pandora.search.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by piguangtao on 24/1/18.
 */
@Service
public class AuthService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Value("${app.auth.excludes}")
    private String excludesAppIdStrs;

    private Set<String> excludesAppIds;

    @Value("${app.auth.timestamp.gap.sec}")
    private Integer timeGapSec;

    @Value("${app.auth.appId.appKeys}")
    private String appIdKeyConfig;

    private Map<String, String> appIdKeyMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!StringUtils.isEmpty(excludesAppIdStrs)) {
            excludesAppIds = new HashSet<>();
            Collections.addAll(excludesAppIds, excludesAppIdStrs.split(","));
        }

        if (!StringUtils.isEmpty(appIdKeyConfig)) {
            Stream.of(appIdKeyConfig.split(",")).forEach(single -> {
                String[] appIdKey = single.split("~");
                if (appIdKey.length == 2) {
                    appIdKeyMap.put(appIdKey[0], appIdKey[1]);
                } else {
                    LOGGER.warn("[app auth]init. wrong config.{}", single);
                }
            });
        }
    }

    public boolean appAuth(String appId, String signature, Long reqTime) {
        if (StringUtils.isEmpty(appId)) {
            return false;
        }
        if (null != excludesAppIds && excludesAppIds.size() > 0) {
            if (excludesAppIds.contains(appId)) {
                return true;

            }
        }

        if (null == reqTime || StringUtils.isEmpty(signature)) {
            LOGGER.warn("[app auth] . reqTime:{},signature:{} can't be empty", reqTime, signature);
            return false;
        }

        if (Math.abs(System.currentTimeMillis() - reqTime) > timeGapSec * 1000) {
            LOGGER.warn("[app auth] reqTime gap is too big. reqTime:{},timeGapSec:{}", reqTime, timeGapSec);
            return false;
        }

        String appKey = appIdKeyMap.get(appId);
        if (StringUtils.isEmpty(appId)) {
            LOGGER.warn("[app auth] appId:{} has not appKey. config:{}", appId, appIdKeyConfig);
            return false;
        }

        String expectedSignature = getSignature(appId, appKey, reqTime);
        if (!expectedSignature.equalsIgnoreCase(signature)) {
            LOGGER.warn("[app auth]signature:{},expectedSignature:{} not same.", signature, expectedSignature);
            return false;
        }

        return true;
    }

    public String getSignature(String appId, String appKey, Long timeStamp) {
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appKey) || null == timeStamp) {
            return "";
        }
        return DigestUtils.md5Hex(String.format("%s%s%s", appId, appKey, String.valueOf(timeStamp)).getBytes
                (StandardCharsets.UTF_8)).toLowerCase();
    }

    public String getExcludesAppIdStrs() {
        return excludesAppIdStrs;
    }

    public void setExcludesAppIdStrs(String excludesAppIdStrs) {
        this.excludesAppIdStrs = excludesAppIdStrs;
    }

    public Integer getTimeGapSec() {
        return timeGapSec;
    }

    public void setTimeGapSec(Integer timeGapSec) {
        this.timeGapSec = timeGapSec;
    }

    public String getAppIdKeyConfig() {
        return appIdKeyConfig;
    }

    public void setAppIdKeyConfig(String appIdKeyConfig) {
        this.appIdKeyConfig = appIdKeyConfig;
    }

    public String getAppKey(String appId) {
        if (StringUtils.isEmpty(appId)) {
            return null;
        } else {
            return appIdKeyMap.get(appId);
        }
    }

    public Map<String, String> getAppIdKeyMap() {
        return appIdKeyMap;
    }
}
