<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>车位管理系统v1.0</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css" />
  <link rel="stylesheet" href="style/css/login.css">

</head>

<body>
<div class="container" >
  <form class="form-signin" action="Ground" method="post" id="myForm">
    <h2 class="form-signin-heading">请登录</h2>
    <input type="email" name="inputEmail" class="form-control" placeholder="Email address" autofocus>
    <input type="password" name="inputPassword" class="form-control" placeholder="Password">
      <span id="Info"> </span>
    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> 记住我
      </label>
    </div>
    <input type="button" class="btn btn-lg btn-primary btn-block" onclick="validate()" value="登录"></input>
      <a class="btn btn-lg btn-info btn-block" href="register.html">注册</a>
  </form>
</div>
</body>
<script>

    function validate() {
        var xmlHttp = new XMLHttpRequest();
        var email = document.getElementsByName("inputEmail")[0].value;
        var password = document.getElementsByName("inputPassword")[0].value;
        xmlHttp.open("post","/LoginServlet", true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded")
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var result = xmlHttp.responseText;//接受返回的字符串
                    if (result == "success") {
                        document.getElementById("myForm").submit();
                    } else {
                        document.getElementById("Info").innerHTML="<span style='color: #ff461b'>"+result;
                    }
                } else {
                    alert("服务器繁忙，请稍候重试！");
                }
            }
        };
        xmlHttp.send("inputEmail="+email+"&inputPassword="+password);
    }
</script>

</html>