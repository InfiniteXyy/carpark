package Servlets.Commit;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderCommit", urlPatterns = {"/Servlets.Commit.OrderCommit"})
public class OrderCommit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        sqlConnect.updateQuery("UPDATE Orders SET order_accepted = 1 WHERE order_id = " + orderId);
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
