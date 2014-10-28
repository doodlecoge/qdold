package com.cisex.qd.widget;

import com.cisex.qd.apiclient.ApiCaller;
import com.cisex.qd.reports.TestStats;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-8-22
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */


public class ProductTrendsWidget extends IWidget {
    @Override
    public void loadData() {
        Data data = null;

        String setting = this.getSetting();
        JSONObject json = JSONObject.fromObject(setting);

        if(!json.containsKey("project_codes")) {
            this.setData(null);
            return;
        }

        JSONArray jarr = json.getJSONArray("project_codes");
        List<String> pcs = new ArrayList<String>();

        int len = jarr.size();

        for(int i = 0; i < len; i++) {
            String pc = jarr.getString(i);
            pc = pc.trim();

//            if(!pc.isEmpty())
            if(!"".equals(pc))
                pcs.add(pc);
        }

        try {
            int builds = ((Configuration)this.getConfiguration()).getBuilds();
            Map<String, List<TestStats>> map = ApiCaller.getProjectPassrate(pcs, builds);
            data = new Data(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setData(data);
    }



    public static final class Data extends IWidget.Data {
        Map<String, List<TestStats>> stats;

        public  Data(Map<String, List<TestStats>> stats) {
            this.stats = stats;
        }

        public Map<String, List<TestStats>> getStats() {
            return stats;
        }

        public void setStats(Map<String, List<TestStats>> stats) {
            this.stats = stats;
        }
    }


    public static final class Configuration extends IWidget.Configuration {
        private JSONObject json;
        private List<String> projectCodes;

        @Override
        public void fromJson(JSONObject json) {
            this.json = json;

            if(!json.containsKey("project_codes")) return;

            JSONArray jarr = json.getJSONArray("project_codes");
            projectCodes = new ArrayList<String>();

            int len = jarr.size();

            for(int i = 0; i < len; i++) {
                String pc = jarr.getString(i);
                pc = pc.trim();

//                if(!pc.isEmpty())
                if(!"".equals(pc))
                    this.projectCodes.add(pc);
            }
        }

        @Override
        public ApiResult validate() {
            try {
                Set<String> set = ApiCaller.GetProjectCodes();
                for(String pc : this.projectCodes) {
                    if(!set.contains(pc))
                        return new ErrorResult("Invalid project name " + pc);
                }
            } catch (Exception e) {
                return new ErrorResult("Error: " + e.getMessage());
            }

            return ApiResult.SUCCESS;
        }

        @Override
        public Object get(String key) {
            Object obj = this.json.get(key);
            if(obj == null) return "";
            else return obj;
        }

        @Override
        public String getProjectCodes() {
            if(!this.json.has("project_codes")) return "";

            JSONArray jarr = this.json.getJSONArray("project_codes");

            int len = jarr.size();

            String pcs = "";

            for(int i = 0; i < len; i++) {

                if(i != 0) pcs += ",";

                pcs += jarr.getString(i);

            }

            return pcs;
        }


        public int getBuilds() {
            return Integer.valueOf(this.json.getString("builds"));
        }
    }
}

