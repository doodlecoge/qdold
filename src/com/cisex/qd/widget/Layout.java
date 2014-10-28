package com.cisex.qd.widget;

import net.sf.json.JSONArray;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vezhou.
 * Date: 2012-8-10
 * Time: 9:49:22
 */
public class Layout {
    private List<Column> columns = new LinkedList<Column>();

    public void addColumn(Column col) {
        columns.add(col);
        col.setId(columns.size());
    }

    public Column getColumn(int index) {
        return columns.get(index);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public static Layout fromJson(String json) {
        Layout layout = new Layout();
        JSONArray layoutJson = JSONArray.fromObject(json);
        for (Object o : layoutJson) {
            if (!(o instanceof JSONArray))
                continue;

            JSONArray columnJson = (JSONArray) o;
            layout.addColumn(toColumn(columnJson));
        }
        return layout;
    }

    private static Column toColumn(JSONArray json) {
        Column col = new Column();
        for (Object o : json) {
            if (o instanceof String) {
                try {
                    col.addWidget(Integer.valueOf((String) o));
                } catch (NumberFormatException ignore) {}
            }
        }
        return col;
    }

    public static class Column {
        int id = 0;
        List<Integer> widgets = new LinkedList<Integer>();

        public void addWidget(int widgetId) {
            widgets.add(Integer.valueOf(widgetId));
        }

        public List<Integer> getWidgets() {
            return widgets;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
