
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
        boolean Admin = request.getParameter("isAdmin").equals("true");
        String name = email;
        try {
            resultSet.next();

            if ((name = resultSet.getString(1)) == null) {
                name = email;
            }
            if(Admin) {
                ResultSet resultSet1 = sqlConnect.executeQuery("SELECT user_isAdmin FROM Users WHERE user_email='"+email+"'");
                resultSet1.next();
                if (resultSet1.getInt(1) == 0) {
                    Admin = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlConnect.endStmt();
        sqlConnect.endDB();

        if (Admin) {
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("AdminMain.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher;
            dispatcher = request.getRequestDispatcher("main.jsp");
            request.setAttribute("name", name);
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
