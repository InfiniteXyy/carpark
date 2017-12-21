<%@ page import="data.user.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="data.GroundUpdater" %><%--
  Created by IntelliJ IDEA.
  User: xyy
  Date: 2017/12/20
  Time: 下午2:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="style/css/styles.css">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
    <script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</head>
<body style="padding-top: 0px">
<div class="card-columns">
<div class="carditems" id="newsItems" style="margin-top: 0">
    <%
        String email = request.getParameter("email");
        GroundUpdater updater = new GroundUpdater(email);
        ArrayList<News> news = updater.updateNews();
        for (News news1 : news) {
    %>

    <div class="card card-block text-right">
        <blockquote class="card-blockquote">
            <p><%=news1.getContent()%></p>
            <footer>
                <small class="text-muted">
                    <%=news1.getOwner()%>
                    <%
                        if (!news1.getTarget().contains("everyone"))
                    %><i class="fa fa-star" aria-hidden="true"></i>
                    <br>
                    <%=news1.getDate().toString() + "   " + news1.getTime().toString()%>
                </small>
            </footer>
        </blockquote>
    </div>
    <%}%>
</div>
</div>
</body>
</html>
