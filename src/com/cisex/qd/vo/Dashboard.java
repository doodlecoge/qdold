package com.cisex.qd.vo;

import com.cisex.qd.widget.Layout;
import com.cisex.qd.widget.NotFoundWidget;
import com.cisex.qd.widget.WidgetManager;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vezhou.
 * Date: 2012-8-9
 * Time: 16:59:33
 */
public class Dashboard implements Serializable {
    private int id;
    private String name;
    private String layout;
    private int type2;
    private int pub;
    private int rank;

    private Set<Widget> widgets;
    private Set<User> users = new HashSet<User>(0);
    private Set<User> owners = new HashSet<User>(0);


    public Widget getWidgetById(int id) {
        for (Widget widget : widgets) {
            if (widget.getId() == id)
                return WidgetManager.getInstance().delegate(widget);
        }
        return new NotFoundWidget();
    }

    public Layout getLayoutObject() {
        return Layout.fromJson(getLayout());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(Set<Widget> widgets) {
        this.widgets = widgets;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public int getPub() {
        return pub;
    }

    public void setPub(int pub) {
        this.pub = pub;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
