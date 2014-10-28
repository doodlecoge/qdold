package com.cisex.qd.apiclient;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import com.cisex.qd.reports.TestStats;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * Created with IntelliJ IDEA.
 * User: huaiwang
 * Date: 12-8-21
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class ApiCaller {
//    public static final String url = "http://localhost/tasche/api/call.action";
    public static final String url = "http://10.224.138.200/tasche/api/call.action";


    public static Set<String> GetProjectCodes() throws IOException, DocumentException {
        HttpEngine eng = new HttpEngine();
        Map<String, String> params = new HashMap<String, String>();
        params.put("api", "ListProjectCode");
        String xml = eng.post(url, params).getHtml();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new StringReader(xml));

        List<Node> nodes = doc.selectNodes("/ApiResult/xml/projectCodes/projectCode");

        Set<String> set = new HashSet<String>();

        int len = nodes.size();

        for(int i = 0; i < len; i++) {
            String txt = nodes.get(i).getText().trim();
            txt = txt.replace("\"", "\\\"");

//            if(!txt.isEmpty())
            if(!"".equals(txt))
                set.add(txt);
        }

        return set;
    }


    private static String getReqXml(List<String> pcs, int builds) {
        Document doc = DocumentHelper.createDocument();

        Element xml = doc.addElement("xml");
        xml.addElement("builds").addText("" + builds);
        Element els = xml.addElement("projects");

        for(String pc : pcs)
            els.addElement("project").addText(pc);

        String reqXml = doc.asXML();

        return reqXml;
    }

    private static String getRespXml(String reqXml) throws IOException {
        HttpEngine eng = new HttpEngine();

        Map<String, String> params = new HashMap<String, String>();
        params.put("api", "GetOverallStats");
        params.put("xml", reqXml);

        String respXml = eng.post(url, params).getHtml();

        return respXml;
    }

    private static List<String> set2list(Set<String> set) {
        List<String> lst = new ArrayList<String>();

        for(String s : set) {
            lst.add(s);
        }


        return lst;
    }

    public static Map<String, List<TestStats>> getProjectPassrate(Set<String> pcs, int builds) throws IOException, DocumentException {
        return getProjectPassrate(set2list(pcs), builds);
    }

    public static Map<String, List<TestStats>> getProjectPassrate(List<String> pcs, int builds) throws IOException, DocumentException {
        String reqXml = getReqXml(pcs, builds);

        String respXml = getRespXml(reqXml);

        SAXReader reader  = new SAXReader();
        Document doc = reader.read(new StringReader(respXml));
        List<Node> nodes = doc.selectNodes("/ApiResult/xml/projectStats/projectStat");

        Map<String, List<TestStats>> stats = new HashMap<String, List<TestStats>>();

        for(Node node : nodes) {
            Element el = (Element) node;
            String pc = el.attribute("projectCode").getValue();

            List<Node> bss = el.selectNodes("buildStat");
            int len_bss = bss.size();

            List<TestStats> lst = new ArrayList<TestStats>();
            for(int i = 0; i < len_bss && i < builds; i++) {
                Element el_bs = (Element) bss.get(i);

                String bn = el_bs.attribute("buildNumber").getValue();
                int tt = Integer.valueOf(el_bs.attribute("totalCase").getValue());
                int ff = Integer.valueOf(el_bs.attribute("failedCase").getValue());
                String dt = el_bs.attribute("dateCreated").getValue();

                lst.add(new TestStats(pc, bn, tt, ff, dt));
            }

            stats.put(pc, lst);
        }

        return stats;
    }
}
