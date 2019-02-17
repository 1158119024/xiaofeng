package com.xiaofeng.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @Auther: 晓枫
 * @Date: 2019/1/16 10:55
 * @Description:
 */
public class CrawlerLottery {

    public static void main(String[] args) throws IOException {
        String url="https://55780cp.com/api_digital/LotteryIssue/getHistoryList";
//        String url = "http://www.163.com/";

//        Document docu1= Jsoup.connect(url).get();
//        {
//            "count": 20,
//                "lotteryId": "19",
//                "nonce": "1547608674282-8d8139cd",
//                "timestamp": 1547608674282
//        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", 20);
        map.put("lotteryId", "19");
        map.put("nonce", "1547608674282-8d8139cd");
        map.put("timestamp", 1547608674282L);
//        String result = "";
        String result = doPost(url, map);
        System.out.println(result);
//        System.out.println(1547618877867L - 1547618872792L);
        JSONObject jsonObject = JSON.parseObject(result);
        Object data = jsonObject.get("data");
        System.out.println(data);
        JSONArray jsonArray = JSON.parseArray(data.toString());
        for ( int i = 0; i<jsonArray.size(); i++ ) {
            System.out.println(jsonArray.get(i));
        }
        System.out.println(jsonArray.size());
//        System.out.println(docu1);
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Sign", "5a04ed09ece65e4829056b53190932f7");
        httpPost.addHeader("Content-Type", "application/json");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            // 通过map集成entrySet方法获取entity
//            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
//            // 循环遍历，获取迭代器
//            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, Object> mapEntry = iterator.next();
//                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
//            }

            // 为httpPost设置封装好的请求参数
            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                System.out.println(JSON.toJSON(paramMap).toString());
                httpPost.setEntity(new StringEntity(JSON.toJSON(paramMap).toString()));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}

