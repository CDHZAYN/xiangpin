package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/map")
public class MapController {

    @GetMapping("/child")
    public ApiResponse getDistrictChild(@RequestHeader Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        String site = "https://apis.map.qq.com";
        String path = "/ws/district/v1/getchildren";
        String param = "id=" + id + "&key=W3HBZ-XUHYX-RK74B-75NJY-RZ5RZ-AFBCG";
        String sk = "haalp7YKRC4SsG2UQePvoyGiRWCxqazv";
        String md5Undone = path + "?" + param + sk;
        String md5 = null;
        md5 = DigestUtils.md5DigestAsHex(md5Undone.getBytes());
        String website = site + path + "?" + param + "&sig=" + md5;
        System.out.println(website);
        String s = restTemplate.getForObject(website, String.class);
        return ApiResponse.ok(s);
    }
}
