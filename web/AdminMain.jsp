<%@ page import="java.util.ArrayList" %>
<%@ page import="data.user.User" %>
<%@ page import="data.AdminUpdater" %><%--
  Created by IntelliJ IDEA.
  User: xyy
  Date: 2017/12/23
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Ground</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="style/css/styles.css">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
    <script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</head>
<body>
<div>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="thead-inverse">

            <tr>
                <th>email</th>
                <th>password</th>
                <th>nickname</th>
                <th>isAdmin</th>
            </tr>
            </thead>
            <tbody>
            <%
                AdminUpdater adminUpdater = new AdminUpdater();
                ArrayList<User> arrayList = adminUpdater.updateUsers();
                for (User user : arrayList) {
            %>
            <tr>
                <th><%=user.getEmail()%></th>
                <th><%=user.getPassword()%></th>
                <th><%=user.getNickname()%></th>
                <%
                    if (user.isAdmin()){
                %>
                <th id="admin<%=user.getEmail()%>"><button  class="btn btn-info" onclick="updateAdmin('<%=user.getEmail()%>', true)"><%=user.isAdmin()%></button></th>

            <%
                } else {
            %>
              <th id="admin<%=user.getEmail()%>"><button  class="btn btn-secondary" onclick="updateAdmin('<%=user.getEmail()%>', false)"><%=user.isAdmin()%></button></th>
                <%
             }
            %>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>
    var xmlHttp = new XMLHttpRequest();
    function updateAdmin(email, type) {
        xmlHttp.open("post","/Servlets.AddContent.ServletAdmin", true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send("Admin="+ email +"&type=" + !type);
        if (!type)
        document.getElementById("admin"+email).innerHTML = '<button  class="btn btn-info" onclick=\"updateAdmin(\''+email+'\',true)\">'+!type+'</button>';
        else document.getElementById("admin"+email).innerHTML = '<button  class="btn btn-secondary" onclick=\"updateAdmin(\''+email+'\',false)\">'+!type+'</button>';

    }
</script>
</html>
