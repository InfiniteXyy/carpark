package Servlets;

import connecter.SqlConnect;
import data.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlets.LoginServlet", urlPatterns = {"/Servlets.LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        //链接数据库，检查是否存在user信息
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();

        int status = sqlConnect.checkInfoFromDB(user);
        switch (status) {
            case User.RIGHTPW:
                response.getWriter().print("success");
                break;
            case User.NOUSER:
                response.getWriter().print("没有找到该用户！");
                break;
            case User.WRONGPW:
                response.getWriter().print("密码错误！");
                break;
        }


        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
