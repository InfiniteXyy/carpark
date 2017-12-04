package MyConnector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class SqlConnect {
    private Connection conn;

    private static final String DSNAME = "jdbc:mysql://localhost:3306/carOwner";

    public void DatabaseConnection() throws Exception {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(DSNAME);
            this.conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
