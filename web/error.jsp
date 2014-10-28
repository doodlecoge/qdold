<%--
  Created by IntelliJ IDEA.
  User: huaiwang
  Date: 12-9-17
  Time: 上午8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="<%= request.getContextPath() %>/script/jquery172.js"></script>
    <script type="text/javascript">
        //var base = "<%= request.getContextPath() %>";
        $(function() {
            var h = $(window).height();
            h = Math.floor(h);

            $("#msg").height(h);
            $("#msg").css('line-height', h + "px");
            $("#msg").css('text-align', 'center');
            $("#msg").css('font-family', 'Courier New');
        });
    </script>
</head>
<body>
<div id="msg">
<h1>
    <a href="<%= request.getContextPath() %>/widget/index">Page Not Found!</a>
</h1>
</div>
</body>
</html>