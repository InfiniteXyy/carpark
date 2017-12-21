package Servlets.Search;

import connecter.SqlConnect;
import data.park.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "CarDetails", urlPatterns = {"/Servlets.Search.CarDetails"})
public class CarDetails extends HttpServlet {
    private Car thisCar;
    private String ddl;
    private String toWhom;
    private boolean isAccepet;
    private boolean isRent;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carId = request.getParameter("carId");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT car_name, ifnull((SELECT concat(order_to, '&', order_ddl, '&', order_accepted) FROM Orders WHERE car_id = order_car  AND curtime() < order_ddl), 'null') AS isRent, ifnull((SELECT sum(order_money) FROM Orders WHERE car_id = order_car AND order_accepted = 1), 0) AS earnedMoney FROM Cars WHERE car_id = "+carId);
        thisCar = new Car();
        try {
            resultSet.next();
            thisCar.setRent(resultSet.getString(2));
            thisCar.setEarnedMoney(resultSet.getInt(3));
            thisCar.setName(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlConnect.endStmt();
        sqlConnect.endDB();
        renderHTML(response.getWriter());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void renderHTML(PrintWriter pw) {

        isRent = parseRent();
        pw.print("<div class=\"modal-header\">\n" +
                "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "          <span aria-hidden=\"true\">&times;</span>\n" +
                "          <span class=\"sr-only\">Close</span>\n" +
                "        </button>\n" +
                "        <h4 class=\"modal-title\">"+ thisCar.getName() +"</h4>\n" +
                "      </div>\n" +
                "      <div class=\"modal-body\">\n" +
                "        <p><b>Total Money Earned:  </b>" + thisCar.getEarnedMoney() +" yuan </p>\n");
        if (isRent) {
            pw.print("<p><b>Rent to:  </b>" + toWhom +"</p>\n");
            pw.print("<p><b>Rent DDL:  </b>" + ddl +"</p>\n");
        }
        pw.print("<p><b>Status:  </b>" + parseStatus() +"</p>\n");
        pw.print("      </div>\n" +
                "      <div class=\"modal-footer\">\n" +
                "        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "      </div>");

    }

    private Boolean parseRent() {
        if (thisCar.getRent().equals("null")) {
            return false;
        } else {
            String[] temp = thisCar.getRent().split("&");
            toWhom = temp[0];
            ddl = temp[1];
            isAccepet = (temp[2].equals("1"));
            return true;
        }
    }

    private String parseStatus() {
        if (!isRent) return "Available";
        if (isAccepet) return "Accepted";
        else return "Waiting";
    }
}
