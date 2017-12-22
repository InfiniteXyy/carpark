package data.park;


import connecter.SqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Carport{
    private int id;
    private String owner;
    private String state;
    private int leftnum;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLeftnum() {
        return leftnum;
    }

    public void setLeftnum(int leftnum) {
        this.leftnum = leftnum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRentBy(String email) {
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        boolean status = false;
        ResultSet resultSet = sqlConnect.executeQuery("SELECT 'isRent' AS status \n" +
                "FROM CarparkOrders \n" +
                "WHERE order_by = '"+email+"' AND order_park = "+id );
        try {
            if (resultSet.next()) {
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return status;
    }
}
