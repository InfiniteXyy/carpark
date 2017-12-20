package Servlets.AddContent;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNews",  urlPatterns = {"/Servlets.AddContent.AddNews"})
public class AddNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String news = request.getParameter("news");
        String email = request.getParameter("email");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        sqlConnect.updateQuery("INSERT INTO News (news_content, news_owner, news_time) \n" +
                "VALUES ('"+ news + "', '"+ email +"', curtime())");
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
