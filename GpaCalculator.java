import java.util.Scanner;
public class GpaCalculator {

    public static void main(String[] args) {
        String course1, course2, course3, course4;
        int grade1, grade2, grade3, grade4;
        int credit1, credit2, credit3, credit4;

        Scanner course = new Scanner(System.in);

        System.out.print("Course: ");
        course1 = course.nextLine();
        System.out.print(course1 + " Grade: ");
        grade1 = course.nextInt();
        System.out.print("Credits: ");
        credit1 = course.nextInt();
        course.nextLine();

        System.out.print("Course: ");
        course2 = course.nextLine();
        System.out.print(course2 +" Grade: ");
        grade2 = course.nextInt();
        System.out.print("Credits: ");
        credit2 = course.nextInt();
        course.nextLine();

        System.out.print("Course: ");
        course3 = course.nextLine();
        System.out.print(course3 +" Grade: ");
        grade3 = course.nextInt();
        System.out.print("Credits: ");
        credit3 = course.nextInt();
        course.nextLine();

        System.out.print("Course: ");
        course4 = course.nextLine();
        System.out.print(course4 +" Grade: ");
        grade4 = course.nextInt();
        System.out.print("Credits: ");
        credit4 = course.nextInt();

        int total = grade1 * credit1 + grade2 * credit2 + grade3 * credit3 + grade4 * credit4;
        double gpa = (double) total / (credit1 + credit2 + credit3 + credit4);
        System.out.println("Your GPA calculated over four course totals: " +gpa);

        String ifPass = (gpa > 60)? "Pass" : "Fail";
        System.out.println("State: " +ifPass);
    }
}
