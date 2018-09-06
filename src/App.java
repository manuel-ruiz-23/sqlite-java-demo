import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        SqliteTest test = new SqliteTest();
        ResultSet rs;

        try {
            rs = test.displayUsers();

            while (rs.next()){
                System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
