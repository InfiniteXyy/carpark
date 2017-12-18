import MyConnector.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ChangeNickName", urlPatterns = {"/ChangeNickName"})
public class ChangeNickName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT COUNT(*) FROM Users WHERE user_nickname = '" +
                nickName + "'");
        try {
            resultSet.next();
            if (resultSet.getInt(1) == 0) {
                response.getWriter().print("ok");
                sqlConnect.updateQuery("UPDATE Users SET user_nickname = '" +
                        nickName + "' WHERE user_email = '" +
                        email + "'");
            }
            sqlConnect.endStmt();
            sqlConnect.endDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
