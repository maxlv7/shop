package org.maxlv.shop.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest req, String key) {
        try {
            return Integer.decode(req.getParameter(key));
        } catch (Exception e) {
            return -1;
        }

    }

    public static Long getLong(HttpServletRequest req, String key) {
        try {
            return Long.valueOf(req.getParameter(key));
        } catch (Exception e) {
            return -1L;
        }

    }

    public static Boolean getBool(HttpServletRequest req, String key) {
        try {
            return Boolean.valueOf(req.getParameter(key));
        } catch (Exception e) {
            return false;
        }

    }

    public static String getString(HttpServletRequest req, String key) {
        try {
            String res = req.getParameter(key);
            if (res != null) {
                return res.trim();
            }
            if (res.equals("")) {
                return null;
            }
            return res;
        } catch (Exception e) {
            return null;
        }

    }

}
