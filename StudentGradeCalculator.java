import java.util.Scanner;



public class StudentGradeCalculator {



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);



        System.out.print("Enter number of subjects: ");

        int subjectCount = input.nextInt();



        int totalMarks = 0;

        int[] marks = new int[subjectCount];



        for (int i = 0; i < subjectCount; i++) {

            System.out.print("Enter marks for subject " + (i + 1) + ": ");

            int score = input.nextInt();



            while (score < 0 || score > 100) {

                System.out.println("Invalid marks. Please enter a value between 0 and 100.");

                System.out.print("Re-enter marks for subject " + (i + 1) + ": ");

                score = input.nextInt();

            }



            marks[i] = score;

            totalMarks += score;

        }



        double average = (double) totalMarks / subjectCount;

        String grade;



        if (average >= 90) {

            grade = "A+";

        } else if (average >= 80) {

            grade = "A";

        } else if (average >= 70) {

            grade = "B";

        } else if (average >= 60) {

            grade = "C";

        } else if (average >= 50) {

            grade = "D";

        } else {

            grade = "F";

        }



        System.out.println("\n--- Result ---");

        System.out.println("Total Marks: " + totalMarks);

        System.out.printf("Average Percentage: %.2f%%\n", average);

        System.out.println("Grade: " + grade);

        input.close();

    }

}