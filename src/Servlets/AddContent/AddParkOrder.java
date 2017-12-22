package Servlets.AddContent;

import connecter.SqlConnect;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddParkOrder", urlPatterns = {"/Servlets.AddContent.AddParkOrder"})
public class AddParkOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String id = request.getParameter("orderId");
        String email = request.getParameter("orderBy");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        if (type.equals("add")) {
            sqlConnect.updateQuery("INSERT INTO CarparkOrders\n" +
                    "(order_park, order_time, order_by) VALUES\n" +
                    "  ("+id+", CURDATE(), '"+email+"')");
        } else {
            sqlConnect.updateQuery("DELETE FROM CarparkOrders\n" +
                    "WHERE order_park = "+id+"\n" +
                    "AND order_by = '"+email+"'");
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
