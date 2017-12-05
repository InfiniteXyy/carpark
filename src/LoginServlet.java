import MyConnector.SqlConnect;
import data.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        System.out.println(email + password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        request.setAttribute("name", user.getEmail());
        //链接数据库，检查是否存在user信息
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        RequestDispatcher dispatcher;

        int status = sqlConnect.checkInfoFromDB(user);

        switch (status) {
            case User.RIGHTPW:
                dispatcher = request.getRequestDispatcher("main.jsp");
                break;
            case User.NOUSER:
                dispatcher = request.getRequestDispatcher("register.html");
                break;
            case User.WRONGPW:
                dispatcher = request.getRequestDispatcher("index.html");
                break;
            default:
                dispatcher = request.getRequestDispatcher("index.html");
                break;
        }

        dispatcher.forward(request, response);
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
