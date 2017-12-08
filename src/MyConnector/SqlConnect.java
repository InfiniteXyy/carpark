package MyConnector;

import data.Info;
import data.user.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class SqlConnect {
    private Connection conn;

    private Statement statement;
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
            statement.executeUpdate("INSERT INTO table_name (Email, Password) VALUES "
                    +"('"+user.getEmail()+"','"+user.getPassword()+"')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkInfoFromDB(User user) {
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM table_name");
            while (resultSet.next()) {
                if (resultSet.getString("Email").equals(user.getEmail())) {
                    if (resultSet.getString("Password").equals(user.getPassword())) {
                        return User.RIGHTPW;
                    } else {
                        return User.WRONGPW;
                    }
                }
            }
            return User.NOUSER;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User.SYSERROR;
    }

    public Info getInfoNums() {
        Info info = new Info();

        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM table_name");
            while (resultSet.next()) {
                info.userNum++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
    public void endDB() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
