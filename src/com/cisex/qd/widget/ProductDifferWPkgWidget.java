package com.cisex.qd.widget;

import com.cisex.qd.apiclient.ApiCaller;
import com.cisex.qd.reports.TestStats;
import com.cisex.qd.web.api.ApiResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-11-27
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class ProductDifferWPkgWidget extends IWidget {
    @Override
    public void loadData() {
        Data data = null;

        String setting = this.getSetting();
        JSONObject json = JSONObject.fromObject(setting);

        if (!json.containsKey("project_codes")) {
            this.setData(null);
            return;
        }

        JSONObject jobj = json.getJSONObject("project_codes");
        Set<String> pcs = new HashSet<String>();


        Iterator it = jobj.keys();

        while (it.hasNext()) {
            String key = (String) it.next();
            JSONArray jarr = jobj.getJSONArray(key);


            int len = jarr.size();

            for (int i = 0; i < len; i++) {
                String pc = jarr.getString(i);
                if (pc.trim() != "")
                    pcs.add(pc);
            }
        }


        try {

            Map<String, List<TestStats>> map = ApiCaller.getProjectPassrate(pcs, 2);

            data = new Data(map);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setData(data);
    }


    public static final class Data extends IWidget.Data {
        Map<String, List<TestStats>> stats;

        public Data(Map<String, List<TestStats>> stats) {
            this.stats = stats;
        }

        public Map<String, List<TestStats>> getStats() {
            return stats;
        }

        public void setStats(Map<String, List<TestStats>> stats) {
            this.stats = stats;
        }

        public List<TestStats> getStat(String name) {
            return this.stats.get(name);
        }


        public List<TestStats> getTotal(JSONArray pcs) {
            List<Integer> tt = new ArrayList<Integer>();
            List<Integer> ff = new ArrayList<Integer>();

            Set<String> pcSet = new HashSet<String>();
            int len = pcs.size();
            for (int i = 0; i < len; i++) {
                pcSet.add(pcs.getString(i));
            }

            int maxLen = 2;

            for (String pc : stats.keySet()) {
                if(!pcSet.contains(pc)) continue;

                List<TestStats> lst = stats.get(pc);

                int lstSz = lst.size();

                for (int i = 0; i < maxLen; i++) {

                    if(lstSz <= i) continue;

                    TestStats ts = lst.get(i);

                    if (tt.size() <= i) {
                        tt.add(ts.getTotalCase());
                        ff.add(ts.getFailedCase());
                    } else {
                        tt.set(i, tt.get(i) + ts.getTotalCase());
                        ff.set(i, ff.get(i) + ts.getFailedCase());
                    }
                }
            }


            List<TestStats> rst = new ArrayList<TestStats>();

            len = tt.size();

            for (int i = 0; i < len; i++) {
                int t = tt.get(i);
                int f = ff.get(i);

                rst.add(new TestStats("", "", t, f, ""));
            }

            return rst;
        }
    }


//    public static final class Data extends IWidget.Data {
//        private int tt;
//        private int ff;
//
//        private List<TestStats> stats = new LinkedList<TestStats>();
//
//
//        public void addStat(TestStats stats) {
//            this.stats.add(stats);
//            tt += stats.getTotalCase();
//            ff += stats.getFailedCase();
//        }
//
//        public List<TestStats> getStats() {
//            return stats;
//        }
//
//        public TestStats getStat(String key) {
//            for(TestStats s : stats) {
//                if(s.getProjectCode().equals(key))
//                    return s;
//            }
//
//            return null;
//        }
//
//
//        public TestStats getTotal() {
//            return new TestStats("Total", "", tt, ff, null);
//        }
//
//        public TestStats getTotal(JSONArray pcs) {
//            int tt = 0;
//            int ff = 0;
//
//
//            Set<String> pcSet = new HashSet<String>();
//            int len = pcs.size();
//            for(int i = 0; i < len; i++) {
//                pcSet.add(pcs.getString(i));
//            }
//
//            for(TestStats ts : stats) {
//                String pc = ts.getProjectCode();
//
//                if(pcSet.contains(pc)) {
//                    tt += ts.getTotalCase();
//                    ff += ts.getFailedCase();
//                }
//            }
//
//            return new TestStats("", "", tt, ff, "");
//        }
//    }


    public static final class Configuration extends IWidget.Configuration {
        private JSONObject json;


        @Override
        public void fromJson(JSONObject json) {
            this.json = json;

        }

        @Override
        public ApiResult validate() {
//            try {
//                Set<String> set = ApiCaller.GetProjectCodes();
//                for(String pc : this.projectCodes) {
//                    if(!set.contains(pc))
//                        return new ErrorResult("Invalid project name " + pc);
//                }
//            } catch (Exception e) {
//                return new ErrorResult("Error: " + e.getMessage());
//            }

            return ApiResult.SUCCESS;
        }

        @Override
        public Object get(String key) {
            Object obj = this.json.get(key);
            if (obj == null) return "";
            else return obj;
        }

        @Override
        public String getProjectCodes() {
            if (!this.json.has("project_codes")) return "{}";

            return this.json.get("project_codes").toString();
        }

//        public String getProjectCode() {
//            if (!this.json.has("project_codes")) return "{}";
//
//            return this.json.get("project_codes").toString().replace("'", "\\'");
//        }
    }
}
