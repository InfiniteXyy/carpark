package Servlets;

import connecter.SqlConnect;
import data.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlets.Register", urlPatterns = {"/Servlets.Register"})
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();

        sqlConnect.putInfoIntoDB(user);

        sqlConnect.endStmt();
        sqlConnect.endDB();

        response.sendRedirect("/login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
