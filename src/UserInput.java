import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class UserInput
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product ID: ");
        int id = scanner.nextInt();

        String sql = "SELECT name FROM products WHERE id = ?";

        String url = "jdbc:postgresql://localhost:5432/Gani";
        String username = "postgres";
        String password = "6667";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sql))
        {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                String name = rs.getString(1);
                System.out.println("Product Name: " + name);
            }
            else
            {
                System.out.println("No product found with ID: " + id);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}