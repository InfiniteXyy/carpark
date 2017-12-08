<%@ page import="MyConnector.SqlConnect" %>
<%@ page import="data.Info" %><%--
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js"></script>
</head>
<body>
<%
    SqlConnect sqlConnect = new SqlConnect();
    sqlConnect.startDB();
    Info info = sqlConnect.getInfoNums();
    sqlConnect.endDB();
%>
<!-- 导航栏 -->
<nav class="navbar navbar-fixed-top navbar-dark bg-primary">
    <button class="navbar-toggler hidden-sm-up pull-right" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
        ☰
    </button>
    <a class="navbar-brand" href="#"><%=request.getAttribute("name")%></a>
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
                <li class="nav-item"><a class="nav-link" href="#">用户设置</a></li>
                <li class="nav-item"><a class="nav-link" href="#">关于网站</a></li>
                <li class="nav-item"><a class="nav-link" href="#">关于合作</a></li>
                <li class="nav-item"><a class="nav-link" href="#">联系我</a></li>
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
                <strong>Holy guacamole!</strong> It's free.. this is an example theme.
            </div>


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
                                <i class="fa fa-list fa-4x"></i>
                            </div>
                            <h6 class="text-uppercase">Lots</h6>
                            <h1 class="display-1">87</h1>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-info">
                        <div class="card-block bg-info">
                            <div class="rotate">
                                <i class="fa fa-twitter fa-5x"></i>
                            </div>
                            <h6 class="text-uppercase">Cars</h6>
                            <h1 class="display-1">125</h1>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="card card-inverse card-warning">
                        <div class="card-block bg-warning">
                            <div class="rotate">
                                <i class="fa fa-share fa-5x"></i>
                            </div>
                            <h6 class="text-uppercase">News</h6>
                            <h1 class="display-1">36</h1>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 标签内容 -->
            <div class="tab-content">
                <br>
                <div role="tabpanel" class="tab-pane fade in active" id="home1">
                    <h1>主页</h1><br>
                    <div class="col-md-12 col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead class="thead-inverse">
                                <tr>
                                    <th>#</th>
                                    <th>车主</th>
                                    <th>小区</th>
                                    <th>时间</th>
                                    <th>价格</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1,001</td>
                                    <td>Xyy</td>
                                    <td>bootstrap</td>
                                    <td>17.12.2</td>
                                    <td>$100</td>
                                </tr>
                                <tr>
                                    <td>1,002</td>
                                    <td>Xyy2</td>
                                    <td>JavaScript</td>
                                    <td>17.12.3</td>
                                    <td>$200</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </p>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="profile1">
                    <h1>个人信息</h1><br>
                    <div class="col-lg-12">
                        <div class="card card-default card-block">
                            <ul id="tabsJustified" class="nav nav-tabs nav-justified">
                                <li class="nav-item">
                                    <a class="nav-link" href="" data-target="#tab1" data-toggle="tab">List</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="" data-target="#tab2" data-toggle="tab">Profile</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="" data-target="#tab3" data-toggle="tab">More</a>
                                </li>
                            </ul>
                            <!--/tabs-->
                            <br>
                            <div id="tabsJustifiedContent" class="tab-content">
                                <div class="tab-pane fade" id="tab1">
                                    <div class="list-group">
                                        <a href="" class="list-group-item"><span class="pull-right label label-success">51</span> Home Link</a>
                                        <a href="" class="list-group-item"><span class="pull-right label label-success">8</span> Link 2</a>
                                        <a href="" class="list-group-item"><span class="pull-right label label-success">23</span> Link 3</a>
                                        <a href="" class="list-group-item text-muted">Link n..</a>
                                    </div>
                                </div>
                                <div class="tab-pane fade active in" id="tab2">
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


                <div role="tabpanel" class="tab-pane fade" id="messages1">
                    <h1>租车请求</h1><br>
                    <div class="card-columns">
                        <div class="carditems">
                            <div class="card text-left">
                                <img class="card-img-top" src="src/jiangge.jpg" width="100%">
                                <div class="card-block">
                                    <h4 class="card-title">你如何看待刘鑫</h4>
                                    <p href="" class="card-text">事件链接</p>
                                    <div class="btn-group">
                                        <button onclick="goToRoom('jiangge', false)" class="btn btn-info">有罪</button>
                                        <button onclick="goToRoom('jiangge', true)" class="btn btn-secondary">无罪</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card text-center">
                                <div class="card-header">
                                    自由聊天室
                                </div>
                                <div class="card-block">
                                    <h4 class="card-title">无题</h4>
                                    <p class="card-text">没有人喜欢孤独，只是不愿失望。</p>
                                    <button onclick="goToRoom('free', null)" class="btn btn-success">立即加入</button>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card text-left">
                                <img class="card-img-top" src="src/learn.jpeg" width="100%">
                                <div class="card-block">
                                    <h4 class="card-title">你觉得学习是为钱吗</h4>
                                    <div class="btn-group">
                                        <div class="btn-group">
                                            <button onclick="goToRoom('money', false)" class="btn btn-info">不是</button>
                                            <button onclick="goToRoom('money', true)" class="btn btn-secondary">是的</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card text-center">
                                <div class="card-header">
                                    自由聊天室
                                </div>
                                <div class="card-block">
                                    <h4 class="card-title">学习</h4>
                                    <p class="card-text">以一种特别的方式和你的同学交流</p>
                                    <button onclick="goToRoom('learn', null)" class="btn btn-success">立即加入</button>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card">
                                <img class="card-img" src="src/wallpaper.png" width="100%" style="opacity: 0.3;">
                                <div class="card-img-overlay">
                                    <h4 class="card-title">夏目漱石</h4>
                                    <p class="card-text"><br><br><br><br>人就在不断的选择的矛盾中，戴上面具，焚烧过去，武装自己。</p>
                                </div>
                            </div>
                        </div>
                        <div class="carditems">
                            <div class="card text-center ">
                                <div class="card-header">
                                    讨论聊天室
                                </div>
                                <div class="card-block">
                                    <h4 class="card-title">编程</h4>
                                    <p class="card-text">JavaScript是世界上最好的语言？</p>
                                    <button onclick="goToRoom('coding', null)" class="btn btn-success">立即加入</button>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card text-left">
                                <img class="card-img-top" src="src/ximengyao.jpg" width="100%">
                                <div class="card-block">
                                    <h4 class="card-title">你如何看待奚梦瑶摔倒</h4>
                                    <p href="" class="card-text">事件链接</p>
                                    <div class="btn-group">
                                        <button onclick="goToRoom('mengyao', false)" class="btn btn-info">应当批评</button>
                                        <button onclick="goToRoom('mengyao', true)" class="btn btn-secondary">应当鼓励</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="carditems">
                            <div class="card card-inverse" style="background-color: #333; border-color: #333;">
                                <div class="card-block">
                                    <h3 class="card-title">帮助我们</h3>
                                    <p class="card-text">不管你有任何建议或者发现了什么bug，都请联系我们，你的支持是我们最大的动力</p>
                                    <a href='mailto:xuyiyangwing@qq.com' class="btn btn-primary">联系邮箱</a>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>


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

</body>
<script>
    $("#myInfo").collapse();
</script>
</html>
