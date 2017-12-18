package Servlets;

import connecter.SqlConnect;
import data.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlets.RegisterServlet", urlPatterns = {"/Servlets.RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String password = "";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();

        //这里应该检查是否有重复的用户存在
        int status = sqlConnect.checkInfoFromDB(user);

        if (status == User.NOUSER) {
            response.getWriter().print("ok");
        } else if (status == User.WRONGPW) {
            response.getWriter().print("duplicate");
        }

        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
