import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employee extends DatabaseActions {
    int employee_id;
    String employee_name;
    String employee_mobile;
    String employee_email;
    String employee_address;
    String employee_username;
    String employee_password;

    public Employee() {
    }

    public Employee(int employee_id, String employee_name, String employee_mobile, String employee_email,
            String employee_address, String employee_username, String employee_password) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_mobile = employee_mobile;
        this.employee_email = employee_email;
        this.employee_address = employee_address;
        this.employee_username = employee_username;
        this.employee_password = employee_password;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_mobile() {
        return employee_mobile;
    }

    public void setEmployee_mobile(String employee_mobile) {
        this.employee_mobile = employee_mobile;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_address() {
        return employee_address;
    }

    public void setEmployee_address(String employee_address) {
        this.employee_address = employee_address;
    }

    public String getEmployee_username() {
        return employee_username;
    }

    public void setEmployee_username(String employee_username) {
        this.employee_username = employee_username;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    @Override
    void menu() {
        System.out.println("Employee Menu");
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

            System.out.print("Enter employee name: ");
            setEmployee_name(scanner.nextLine());

            System.out.print("Enter employee mobile: ");
            setEmployee_mobile(scanner.nextLine());

            System.out.print("Enter employee email: ");
            setEmployee_email(scanner.nextLine());

            System.out.print("Enter employee address: ");
            setEmployee_address(scanner.nextLine());

            System.out.print("Enter employee username: ");
            setEmployee_username(scanner.nextLine());

            System.out.print("Enter employee password: ");
            setEmployee_password(scanner.nextLine());

            String query = "INSERT INTO employee (employee_name, employee_mobile, employee_email, employee_address, employee_username, employee_password) VALUES ('"
                    + getEmployee_name() + "', '" + getEmployee_mobile() + "', '" + getEmployee_email() + "', '"
                    + getEmployee_address() + "', '" + getEmployee_username() + "', '" + getEmployee_password() + "')";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                System.out.println("Employee added successfully");
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void update() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter employee id: ");
            setEmployee_id(scanner.nextInt());

            System.out.println("Enter employee name: ");
            setEmployee_name(scanner.nextLine());

            System.out.println("Enter employee mobile: ");
            setEmployee_mobile(scanner.nextLine());

            System.out.println("Enter employee email: ");
            setEmployee_email(scanner.nextLine());

            System.out.println("Enter employee address: ");
            setEmployee_address(scanner.nextLine());

            System.out.println("Enter employee username: ");
            setEmployee_username(scanner.nextLine());

            System.out.println("Enter employee password: ");
            setEmployee_password(scanner.nextLine());

            String query = "UPDATE employee SET employee_name = '" + getEmployee_name() + "', employee_mobile = '"
                    + getEmployee_mobile() + "', employee_email = '" + getEmployee_email() + "', employee_address = '"
                    + getEmployee_address() + "', employee_username = '" + getEmployee_username()
                    + "', employee_password = '"
                    + getEmployee_password() + "' WHERE employee_id = " + getEmployee_id();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                System.out.println("Employee updated successfully");
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void delete() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter employee id: ");
            setEmployee_id(scanner.nextInt());

            String query = "DELETE FROM employee WHERE employee_id = " + getEmployee_id();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                System.out.println("Employee deleted successfully");
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    void select() {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM employee";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Employee id: " + resultSet.getInt("employee_id"));
                System.out.println("Employee name: " + resultSet.getString("employee_name"));
                System.out.println("Employee mobile: " + resultSet.getString("employee_mobile"));
                System.out.println("Employee email: " + resultSet.getString("employee_email"));
                System.out.println("Employee address: " + resultSet.getString("employee_address"));
                System.out.println("Employee username: " + resultSet.getString("employee_username"));
                System.out.println("Employee password: " + resultSet.getString("employee_password"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}