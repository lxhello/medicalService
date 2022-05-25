package com.medical.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: lixiang
 * @date: 2022/5/22
 * @version: 1.0版本
 */
public class DrugsUtils {

    //获取配药信息
    public static String getDrugsInfo(Map map) {
        Iterator entries = map.entrySet().iterator();
        String ids = "";
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            String[] _key = key.split("_");
            if (_key.length > 1) {
                if (_key[1].equals("number") && !value.equals("")) {
                    ids += (_key[0] + "@" + value) + ",";
                }
            }
        }
        ids = ids.substring(0, ids.length() - 1);
        return ids;
    }
}
