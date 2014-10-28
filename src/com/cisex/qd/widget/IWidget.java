package com.cisex.qd.widget;

import com.cisex.qd.dao.Dao;
import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Created by vezhou.
 * Date: 2012-8-8
 * Time: 15:18:52
 */
public abstract class IWidget extends Widget {

    private Configuration configuration;

    private Data data;

    private Dao dao;

    public void copyFromWidget(Widget widget) {
        setSetting(widget.getSetting());
        setId(widget.getId());
        setDashboardId(widget.getDashboardId());
        setType(widget.getType());
        setName(widget.getName());


        Class clazz = this.getClass();
        String settingClass = clazz.getName() + "$Configuration";
        try {
            Configuration configuration = (Configuration) Class.forName(settingClass).newInstance();
            configuration.fromString(widget.getSetting());
            this.configuration = configuration;
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    public ApiResult validateConfig(String jsonString) {
        try {
            JSONObject json = JSONObject.fromObject(jsonString);
            configuration.fromJson(json);
            return configuration.validate();
        } catch (JSONException e) {
            return new ErrorResult("Error: " + e.getMessage());
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    public abstract void loadData();


    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public abstract static class Data {

    }

    public abstract static class Configuration {
        public void fromString(String config) {
            JSONObject json = JSONObject.fromObject(config);
            if (json != null && !json.isNullObject()) {
                fromJson(json);
            }
        }

        public abstract void fromJson(JSONObject json);

        public abstract ApiResult validate();

        public abstract Object get(String key);

        public abstract String getProjectCodes();
    }
}
