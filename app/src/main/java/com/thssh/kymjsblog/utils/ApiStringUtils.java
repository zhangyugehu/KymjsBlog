package com.thssh.kymjsblog.utils;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class ApiStringUtils {
    /**
     * 重组图片url已获取对应尺寸的醋片
     *
     * @param url    原url
     * @param width  需要的图片宽度
     * @param height 需要图片高度
     * @return
     */
    public static String replaceImageUrlSize(String url, int width, int height) {
        return url.replaceAll("\\d+x\\d+", width + "x" + height);
    }
}
