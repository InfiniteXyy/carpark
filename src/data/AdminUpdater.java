package data;

import connecter.SqlConnect;
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

}
