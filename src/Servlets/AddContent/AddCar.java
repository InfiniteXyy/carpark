package Servlets.AddContent;

import connecter.SqlConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCar", urlPatterns = {"/Servlets.AddContent.AddCar"})
public class AddCar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String pic = request.getParameter("pic");
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        sqlConnect.updateQuery("INSERT INTO Cars (car_picture, car_owner, car_name)\n" +
                "VALUES ('car"+pic+".jpg', '"+owner+"', '"+name+"')");
        sqlConnect.endStmt();
        sqlConnect.endDB();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
