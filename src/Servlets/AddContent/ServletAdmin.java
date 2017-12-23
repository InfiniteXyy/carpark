package Servlets.AddContent;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletAdmin", urlPatterns = {"/Servlets.AddContent.ServletAdmin"})
public class ServletAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Admin");
        String type = request.getParameter("type");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        if (type.equals("true")) {
            sqlConnect.updateQuery("UPDATE Users SET user_isAdmin = 1 WHERE user_email='"+name+"'");
        } else {
            sqlConnect.updateQuery("UPDATE Users SET user_isAdmin = 0 WHERE user_email='"+name+"'");
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
