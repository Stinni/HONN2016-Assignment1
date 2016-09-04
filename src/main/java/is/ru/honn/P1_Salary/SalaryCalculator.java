package is.ru.honn.P1_Salary;

/**
 * Forritið SalaryCalculator (SalaryCalculator.java)
 * prentar út margfeldi af grunnlaunum og unnum tímum eða villu
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 03.09.16
 */
public class SalaryCalculator {

    // employees get paid time and a half for any hours over 40 per week
    private static final int MAX_NORMAL_HOURS = 40;
    // no worker can work more than 60 hours per week
    private static final int MAX_HOURS = 60;
    // The minimum wage is 500 ISK
    private static final int MINIMUM_WAGE = 500;
    private static int basePay, hours;

    public static void main(String[] args) {

        if(args.length < 2) {
            System.out.println("Too few arguments. There has to be 2 integers!");
            System.exit(1);
        }

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

    private static int calculateSalary(int basePay, int hours) {

        if(basePay < 500 && hours > MAX_HOURS) {
            System.out.println("Both arguments are invalid.\n" +
                    "Base pay can't be lower than the minimum wage (" +
                    MINIMUM_WAGE + ") and workers aren't allowed to work more than 60 hours per week!");
            System.exit(3);
        } else if(basePay < MINIMUM_WAGE) {
            System.out.println("Base pay can't be lower than the minimum wage (" + MINIMUM_WAGE + ")");
            System.exit(4);
        } else if(hours > MAX_HOURS) {
            System.out.println("Workers aren't allowed to work more than 60 hours per week!");
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
