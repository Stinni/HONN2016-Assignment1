package is.ru.honn.P1_Salary;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 1:
 * Unit test class SalaryCalculatorTest (SalaryCalculatorTest.java)
 * for the SalaryCalculator (SalaryCalculator.java) class
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 04.09.16
 */
public class SalaryCalculatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private SalaryCalculator calculator;

    /**
     * The exit (ExpectedSystemExit) variable is set up to to be able to prevent
     * the SalaryCalculator from exiting. I first thought I'd need to have all the
     * tests in one function and since only the first testcase ran and then the
     * program exited, this is the solution I found. I then realised that the tests
     * should be in separate functions but decided to keep this and check the
     * exit codes.
     */
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        calculator = new SalaryCalculator();
    }

    @Test
    public void test1_ExitsWithExitCode4() throws Exception {
        int basePay = 450;
        int hours = 35;
        exit.expectSystemExitWithStatus(4);
        calculator.calculateSalary(basePay, hours);
        assertEquals("Base pay can't be lower than the minimum wage (500)", outContent.toString());
    }

    @Test
    public void test2_ReturnsTotalSalary() throws Exception {
        int basePay = 900;
        int hours = 47;
        double salary = 45450.0;
        assertEquals(salary, (double)calculator.calculateSalary(basePay, hours), 0.1);
    }

    @Test
    public void test3_ExitsWithExitCode5() throws Exception {
        int basePay = 1500;
        int hours = 73;
        exit.expectSystemExitWithStatus(5);
        calculator.calculateSalary(basePay, hours);
        assertEquals("Workers aren't allowed to work more than 60 hours per week!", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}