
<%@ page import="data.info.Info" %>
<%@ page import="data.GroundUpdater" %>
<%@ page import="data.park.Carport" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="data.park.Car" %>
<%@ page import="data.user.News" %><%--
  Created by IntelliJ IDEA.
  User: xyy
  Date: 2017/12/5
  Time: 下午11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>大厅</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="style/css/styles.css">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
    <script src="https://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</head>
<body>
<%
    String email = request.getParameter("inputEmail");
    GroundUpdater updater = new GroundUpdater(email);
    Info info = updater.updateInfo();
%>
<!-- 导航栏 -->
<nav class="navbar navbar-fixed-top navbar-dark bg-primary">
    <button class="navbar-toggler hidden-sm-up pull-right" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
        ☰
    </button>
    <a class="navbar-brand" href="#" id="nickName"><%=request.getAttribute("name")%></a>
    <div class="collapse navbar-toggleable-xs" id="collapsingNavbar">
        <ul class="nav navbar-nav pull-right">
            <li class="nav-item">
                <a class="nav-link" href="#myInfo" data-toggle="collapse">数据显示/关闭</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#home1" role="tab" data-toggle="tab">主页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#profile1" role="tab" data-toggle="tab">个人信息</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#messages1" role="tab" data-toggle="tab">租车请求</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#rank" role="tab" data-toggle="tab">排行榜</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#settings1" role="tab" data-toggle="tab">Settings</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid" id="main">
    <div class="row row-offcanvas row-offcanvas-left">
        <!-- 侧边栏 -->
        <div class="col-md-3 col-lg-2 sidebar-offcanvas" id="sidebar" role="navigation">
            <ul class="nav nav-pills nav-stacked">
                <li class="nav-item"><a class="nav-link" data-toggle="modal" data-target="#user_info_model" style="cursor: pointer">昵称设置</a></li>
                <li class="nav-item"><a class="nav-link" data-toggle="modal" data-target="#connect_model" style="cursor: pointer">联系我</a></li>
                <li class="nav-item"><a class="nav-link" data-toggle="modal" data-target="#inputTxtModel" style="cursor: pointer">发送信息</a></li>
            </ul>
        </div>
        <div class="col-md-9 col-lg-10 main">
            <h1 class="display-1 hidden-xs-down">
                停车场管理系统
            </h1>
            <!-- 最上部分的提示栏 -->
            <p class="lead">version1.0   Thanks to Bootstrap4 demo</p>

            <!-- 这是用于警示的信息 -->
            <div class="alert alert-warning fade collapse" role="alert" id="myAlert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <strong>警告</strong> 与服务器断开连接，请检查你的网络状况！
            </div>

            <!-- 显示数字 -->
            <div class="row fade collapse" id="myInfo">
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-success">
                        <div class="card-block bg-success">
                            <div class="rotate">
                                <i class="fa fa-user fa-5x"></i>
                            </div>
                            <h6 class="text-uppercase">Users</h6>
                            <h1 class="display-1"><%=info.userNum%></h1>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-danger">
                        <div class="card-block bg-danger">
                            <div class="rotate">
                                <i class="fa fa-star fa-4x"></i>
                            </div>
                            <h6 class="text-uppercase">Lots</h6>
                            <h1 class="display-1"><%=info.lotNum%></h1>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-info">
                        <div class="card-block bg-info">
                            <div class="rotate">
                                <i class="fa fa-car fa-5x"></i>
                            </div>
                            <h6 class="text-uppercase">Cars</h6>
                            <h1 class="display-1"><%=info.carNum%></h1>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-warning">
                        <div class="card-block bg-warning">
                            <div class="rotate">
                                <i class="fa fa-keyboard-o fa-5x"></i>
                            </div>
                            <h6 class="text-uppercase">News</h6>
                            <h1 class="display-1"><%=info.newsNum%></h1>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 标签内容 -->
            <div class="tab-content">
                <!-- 主页 -->
                <div role="tabpanel" class="tab-pane fade in active" id="home1">
                    <h1>最近消息</h1><br>
                    <iframe src="news_holder.jsp" id="news_holder" frameborder="no" scrolling="no"></iframe>
                    <h1>车位租借</h1><br>
                    <div class="col-md-12 col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead class="thead-inverse">
                                <tr>
                                    <th>#</th>
                                    <th>卖家</th>
                                    <th>小区</th>
                                    <th>租借</th>
                                    <th>价格</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                ArrayList<Carport> carports = updater.updateCarports();
                                for (Carport rs : carports) {
                                %>
                                <tr>
                                    <td><%= rs.getId()%></td>
                                    <td><%= rs.getOwner()%></td>
                                    <td><%= rs.getState()%></td>
                                    <td><%= rs.getDate()%></td>
                                    <td><%= rs.getPrice()%></td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- 个人信息 -->
                <div role="tabpanel" class="tab-pane fade" id="profile1">
                    <h1>个人信息</h1><br>
                    <div class="col-lg-12">
                        <div class="card card-default card-block">
                            <ul id="tabsJustified" class="nav nav-tabs nav-justified">
                                <li class="nav-item">
                                    <a class="nav-link active" href="" data-target="#tab1" data-toggle="tab">我的车辆</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="" data-target="#tab2" data-toggle="tab">我的租借</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="" data-target="#tab3" data-toggle="tab">我的车位</a>
                                </li>
                            </ul>
                            <br>
                            <div id="tabsJustifiedContent" class="tab-content">
                                <div class="tab-pane fade active in" id="tab1">
                                    <div class="list-group">
                                        <div class="card-columns">
                                            <%
                                                ArrayList<Car> mycars = updater.updateCars();
                                                for (Car car : mycars) {
                                            %>
                                            <div class="carditems">
                                                <div class="card text-left">
                                                    <img class="card-img-top" src="/imgs/<%=car.getPicture()%>" width="100%">
                                                    <div class="card-block">
                                                        <h4 class="card-title"><%=car.getName()%></h4>
                                                        <button class="btn btn-primary" onclick="showCarInfo('<%=car.getName()%>')">查看详情</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="tab2">
                                    <div class="row">
                                        <div class="col-sm-7">
                                            <h4>Profile Section</h4>
                                            <p>Imagine creating this simple user profile inside a tab card.</p>
                                        </div>
                                        <div class="col-sm-5"><img src="http://placehold.it/170" class="pull-right img-responsive img-rounded"></div>
                                    </div>
                                    <hr>
                                    <a href="javascript:;" class="btn btn-info btn-block">Read More Profiles</a>
                                    <div class="spacer5"></div>
                                </div>
                                <div class="tab-pane fade" id="tab3">
                                    <div class="list-group">
                                        <a href="" class="list-group-item"><span class="pull-right label label-info label-pill">44</span> <code>.panel</code> is now <code>.card</code></a>
                                        <a href="" class="list-group-item"><span class="pull-right label label-info label-pill">8</span> <code>.nav-justified</code> is deprecated</a>
                                        <a href="" class="list-group-item"><span class="pull-right label label-info label-pill">23</span> <code>.badge</code> is now <code>.label-pill</code></a>
                                        <a href="" class="list-group-item text-muted">Message n..</a>
                                    </div>
                                </div>
                            </div>
                            <!--/tabs content-->
                        </div><!--/card-->
                    </div><!--/col-->
                </div>

                <!-- 租车请求 -->
                <div role="tabpanel" class="tab-pane fade" id="messages1">
                    <h1>租车请求</h1><br>


                </div>

                <!-- 排行榜 -->
                <div role="tabpanel" class="tab-pane" id="rank">
                    <h1>排行榜</h1><br><br>

                    <div class="card-columns">
                        <%
                            ArrayList<Car> cars = updater.updateRankCars();
                            for (Car car : cars) {
                        %>
                        <div class="carditems">
                            <div class="card text-left">
                                <img class="card-img-top" src="/imgs/<%=car.getPicture()%>" width="100%">
                                <div class="card-block">
                                    <h4 class="card-title"><%=car.getName()%></h4>
                                    <p class="card-text">From <%=car.getOwner()%></p>
                                        <button class="btn btn-primary">联系主人</button>
                                        <button class="btn btn-secondary">添加到愿望单</button>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>

                <!-- 设置 -->
                <div role="tabpanel" class="tab-pane" id="settings1">
                    <h1>设置</h1><br><br>

                    <h4>请选择主题</h4><br>
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary-outline active">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> 蓝色
                        </label>
                        <label class="btn btn-secondary-outline">
                            <input type="radio" name="options" id="option2" autocomplete="off"> 白色
                        </label>
                        <label class="btn btn-warning-outline">
                            <input type="radio" name="options" id="option3" autocomplete="off"> 黄色
                        </label>
                    </div><br><br>
                    <h4>清除cookie</h4><br>
                    <button type="button" class="btn btn-secondary">清除</button>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br><br><br>

<!-- 发送信息模态框 -->
<div class="modal fade" id="inputTxtModel" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">New message</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="atNames" class="control-label">@Somebody or @Everyone:</label>
                        <input type="text" class="form-control" id="atNames">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">Message:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="send_message()">Send message</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改昵称的模态框 -->
<div class="modal fade" id="user_info_model">
    <div class="modal-dialog" role="document" aria-hidden="true">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">修改昵称</h4>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="nickNameTxt">
            </div>
            <div class="modal-footer">
                <span id="nameExistWarn" style="display: none;"></span>
                <button type="button" class="btn btn-primary" id="nickNameBtn">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 联系我们的模态框 -->
<div class="modal fade" id="connect_model">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="carditems">
                    <div class="card">
                        <div class="card-block">
                            <h3 class="card-title">帮助我</h3>
                            <p class="card-text">不管你有任何建议或者发现了什么bug，都请联系我们，你的支持是我最大的动力</p>
                            <a href='mailto:xuyiyangwing@qq.com' class="btn btn-primary">联系邮箱</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- 显示车辆信息的模态框 -->
<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" id="carinfo_model">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <p id="carName"></p>
            <p id="carEarnMoney"></p>
            <p id="carMoney"></p>
            <p id="carIsRent"></p>
        </div>
    </div>
</div>
</body>
<script>
    $("#myInfo").collapse();
    var xmlHttp = new XMLHttpRequest();
    var email = '<%= request.getParameter("inputEmail")%>';

    //设置昵称
    document.getElementById('nickNameBtn').addEventListener('click', function() {
        var nickName = document.getElementById("nickNameTxt").value;
        if (nickName.trim().length != 0) {
            xmlHttp.open("post","/Servlets.ChangeNickName", true);
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        var result = xmlHttp.responseText;//接受返回的字符串
                        if (result == "ok") {
                            document.getElementById("nickName").innerText=nickName;
                            $('#user_info_model').modal('hide');
                        } else {
                            nameExistWarn();
                        }
                    } else {
                        alert("服务器繁忙，请稍候重试！");
                    }
                }
            };
            xmlHttp.send("nickName="+nickName+"&email="+email);

        } else {
            document.getElementById('nickNameTxt').focus();
            document.getElementById('nameExistWarn').innerHTML = '<b style="color:#cd5e3c"> 请输入昵称！</b>';
            $("#nameExistWarn").fadeIn(100);
        }
    }, false);

    //设置发送信息
    function send_message() {
        var input = document.getElementById("message-text").value;
        if (input.trim().length != 0) {
            xmlHttp.open("post","/Servlets.AddContent.AddNews", true);
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlHttp.send("news="+input +"&email="+ email);
            $("#news_holder").attr('src', $('#news_holder').attr('src'));
            document.getElementById("inputTxt").value = "";
            autoFitIframe(document.getElementById("news_holder"));
        }
    }

    //若昵称已存在的警告信息
    function nameExistWarn() {
        document.getElementById('nameExistWarn').innerHTML = '<b style="color:#cd5e3c"> 昵称已存在！</b>';
        $("#nameExistWarn").fadeIn(100);
    }

    //自动调节子网页的高度
    autoFitIframe(document.getElementById("news_holder"));
    function autoFitIframe(iframe) {
        var doc = iframe.contentDocument || iframe.contentWindow.document;
        iframe.style.width = '100%';

        function update() {
            var containerWidth = iframe.parentNode.offsetWidth;
            doc.body.style.width =  + 'px';
            doc.body.style.padding = '0';
            doc.body.style.margin = '0';
            doc.body.style.border = 'none';
            iframe.style.height = '0';
            iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 'px';
        }
        if (doc.readyState === 'complete') {
            update();
        }

        if (iframe.addEventListener) {
            iframe.addEventListener('load', update, false);
        }
        else if (iframe.attachEvent) {
            iframe.attachEvent('onload', update);
        }
    }

    //显示车辆信息模态框
    function showCarInfo(car) {
        //目前租出状态，累计获得价格，车辆购买价格，车辆照片，车辆名称
        xmlHttp.open("post","/Servlets.Search.Car", true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send("news="+input +"&email="+ email);
        $("#news_holder").attr('src', $('#news_holder').attr('src'));
        document.getElementById("inputTxt").value = "";
        autoFitIframe(document.getElementById("news_holder"));
    }
</script>
</html>
