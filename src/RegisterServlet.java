import MyConnector.SqlConnect;
import data.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();

        //这里应该检查是否有重复的用户存在
        int status = sqlConnect.checkInfoFromDB(user);


        sqlConnect.endStmt();
        sqlConnect.endDB();
        request.setAttribute("newName", user.getEmail());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
