import java.sql.*;

public class SqliteTest {

    private static Connection con;
    private static boolean hasData = false;

    public ResultSet displayUsers() throws SQLException, ClassNotFoundException {
        if(con == null){
            getConnection();
        }

        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT fname, lname FROM user");
        return res;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLiteTest1.db");
        initialise();

    }

    private void initialise() throws SQLException {
        if (!hasData){
            hasData = true;
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
            if(!res.next()){
                System.out.println("Building the User table with prepopulated values");
                //need to buil a table
                Statement state2 = con.createStatement();
                state2.execute(
                        "CREATE TABLE user(id integer,"+
                        "fName varchar(60)," +
                        "lName varchar(60)," +
                        "primary key(id));"
                );

                // inserting some sampple data
                PreparedStatement prep = con.prepareStatement("INSERT INTO user VALUES(?,?,?);");
                prep.setInt(1,1);
                prep.setString(2,"Manuel");
                prep.setString(3,"Ruiz");
                prep.execute();

                PreparedStatement prep2 = con.prepareStatement("INSERT INTO user VALUES(?,?,?);");
                prep2.setInt(1,2);
                prep2.setString(2,"Arturo");
                prep2.setString(3,"Federico");
                prep2.execute();
            }
        }
    }

    public void addUser(String firstName, String lastName) throws SQLException, ClassNotFoundException {
        if(con == null){
            getConnection();
        }

        PreparedStatement prep = con.prepareStatement("INSERT INTO user VALUES(?,?,?);");
        prep.setString(2,firstName);
        prep.setString(3,lastName);
        prep.execute();
    }

}
