import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Permission extends DatabaseActions {
    int permission_id;
    int role_id;
    String permission_title;
    String permission_module;
    String permission_description;

    public Permission() {
    }

    public Permission(int permission_id, int role_id, String permission_title, String permission_module,
            String permission_description) {
        this.permission_id = permission_id;
        this.role_id = role_id;
        this.permission_title = permission_title;
        this.permission_module = permission_module;
        this.permission_description = permission_description;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getPermission_title() {
        return permission_title;
    }

    public void setPermission_title(String permission_title) {
        this.permission_title = permission_title;
    }

    public String getPermission_module() {
        return permission_module;
    }

    public void setPermission_module(String permission_module) {
        this.permission_module = permission_module;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    @Override
    void menu() {
        System.out.println("Permission Menu");
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

            System.out.println("Enter permission title");
            setPermission_title(scanner.nextLine());

            System.out.println("Enter permission module");
            setPermission_module(scanner.nextLine());

            System.out.println("Enter permission description");
            setPermission_description(scanner.nextLine());

            String sql = "INSERT INTO permission (role_id, permission_title, permission_module, permission_description) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.setString(2, getPermission_title());
                preparedStatement.setString(3, getPermission_module());
                preparedStatement.setString(4, getPermission_description());
                preparedStatement.executeUpdate();
                System.out.println("Record inserted successfully");
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

            System.out.println("Enter permission id");
            setPermission_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter role id");
            setRole_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter permission title");
            setPermission_title(scanner.nextLine());

            System.out.println("Enter permission module");
            setPermission_module(scanner.nextLine());

            System.out.println("Enter permission description");
            setPermission_description(scanner.nextLine());

            String sql = "UPDATE permission SET role_id = ?, permission_title = ?, permission_module = ?, permission_description = ? WHERE permission_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.setString(2, getPermission_title());
                preparedStatement.setString(3, getPermission_module());
                preparedStatement.setString(4, getPermission_description());
                preparedStatement.setInt(5, getPermission_id());
                preparedStatement.executeUpdate();
                System.out.println("Record updated successfully");
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

            System.out.println("Enter permission id");
            setPermission_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM permission WHERE permission_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getPermission_id());
                preparedStatement.executeUpdate();
                System.out.println("Record deleted successfully");
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

        String sql = "SELECT * FROM permission";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Role id: " + resultSet.getInt("role_id"));
                System.out.println("Permission title: " + resultSet.getString("permission_title"));
                System.out.println("Permission module: " + resultSet.getString("permission_module"));
                System.out.println("Permission description: " + resultSet.getString("permission_description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}