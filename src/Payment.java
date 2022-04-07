import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Payment extends DatabaseActions {
    int payment_id;
    String payment_amount;
    String payment_description;
    String payment_date;
    int employee_id;

    public Payment() {
    }

    public Payment(int payment_id, String payment_amount, String payment_description, String payment_date,
            int employee_id) {
        this.payment_id = payment_id;
        this.payment_amount = payment_amount;
        this.payment_description = payment_description;
        this.payment_date = payment_date;
        this.employee_id = employee_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_description() {
        return payment_description;
    }

    public void setPayment_description(String payment_description) {
        this.payment_description = payment_description;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    void menu() {
        System.out.println("Payment Menu");
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

            System.out.println("Enter payment amount");
            setPayment_amount(scanner.nextLine());

            System.out.println("Enter payment description");
            setPayment_description(scanner.nextLine());

            System.out.println("Enter payment date");
            setPayment_date(scanner.nextLine());

            System.out.println("Enter employee id");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            String sql = "INSERT INTO payment (payment_amount, payment_description, payment_date, employee_id) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getPayment_amount());
                preparedStatement.setString(2, getPayment_description());
                preparedStatement.setString(3, getPayment_date());
                preparedStatement.setInt(4, getEmployee_id());
                preparedStatement.executeUpdate();
                System.out.println("Payment inserted successfully");
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

            System.out.println("Enter payment id");
            setPayment_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter payment amount");
            setPayment_amount(scanner.nextLine());

            System.out.println("Enter payment description");
            setPayment_description(scanner.nextLine());

            System.out.println("Enter payment date");
            setPayment_date(scanner.nextLine());

            System.out.println("Enter employee id");
            setEmployee_id(Integer.parseInt(scanner.nextLine()));

            String sql = "UPDATE payment SET payment_amount = ?, payment_description = ?, payment_date = ?, employee_id = ? WHERE payment_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getPayment_amount());
                preparedStatement.setString(2, getPayment_description());
                preparedStatement.setString(3, getPayment_date());
                preparedStatement.setInt(4, getEmployee_id());
                preparedStatement.setInt(5, getPayment_id());
                preparedStatement.executeUpdate();
                System.out.println("Payment updated successfully");
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

            System.out.println("Enter payment id");
            setPayment_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM payment WHERE payment_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getPayment_id());
                preparedStatement.executeUpdate();
                System.out.println("Payment deleted successfully");
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

        String sql = "SELECT * FROM payment";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Payment id: " + resultSet.getInt("payment_id"));
                System.out.println("Payment amount: " + resultSet.getString("payment_amount"));
                System.out.println("Payment description: " + resultSet.getString("payment_description"));
                System.out.println("Payment date: " + resultSet.getString("payment_date"));
                System.out.println("Employee id: " + resultSet.getInt("employee_id"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}