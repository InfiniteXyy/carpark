package Servlets.AddContent;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddOrder", urlPatterns = {"/Servlets.AddContent.AddOrder"})
public class AddOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        String to  = request.getParameter("to");
        String ddl = request.getParameter("ddl");
        String car = request.getParameter("car");
        String money = request.getParameter("money");

        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT user_email FROM Users JOIN Cars WHERE car_owner = user_email AND car_id = " + car);

        try {
            resultSet.next();
            //验证请求信息是否正确。
            if (!to.equals(resultSet.getString(1))) {
                response.getWriter().print("error");
            } else {
                sqlConnect.updateQuery("INSERT INTO Orders (order_by, order_to, order_car, order_ddl, order_money) VALUES ('"+ from +"', '"+ to+ "', " + car +", '"+ddl+"', "+ money+")");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
