package Servlets.AddContent;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "CarportAdmin", urlPatterns = {"/Servlets.AddContent.CarportAdmin"})
public class CarportAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String owner = request.getParameter("owner");
        String state = request.getParameter("state");
        String num = request.getParameter("num");
        String price = request.getParameter("price");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        sqlConnect.updateQuery("INSERT INTO Carports (carport_owner, carport_state, carport_leftnum, carport_price)\n" +
                "VALUES ('"+owner+"', '"+state+"', "+num+", "+price+")");
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
