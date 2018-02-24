package boot.https.util;

import com.alibaba.fastjson.JSON;

public class WebUtil {


    /**
     * object转json字符
     * xuchun
     * 2016-07-07
     * @param object
     * @return
     * @throws Exception
     */
    public static String parseFastJson(Object object){
        return JSON.toJSONString(object);
    }
}
