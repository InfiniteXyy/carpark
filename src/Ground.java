
import connecter.SqlConnect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Ground", urlPatterns = {"/Ground"})
public class Ground extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT user_nickname FROM Users " +
                "WHERE user_email = '" + email +"'");
        String name = email;
        try {
            resultSet.next();

            if ((name = resultSet.getString(1)) == null) {
                name = email;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("main.jsp");
        request.setAttribute("name", name);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
