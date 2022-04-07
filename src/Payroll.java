import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Payroll extends DatabaseActions {
    int payroll_id;
    String payroll_title;
    String payroll_description;
    String payroll_type;
    int employee_id;

    public Payroll() {
    }

    public Payroll(int payroll_id, String payroll_title, String payroll_description, String payroll_type,
            int employee_id) {
        this.payroll_id = payroll_id;
        this.payroll_title = payroll_title;
        this.payroll_description = payroll_description;
        this.payroll_type = payroll_type;
        this.employee_id = employee_id;
    }

    public int getPayroll_id() {
        return payroll_id;
    }

    public void setPayroll_id(int payroll_id) {
        this.payroll_id = payroll_id;
    }

    public String getPayroll_title() {
        return payroll_title;
    }

    public void setPayroll_title(String payroll_title) {
        this.payroll_title = payroll_title;
    }

    public String getPayroll_description() {
        return payroll_description;
    }

    public void setPayroll_description(String payroll_description) {
        this.payroll_description = payroll_description;
    }

    public String getPayroll_type() {
        return payroll_type;
    }

    public void setPayroll_type(String payroll_type) {
        this.payroll_type = payroll_type;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    void menu() {
        System.out.println("Payroll Menu");
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

            System.out.println("Enter payroll title: ");
            setPayroll_title(scanner.nextLine());

            System.out.println("Enter payroll description: ");
            setPayroll_description(scanner.nextLine());

            System.out.println("Enter payroll type: ");
            setPayroll_type(scanner.nextLine());

            System.out.println("Enter employee id: ");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            String sql = "INSERT INTO payroll (payroll_title, payroll_description, payroll_type, employee_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, getPayroll_title());
                preparedStatement.setString(2, getPayroll_description());
                preparedStatement.setString(3, getPayroll_type());
                preparedStatement.setInt(4, getEmployee_id());
                preparedStatement.executeUpdate();
                System.out.println("Payroll inserted successfully");
                System.out.println();
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

            System.out.println("Enter payroll id: ");
            setPayroll_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter payroll title: ");
            setPayroll_title(scanner.nextLine());

            System.out.println("Enter payroll description: ");
            setPayroll_description(scanner.nextLine());

            System.out.println("Enter payroll type: ");
            setPayroll_type(scanner.nextLine());

            System.out.println("Enter employee id: ");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            String sql = "UPDATE payroll SET payroll_title = ?, payroll_description = ?, payroll_type = ?, employee_id = ? WHERE payroll_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, getPayroll_title());
                preparedStatement.setString(2, getPayroll_description());
                preparedStatement.setString(3, getPayroll_type());
                preparedStatement.setInt(4, getEmployee_id());
                preparedStatement.setInt(5, getPayroll_id());
                preparedStatement.executeUpdate();
                System.out.println("Payroll updated successfully");
                System.out.println();
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

            System.out.println("Enter payroll id: ");
            setPayroll_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM payroll WHERE payroll_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, getPayroll_id());
                preparedStatement.executeUpdate();
                System.out.println("Payroll deleted successfully");
                System.out.println();
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

        String sql = "SELECT * FROM payroll";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Payroll id: " + resultSet.getInt("payroll_id"));
                System.out.println("Payroll title: " + resultSet.getString("payroll_title"));
                System.out.println("Payroll description: " + resultSet.getString("payroll_description"));
                System.out.println("Payroll type: " + resultSet.getString("payroll_type"));
                System.out.println("Employee id: " + resultSet.getInt("employee_id"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}