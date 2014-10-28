package com.cisex.qd.web.action.widget;

import com.cisex.qd.vo.Widget;
import com.cisex.qd.web.action.ApiAction;
import com.cisex.qd.web.api.ApiResult;
import com.cisex.qd.web.api.ErrorResult;
import com.cisex.qd.widget.IWidget;
import com.cisex.qd.widget.WidgetManager;
import org.apache.commons.lang.StringUtils;

/**
 * Created by vezhou.
 * Date: 2012-8-14
 * Time: 16:08:02
 */
public class SaveSettingAction extends ApiAction {

    private int widgetId;

    private String name;

    private String setting;


    @Override
    protected ApiResult executeApi() {
        Widget widget = this.dao.getWidgetDao().findWidgetById(widgetId);

        if (widget == null) {
            return new ErrorResult("Widget " + widgetId + " not found");
        }

        IWidget iWidget = WidgetManager.getInstance().delegate(widget);

        if (StringUtils.isBlank(name))
            return new ErrorResult("Please provide a name for the widget");

        if (StringUtils.isBlank(setting))
            setting = "{}";

        ApiResult validateResult = iWidget.validateConfig(setting);
        if (!validateResult.isSuccess())
            return validateResult;

        widget.setSetting(setting);
        widget.setName(name);

        this.dao.getWidgetDao().saveWidget(widget);

        return ApiResult.SUCCESS;
    }


    public int getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }
}
