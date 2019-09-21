package com.seeyu.mvc.utils;

import com.seeyu.core.utils.BaseJsonData;
import com.seeyu.i18n.core.I18nMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author seeyu
 * @date 2019/6/26
 */
public class ResponseUtils {

    public static void responseJsonData(HttpServletResponse resp, BaseJsonData jsonData) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        jsonData.setMessage(I18nMessage.valueOf(jsonData.getMessage(), jsonData.getMessageArgs()));
        resp.getOutputStream().write(JsonUtils.bean2Json(jsonData).getBytes());
    }

}
