package com.xiangpin.util;

import com.xiangpin.model.vo.WechatHttpApiVO;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpUtil {
    static public WechatHttpApiVO getOpenID(String code) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建URI对象，并且设置请求参数
        URI uri = new URIBuilder("http://www.baidu.com/s")
                .setParameter("appid", "wx4ca42746bf80b0b0")
                .setParameter("secret","2a352f760ae959cf38ee15d6a5920724")
                .setParameter("js_code",code)
                .setParameter("grant_type","authorization_code").build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }JSON.
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
        return new WechatHttpApiVO();
    }
}
