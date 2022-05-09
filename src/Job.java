import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Job extends DatabaseActions {
    int job_id;
    String job_title;
    String job_type;
    String job_description;

    public Job() {
    }

    public Job(int job_id, String job_title, String job_type, String job_description) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_type = job_type;
        this.job_description = job_description;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    @Override
    void menu() {
        System.out.println("Job Menu");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Select");
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
                    delete();
                    break;
                case 4:
                    select();
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
            System.out.println("Enter Job Title");
            setJob_title(scanner.nextLine());

            System.out.println("Enter Job Type");
            setJob_type(scanner.nextLine());

            System.out.println("Enter Job Description");
            setJob_description(scanner.nextLine());

            String sql = "INSERT INTO job (job_title, job_type, job_description) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getJob_title());
                preparedStatement.setString(2, getJob_type());
                preparedStatement.setString(3, getJob_description());
                preparedStatement.executeUpdate();
                System.out.println("Job Inserted");
                System.out.println();
                menu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void update() {
        try (Scanner scanner = new Scanner(System.in)) {
            Connection connection = DatabaseConnection.getConnection();

            System.out.println("Enter Job Id");
            setJob_id(Integer.parseInt(scanner.nextLine()));

            System.out.println("Enter Job Title");
            setJob_title(scanner.nextLine());

            System.out.println("Enter Job Type");
            setJob_type(scanner.nextLine());

            System.out.println("Enter Job Description");
            setJob_description(scanner.nextLine());

            String sql = "UPDATE job SET job_title = ?, job_type = ?, job_description = ? WHERE job_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, getJob_title());
                preparedStatement.setString(2, getJob_type());
                preparedStatement.setString(3, getJob_description());
                preparedStatement.setInt(4, getJob_id());
                preparedStatement.executeUpdate();
                System.out.println("Job Updated");
                System.out.println();
                menu();
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

            System.out.println("Enter Job Id");
            setJob_id(Integer.parseInt(scanner.nextLine()));

            String sql = "DELETE FROM job WHERE job_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getJob_id());
                preparedStatement.executeUpdate();
                System.out.println("Job Deleted");
                System.out.println();
                menu();
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

        String sql = "SELECT * FROM job";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Job Id: " + resultSet.getInt("job_id"));
                System.out.println("Job Title: " + resultSet.getString("job_title"));
                System.out.println("Job Type: " + resultSet.getString("job_type"));
                System.out.println("Job Description: " + resultSet.getString("job_description"));
                System.out.println();
            }
            menu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}