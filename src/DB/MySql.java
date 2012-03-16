package DB;



import com.mysql.jdbc.Statement;
import java.sql.*;

public class MySql {

    Connection conn = null;
    String host = "localhost", database, user, password;
    ///
    //Statement stmt = null;

    public MySql(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;

    }

    public boolean connect() {
       // System.out.println("bağlanıyore....");
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            if (conn == null || conn.isClosed()) {
                String conStr = "jdbc:mysql://" + host + "/" + database;// + "?user=" + user + "&password=" + password;
                conn = DriverManager.getConnection(conStr + "?user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8");
                //conn = DriverManager.getConnection(conStr);
                //System.out.println("Connection Successful..");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean close() {
        try {
            if (conn != null) {
                conn.close();
            }
            return true;
        } catch (Exception ex) {
            System.out.println("MySql close error..");
            ex.printStackTrace();
        }
        return false;
    }

    public int executeAndGetLastID(String sql) {
        ResultSet rs = null;
        int id = -1;
        try {
            //if (conn.isClosed()) {
            connect();
            //}
            Statement st = (Statement) conn.createStatement();
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("executeQuery Error.");
            e.printStackTrace();
        }
        close();
        return id;
    }

    public synchronized ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            //if (conn.isClosed()) {
            connect();
            //}
            rs = conn.createStatement().executeQuery(sql);
            //rs = stmt.getResultSet();
            //close();
            return rs;
        } catch (Exception e) {
            System.out.println("executeQuery Error.");
            e.printStackTrace();
        }
        close();
        return rs;
    }

    public synchronized  boolean execute(String sql) {
        ResultSet rs = null;
        try {
            //if (conn.isClosed()) {
            connect();
            //}
            //stmt = (Statement) ;
            //System.out.println("executing..");
            conn.createStatement().execute(sql);
            //System.out.println("executed.");
            close();
            //System.out.println("conn closed");
            return true;
        } catch (Exception e) {
            System.out.println("execute Error.");
            e.printStackTrace();
            close();
            return false;
        }

    }

    public static void main(String[] args) {
        MySql m = new MySql("bilmuh", "root", "");
        //m.connect();
        try {
            ResultSet b = m.executeQuery("select * from mezunlar");
            b.next();
            System.out.println("b=" + b.getString("mesaj"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
