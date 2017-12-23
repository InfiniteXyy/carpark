package data;

import connecter.SqlConnect;
import data.park.Carport;
import data.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminUpdater {
    public ArrayList<User> updateUsers() {
        ArrayList<User> arrayList = new ArrayList<>();
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT user_email, user_nickname, user_password, user_isAdmin FROM Users");
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setEmail(resultSet.getString(1));
                user.setNickname(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setAdmin(resultSet.getInt(4)==1);
                arrayList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return arrayList;
    }

    public ArrayList<Carport> updateParks() {
        ArrayList<Carport> arrayList = new ArrayList<>();
        SqlConnect sqlConnect = new SqlConnect();
        sqlConnect.startDB();
        ResultSet resultSet = sqlConnect.executeQuery("SELECT carport_id, carport_owner, carport_state, carport_price, carport_leftnum FROM Carports");
        try {

            while (resultSet.next()) {
                Carport carport = new Carport();
                carport.setId(resultSet.getInt(1));
                carport.setOwner(resultSet.getString(2));
                carport.setState(resultSet.getString(3));
                carport.setPrice(resultSet.getInt(4));
                carport.setLeftnum(resultSet.getInt(5));
                arrayList.add(carport);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return arrayList;
    }

}
