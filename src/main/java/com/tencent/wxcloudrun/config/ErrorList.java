package com.tencent.wxcloudrun.config;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorList {
    public static HashMap<String, String> errorList = new HashMap<String, String>() {
        {
            put("00001", "Error occurred when select a certain user.");
            put("00002", "User not found.");
            put("00003", "Error occurred when register.");
            put("00004", "This user has registered.");
        }
    };
}
