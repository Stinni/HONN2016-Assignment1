package is.ru.honn.P1_Salary;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 1:
 * The program SalaryCalculator (SalaryCalculator.java)
 * prints out the product of a base salary and worked hours
 * or prints out an error message and exits the program if input is incorrect
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class SalaryCalculator {

    // employees get paid time and a half for any hours over 40 per week
    private static final int MAX_NORMAL_HOURS = 40;
    // no worker can work more than 60 hours per week
    private static final int MAX_HOURS = 60;
    // The minimum wage is 500 ISK
    private static final int MINIMUM_WAGE = 500;

    public static void main(String[] args) {

        // The array args has to include at least 2 arguments
        if(args.length < 2) {
            System.out.println("Too few arguments. There has to be 2 integers!");
            System.exit(1);
        }

        int basePay = 0;
        int hours = 0;

        // Argument strings parsed to integers and if first 2 argumenta aren't integers,
        // an error message is printed and the program exited
        try {
            basePay = Integer.parseInt(args[0]);
            hours = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Arguments are in the wrong format. There has to be 2 integers!");
            System.exit(2);
        }

        int salary = calculateSalary(basePay, hours);
        System.out.println(salary);
    }

    /**
     * The function calculateSalary
     * Calculates the total salary or if the arguments aren't valid
     * it prints out an error message and exits the program.
     * @param basePay The base pay used to calculate the total salary
     * @param hours The number of hours that the employee has worked in a week
     * @return the total salary
     */
    public static int calculateSalary(int basePay, int hours) {

        // Checks if the parameters are valid. Print out corresponding error
        // messages if they're not and exits the program
        if(basePay < MINIMUM_WAGE && hours > MAX_HOURS) {
            System.out.println("Both arguments are invalid.\n" + "Base pay can't be lower than the minimum wage (" +
                    MINIMUM_WAGE + ") and workers aren't allowed to work more than " + MAX_HOURS + " hours per week!");
            System.exit(3);
        } else if(basePay < MINIMUM_WAGE) {
            System.out.println("Base pay can't be lower than the minimum wage (" + MINIMUM_WAGE + ")");
            System.exit(4);
        } else if(hours > MAX_HOURS) {
            System.out.println("Workers aren't allowed to work more than " + MAX_HOURS + " hours per week!");
            System.exit(5);
        }

        int salary = 0;

        if(hours > MAX_NORMAL_HOURS) {
            salary += MAX_NORMAL_HOURS * basePay;
            salary += (hours - MAX_NORMAL_HOURS) * (int)((double)basePay * 1.5);
        }
        else {
            salary += hours * basePay;
        }

        return salary;
    }
}
