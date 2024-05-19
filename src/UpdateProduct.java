import java.sql.*;
import java.util.Scanner;

public class UpdateProduct {
    private static final String url = "jdbc:postgresql://localhost:5432/Gani";
    private static final String username = "postgres";
    private static final String password = "6667";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. View product by ID");
            System.out.println("2. Update product name");
            System.out.println("3. Update product price");
            System.out.println("4. Update product description");
            System.out.println("5. Delete product");
            System.out.println("6. List all products");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the product ID: ");
                    int viewId = scanner.nextInt();
                    viewProductById(viewId);
                    break;
                case 2:
                    System.out.print("Enter the product ID to update: ");
                    int updateNameId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the new product name: ");
                    String newName = scanner.nextLine();
                    updateProductName(updateNameId, newName);
                    break;
                case 3:
                    System.out.print("Enter the product ID to update: ");
                    int updatePriceId = scanner.nextInt();
                    System.out.print("Enter the new product price: ");
                    double newPrice = scanner.nextDouble();
                    updateProductPrice(updatePriceId, newPrice);
                    break;
                case 4:
                    System.out.print("Enter the product ID to update: ");
                    int updateDescId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the new product description: ");
                    String newDescription = scanner.nextLine();
                    updateProductDescription(updateDescId, newDescription);
                    break;
                case 5:
                    System.out.print("Enter the product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteProduct(deleteId);
                    break;
                case 6:
                    listAllProducts();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void viewProductById(int id) {
        String sql = "SELECT name, price, description FROM products WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                System.out.println("Product Name: " + name);
                System.out.println("Price: $" + price);
                System.out.println("Description: " + description);
            } else {
                System.out.println("No product found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProductName(int id, String newName) {
        String sqlUpdate = "UPDATE products SET name = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sqlUpdate)) {

            pst.setString(1, newName);
            pst.setInt(2, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product name updated successfully.");
            } else {
                System.out.println("No product found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProductPrice(int id, double newPrice) {
        String sqlUpdate = "UPDATE products SET price = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sqlUpdate)) {

            pst.setDouble(1, newPrice);
            pst.setInt(2, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product price updated successfully.");
            } else {
                System.out.println("No product found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProductDescription(int id, String newDescription) {
        String sqlUpdate = "UPDATE products SET description = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sqlUpdate)) {

            pst.setString(1, newDescription);
            pst.setInt(2, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product description updated successfully.");
            } else {
                System.out.println("No product found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        String sqlDelete = "DELETE FROM products WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = con.prepareStatement(sqlDelete)) {

            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listAllProducts() {
        String sqlList = "SELECT id, name FROM products";
        try (Connection con = DriverManager.getConnection(url, username, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sqlList)) {

            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                System.out.println("ID: " + productId + ", Name: " + productName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
