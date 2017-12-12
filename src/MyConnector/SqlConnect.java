package MyConnector;

import data.Info;
import data.user.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class SqlConnect {
    private Connection conn = null;
    private Statement statement = null;

    private static final String DBNAME = "java:comp/env/jdbc/test";

    //通过调用一次DatabaseConnection就连接了数据库
    public void startDB(){
        try {
            // 初始化查找命名空间
            Context ctx = new InitialContext();
            //找到这个数据库
            DataSource ds = (DataSource)ctx.lookup(DBNAME);
            conn = ds.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putInfoIntoDB(User user) {
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO Users (user_email, user_password) VALUES "
                    +"('"+user.getEmail()+"','"+user.getPassword()+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkInfoFromDB(User user) {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_password FROM Users WHERE user_email = '"
                + user.getEmail()+"'");
            if (!resultSet.next()) {
                return User.NOUSER;
            }
            resultSet.first();
            if (resultSet.getString(1).equals(user.getPassword())) {
                return User.RIGHTPW;
            } else return User.WRONGPW;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User.SYSERROR;
    }

    public Info getInfo() {
        Info info = new Info();

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_email FROM Users");
            while (resultSet.next()) {
                info.userNum++;
            }
            resultSet = statement.executeQuery("SELECT carport_id FROM Carports");
            while (resultSet.next()) {
                info.lotNum++;
            }
            resultSet = statement.executeQuery("SELECT car_id FROM Cars");
            while (resultSet.next()) {
                info.carNum++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void endDB() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endStmt() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
