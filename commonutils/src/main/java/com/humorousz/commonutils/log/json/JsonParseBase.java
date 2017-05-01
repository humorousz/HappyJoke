package com.humorousz.commonutils.log.json;

import org.json.JSONObject;

/**
 * Created by zhangzhiquan on 2017/5/1.
 */

public abstract class JsonParseBase {
    public abstract <T> T parse(JSONObject jsonObj,Class<T> cls);
}
