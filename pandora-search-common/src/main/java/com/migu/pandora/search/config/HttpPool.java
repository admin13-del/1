package com.migu.pandora.search.config;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpPool.class);

    @Value("${httpClient.connectionsMax}")
    private int connectionsMax;
    @Value("${httpClient.connectionsMaxPerPost}")
    private int connectionsMaxPerPost;
    @Value("${httpClient.connectionReqTimeout}")
    private int connectionReqTimeout;
    @Value("${httpClient.connectTimeout}")
    private int connectTimeout;
    @Value("${httpClient.socketTimeout}")
    private int socketTimeout;

    @Bean
    public HttpClient httpClient() {
    	return this.httpClient0(socketTimeout, connectionReqTimeout, connectTimeout, connectionsMax, connectionsMaxPerPost);
    }

    private HttpClient httpClient0(int socketTimeout, int connectionReqTimeout, int connectTimeout, int connectionsMax, int connectionsMaxPerPost) {
        LOGGER.info("init httpclient configuration ");
        // 生成默认请求配置
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // 超时时间
        requestConfigBuilder.setSocketTimeout(socketTimeout);
        requestConfigBuilder.setConnectionRequestTimeout(connectionReqTimeout);
        // 连接时间
        requestConfigBuilder.setConnectTimeout(connectTimeout);
        RequestConfig defaultRequestConfig = requestConfigBuilder.build();
        // 连接池配置
        // 长连接保持30秒
        final PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager
                (60, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(connectionsMax);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(connectionsMaxPerPost);

        // httpclient 配置
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
        HttpClient client = httpClientBuilder.build();


        // 启动定时器，定时回收过期的连接
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    pollingConnectionManager.closeExpiredConnections();
                    pollingConnectionManager.closeIdleConnections(30, TimeUnit.SECONDS);
                } catch (Exception e) {
                    LOGGER.error("fails to close invalid collection", e);
                }
            }
        }, 10 * 1000, 5 * 1000);
        return client;
    }


}