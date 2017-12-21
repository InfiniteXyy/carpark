package Servlets.AddContent;

import connecter.SqlConnect;
import data.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddNews",  urlPatterns = {"/Servlets.AddContent.AddNews"})
public class AddNews extends HttpServlet {
    private SqlConnect sqlConnect;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String news = request.getParameter("news");
        String email = request.getParameter("email");
        String target = request.getParameter("target");
        sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        if (checkTarget(target.trim(), response.getWriter()))
        sqlConnect.updateQuery("INSERT INTO News (news_content, news_owner, news_time, news_target) \n" +
                "VALUES ('"+ news + "', '"+ email +"', curtime(), '"+target+"')");
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean checkTarget(String target, PrintWriter pw) {
        String[] data = target.split(",");
        for (String single : data) {
            if (single.equalsIgnoreCase("everyone") || single.equals("")) continue;
            User user = new User();
            user.setEmail(single.trim());
            user.setPassword("");
            int status = sqlConnect.checkInfoFromDB(user);
            if (status == User.NOUSER) {
                pw.print("nouser");
                return false;
            }
        }
        return true;
    }

}
