package MyConnector;

import data.user.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class SqlConnect {
    private Connection conn;

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

    }
    public boolean checkInfoFromDB(User user) {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Password FROM table_name WHERE Email="+user.getEmail());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endDB() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
