package com.cisex.qd.web.api;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vezhou.
 * Date: 2012-8-15
 * Time: 9:31:04
 */
public class ApiResultWriter {

    public static void write(HttpServletResponse response, ApiResult result) throws IOException {
        JSONObject json = new JSONObject();
        json.put("statusCode", result.getStatusCode());
        json.put("statusMessage", result.getStatusMessage());
        json.put("ext", result.getExt());
//        String jsonString = json.toString();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        out.print(json);
        out.close();
    }

}
