<%@ page import="java.util.ArrayList" %>
<%@ page import="data.user.User" %>
<%@ page import="data.AdminUpdater" %>
<%@ page import="data.park.Carport" %><%--
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
<body style="padding-top: 0px">
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
                <th><%=user.getEmail()%><%
                    if (request.getParameter("inputEmail").equals(user.getEmail()))
                        out.print("<b style='color: red'>  me</b>");
                %></th>
                <th><%=user.getPassword()%></th>
                <th><%=user.renderNickname()%></th>
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
    <br><br>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="thead-inverse">

            <tr>
                <th>parkID  <i class="fa fa-plus" aria-hidden="true" data-toggle="modal" data-target="#addPark_modal" style="cursor:pointer;"></i></th>
                <th>leftnum</th>
                <th>owner</th>
                <th>money</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Carport> carports = adminUpdater.updateParks();
                for (Carport carport : carports) {
            %>
            <tr>
                <th><%=carport.getId()%></th>
                <th><%=carport.getLeftnum()%></th>
                <th><%=carport.getOwner()%></th>
                <th><%=carport.getPrice()%></th>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <!-- 增加新车的模态框 -->
    <div class="modal fade" id="addPark_modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" >New Carport</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">SET owner:</label>
                            <input type="text" class="form-control" id="owner">
                        </div>
                        <div class="form-group">
                            <label class="control-label">SET state:</label>
                            <input type="text" class="form-control" id="state">
                        </div>
                        <div class="form-group">
                            <label class="control-label">SET num:</label>
                            <input type="text" class="form-control" id="num">
                        </div>
                        <div class="form-group">
                            <label class="control-label">SET price:</label>
                            <input type="text" class="form-control" id="price">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addPort()" data-dismiss="modal" >Add</button>
                </div>
            </div>
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

    function addPort() {
        var owner = $("#owner").val();
        var state = $("#state").val();
        var num = $("#num").val();
        var price = $("#price").val();
        xmlHttp.open("post","/Servlets.AddContent.CarportAdmin", true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send("owner="+ owner +"&state=" + state+"&num="+num+"&price="+price);
    }
</script>
</html>
