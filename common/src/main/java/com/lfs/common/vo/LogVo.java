package com.lfs.common.vo;

import com.lfs.common.util.JsonUtils;

/**
 * LogVo
 *
 * @author fushenliao
 * @version 1.0.0
 * @project im-callrecord
 * @create 2022/6/7
 * @modify 2022/6/7
 */
public class LogVo {
    private int limit = 0;
    private Object object;

    public static LogVo newNoLimit(Object o) {
        LogVo ans = new LogVo();
        ans.object = o;
        return ans;
    }

    public static LogVo newLimit(Object o, Integer limit) {
        LogVo ans = new LogVo();
        ans.limit = limit == null ? 0 : limit;
        ans.object = o;
        return ans;
    }

    public static LogVo newLimit200(Object o) {
        LogVo ans = new LogVo();
        ans.limit = 200;
        ans.object = o;
        return ans;
    }

    public static LogVo newLimit100(Object o) {
        LogVo ans = new LogVo();
        ans.limit = 100;
        ans.object = o;
        return ans;
    }

    @Override
    public String toString() {
        String string = "";
        try {
            if (object == null) {
                string = "NULL";
            } else if (object instanceof Number || object instanceof String) {
                string = object.toString();
            } else {
                string = JsonUtils.toStr(object);
            }
        } catch (Exception ignore) {
        }
        if (limit > 0 && string != null && string.length() > limit) {
            string = string.substring(0, limit) + "...";
        }
        return string;
    }
}
