/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aliyun.api.gateway.demo;

import com.alibaba.fastjson.JSON;
import com.aliyun.api.gateway.demo.constant.*;
import com.aliyun.api.gateway.demo.enums.Method;
import org.junit.Test;

import java.util.*;

/**
 * 调用示例
 * 请替换APP_KEY,APP_SECRET,HOST,CUSTOM_HEADERS_TO_SIGN_PREFIX为真实配置
 */
public class Demo {

    //APP KEY
    private final static String APP_KEY = "";
    // APP密钥
    private final static String APP_SECRET = "";
    //API域名
    private final static String HOST = "api.xubei.com";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    @Test
    public void test() throws Exception {
        //请求path
        String path = "/b/arbitration/findArbitrationItem";

        Map<String, String> headers = new HashMap<String, String>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, "application/json; charset=UTF-8");

        CUSTOM_HEADERS_TO_SIGN_PREFIX.clear();

        Request request = new Request(Method.POST_FORM, HttpSchema.HTTP + HOST, path, APP_KEY, APP_SECRET, Constants.DEFAULT_TIMEOUT);
        request.setHeaders(headers);
        request.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);

        //请求的query
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("userId", ""); // 有登录token，就填写登录token，没有就写商户号
        querys.put("businessNo", ""); // 商户号
        querys.put("orderNo", "0935462870131732242685D"); // 订单号
        request.setQuerys(querys);

        //调用服务端
        Response response = Client.execute(request);

        System.out.println(JSON.toJSONString(response));
    }

}
