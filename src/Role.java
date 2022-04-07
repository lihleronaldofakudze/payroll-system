import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Role extends DatabaseActions {
    int role_id;
    String role_title;
    String role_description;

    public Role() {
    }

    public Role(int role_id, String role_title, String role_description) {
        this.role_id = role_id;
        this.role_title = role_title;
        this.role_description = role_description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    @Override
    void menu() {
        System.out.println("Role Menu");
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

            System.out.println("Enter role title");
            setRole_title(scanner.nextLine());

            System.out.println("Enter role description");
            setRole_description(scanner.nextLine());

            String sql = "INSERT INTO role (role_id, role_title, role_description) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.setString(2, getRole_title());
                preparedStatement.setString(3, getRole_description());
                preparedStatement.executeUpdate();
                System.out.println("Inserted successfully");
            } catch (Exception e) {
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

            System.out.println("Enter role id");
            setRole_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter role title");
            setRole_title(scanner.nextLine());

            System.out.println("Enter role description");
            setRole_description(scanner.nextLine());

            String sql = "UPDATE role SET role_title = ?, role_description = ? WHERE role_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getRole_title());
                preparedStatement.setString(2, getRole_description());
                preparedStatement.setInt(3, getRole_id());
                preparedStatement.executeUpdate();
                System.out.println("Updated successfully");
            } catch (Exception e) {
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

            System.out.println("Enter role id");
            setRole_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM role WHERE role_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getRole_id());
                preparedStatement.executeUpdate();
                System.out.println("Deleted successfully");
            } catch (Exception e) {
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

        String sql = "SELECT * FROM role";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getRole_id());
            preparedStatement.executeQuery();
            System.out.println("Selected successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}