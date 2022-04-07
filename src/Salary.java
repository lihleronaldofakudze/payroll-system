import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Salary extends DatabaseActions {
    int salary_id;
    int employee_id;
    String salary_type;
    String salary_description;
    String salary_amount;
    String salary_total;

    public Salary() {
    }

    public Salary(int salary_id, int employee_id, String salary_type, String salary_description, String salary_amount,
            String salary_total) {
        this.salary_id = salary_id;
        this.employee_id = employee_id;
        this.salary_type = salary_type;
        this.salary_description = salary_description;
        this.salary_amount = salary_amount;
        this.salary_total = salary_total;
    }

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(String salary_type) {
        this.salary_type = salary_type;
    }

    public String getSalary_description() {
        return salary_description;
    }

    public void setSalary_description(String salary_description) {
        this.salary_description = salary_description;
    }

    public String getSalary_amount() {
        return salary_amount;
    }

    public void setSalary_amount(String salary_amount) {
        this.salary_amount = salary_amount;
    }

    public String getSalary_total() {
        return salary_total;
    }

    public void setSalary_total(String salary_total) {
        this.salary_total = salary_total;
    }

    @Override
    void menu() {
        System.out.println("Salary Menu");
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

            System.out.println("Enter Employee ID");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter Salary Type");
            setSalary_type(scanner.nextLine());

            System.out.println("Enter Salary Description");
            setSalary_description(scanner.nextLine());

            System.out.println("Enter Salary Amount");
            setSalary_amount(scanner.nextLine());

            System.out.println("Enter Salary Total");
            setSalary_total(scanner.nextLine());

            String query = "INSERT INTO salary (employee_id, salary_type, salary_description, salary_amount, salary_total) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, getEmployee_id());
                preparedStatement.setString(2, getSalary_type());
                preparedStatement.setString(3, getSalary_description());
                preparedStatement.setString(4, getSalary_amount());
                preparedStatement.setString(5, getSalary_total());
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

            System.out.println("Enter Employee ID");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter Salary Type");
            setSalary_type(scanner.nextLine());

            System.out.println("Enter Salary Description");
            setSalary_description(scanner.nextLine());

            System.out.println("Enter Salary Amount");
            setSalary_amount(scanner.nextLine());

            System.out.println("Enter Salary Total");
            setSalary_total(scanner.nextLine());

            String query = "UPDATE salary SET employee_id = ?, salary_type = ?, salary_description = ?, salary_amount = ?, salary_total = ? WHERE salary_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, getEmployee_id());
                preparedStatement.setString(2, getSalary_type());
                preparedStatement.setString(3, getSalary_description());
                preparedStatement.setString(4, getSalary_amount());
                preparedStatement.setString(5, getSalary_total());
                preparedStatement.setInt(6, getSalary_id());
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

            System.out.println("Enter Employee ID");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            String query = "DELETE FROM salary WHERE employee_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, getEmployee_id());
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
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM salary";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("Salary ID: " + resultSet.getInt("salary_id"));
                    System.out.println("Employee ID: " + resultSet.getInt("employee_id"));
                    System.out.println("Salary Type: " + resultSet.getString("salary_type"));
                    System.out.println("Salary Description: " + resultSet.getString("salary_description"));
                    System.out.println("Salary Amount: " + resultSet.getString("salary_amount"));
                    System.out.println("Salary Total: " + resultSet.getString("salary_total"));
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid choice");
        }
    }
}