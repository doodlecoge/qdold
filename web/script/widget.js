var WIDGET_PREFIX = "widgetid-";

function addNewWidget(type, dashboardId) {
    var newWidgetId = -1;
    $.ajaxSetup({async:false});
    $.post("create", {dashboardId : dashboardId || 1, type : type, name: "New Widget"},
        function(data) {
            var response = eval("(" + data + ")");
            if (response["statusCode"] == 0) {
                newWidgetId = response["ext"]["widgetId"];
            }
        });
    return newWidgetId;
}

function generateEditableWidget(widgetId) {
    return "<div class=\"widget movable removable editable closeconfirm\" id=\"" + WIDGET_PREFIX + widgetId + "\"><div class=\"widget-header\">\n" +
        "        <strong class=\"widget-title\"></strong>\n" +
        "    </div>\n" +
        "    <div class=\"widget-editbox\">\n" +
        "        <iframe src=\"setting?widgetId=" + widgetId + "\" style=\"width: 100%; overflow: hidden; border: 0px; height: auto\"\n" +
        "                scrolling=\"no\" scrollbar=\"no\"></iframe>\n" +
        "    </div>\n" +
        "    <div class=\"widget-content\">\n" +
        "        <iframe src=\"view?widgetId=" + widgetId + "\" style=\"width: 100%; overflow: hidden; border: 0px; height: auto\"\n" +
        "                scrolling=\"no\" scrollbar=\"no\"></iframe>\n" +
        "    </div></div>";
}

function saveDashboardLayout(dbId) {
//    alert(dbId);
    var layout = getDashboardLayout();
//    alert(JSON.stringify(layout));
    $.ajaxSetup({async:false});
    layout = JSON.stringify(layout);
    $.post("savelayout", {dashboardId : dbId, layout: layout},
        function(data) {
        });
}

function removeWidget(widgetId) {
    var removed = false;
    $.ajaxSetup({async:false});
    $.post("delete", { widgetId: widgetId}, function(data) {
        var response = eval("(" + data + ")");
        if (response["statusCode"] == 0)
            removed = true;
    });
    return removed;
}

function renameWidget(widgetId, newName) {
    $(".widget-title", $("#" + widgetId)).text(newName);
}

function getDashboardLayout() {
    var layout = [];
    $(".widget-place").each(function(index, element) {
        var widgetPlace = $(element);
        var column = [];
        widgetPlace.find(".widget").each(function(index, element) {
            column[index] = $(element).attr("id").substring(WIDGET_PREFIX.length);
        });
        layout[index] = column;
    });
    return layout;
}


function update() {

}