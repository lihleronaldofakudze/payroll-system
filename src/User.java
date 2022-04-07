import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User extends DatabaseActions {
    int user_id;
    int role_id;
    String user_name;
    String user_email;
    String user_dob;
    String user_address;

    public User() {
    }

    public User(int user_id, int role_id, String user_name, String user_email, String user_dob, String user_address) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_dob = user_dob;
        this.user_address = user_address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_dob() {
        return user_dob;
    }

    public void setUser_dob(String user_dob) {
        this.user_dob = user_dob;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    @Override
    void menu() {
        System.out.println("User Menu");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Select");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.println();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    insert();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    select();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid choice");
        }
    }

    @Override
    void insert() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter role id");
            setRole_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter user name");
            setUser_name(scanner.nextLine());

            System.out.println("Enter user email");
            setUser_email(scanner.nextLine());

            System.out.println("Enter user dob");
            setUser_dob(scanner.nextLine());

            System.out.println("Enter user address");
            setUser_address(scanner.nextLine());

            String sql = "INSERT INTO user (role_id, user_name, user_email, user_dob, user_address) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.setString(2, getUser_name());
                preparedStatement.setString(3, getUser_email());
                preparedStatement.setString(4, getUser_dob());
                preparedStatement.setString(5, getUser_address());
                preparedStatement.executeUpdate();
                System.out.println("Inserted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid choice");
        }
    }

    @Override
    void update() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter user id");
            setUser_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter role id");
            setRole_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter user name");
            setUser_name(scanner.nextLine());

            System.out.println("Enter user email");
            setUser_email(scanner.nextLine());

            System.out.println("Enter user dob");
            setUser_dob(scanner.nextLine());

            System.out.println("Enter user address");
            setUser_address(scanner.nextLine());

            String sql = "UPDATE user SET role_id = ?, user_name = ?, user_email = ?, user_dob = ?, user_address = ? WHERE user_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.setString(2, getUser_name());
                preparedStatement.setString(3, getUser_email());
                preparedStatement.setString(4, getUser_dob());
                preparedStatement.setString(5, getUser_address());
                preparedStatement.setInt(6, getUser_id());
                preparedStatement.executeUpdate();
                System.out.println("Updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid choice");
        }
    }

    @Override
    void delete() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter user id");
            setUser_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM user WHERE user_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getUser_id());
                preparedStatement.executeUpdate();
                System.out.println("Deleted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid choice");
        }
    }

    @Override
    void select() {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("user_id") + " " + resultSet.getInt("role_id") + " "
                        + resultSet.getString("user_name") + " " + resultSet.getString("user_email") + " "
                        + resultSet.getString("user_dob") + " " + resultSet.getString("user_address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}