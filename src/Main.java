import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        {
            String sql = "Select name from  products where id=8";

            String url = "jdbc:postgresql://localhost:5432/Gani";
            String username = "postgres";
            String password = "6667";

            Connection con = DriverManager.getConnection(url,username,password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            rs.next();

            String name = rs.getString(1);
            System.out.println(name);

            con.close();

        }
    }
}