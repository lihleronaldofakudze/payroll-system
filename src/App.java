import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static Employee employee;
    private static Job job;
    private static Payment payment;
    private static Payroll payroll;
    private static Permission permission;
    private static Role role;
    private static Salary salary;
    private static User user;

    public static void main(String[] args) throws Exception {
        DatabaseConnection.getConnection();

        menu();
    }

    public static void menu() {
        employee = new Employee();
        job = new Job();
        payment = new Payment();
        payroll = new Payroll();
        permission = new Permission();
        role = new Role();
        salary = new Salary();
        user = new User();

        System.out.println("Welcome to Payroll System");
        System.out.println("1. Employee");
        System.out.println("2. Job");
        System.out.println("3. Payment");
        System.out.println("4. Payroll");
        System.out.println("5. Permission");
        System.out.println("6. Role");
        System.out.println("7. Salary");
        System.out.println("8. User");
        System.out.println("9. Exit");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                employee.menu();
                break;
            case 2:
                job.menu();
                break;
            case 3:
                payment.menu();
                break;
            case 4:
                payroll.menu();
                break;
            case 5:
                permission.menu();
                break;
            case 6:
                role.menu();
                break;
            case 7:
                salary.menu();
                break;
            case 8:
                user.menu();
                break;
            default:
                System.out.println("Invalid choice");
                menu();
                break;
        }
    }
}
