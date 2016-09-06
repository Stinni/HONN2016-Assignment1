package is.ru.honn.P2_BanksRUs.Accounts;

/**
 * Hönnun og Smíði Hugbúnaðar - Assignment 1, Part 2:
 * The Interface IOverdrawable (IOverdrawable.java)
 * Necessary variables and functions to make an account Overdrawable
 *
 * @author Kristinn Heiðar Freysteinsson
 * @version 1, 05.09.16
 */
public interface IOverdrawable {

    void setAllowedOverdrawAmount(double allowedOd);
    double getAllowedOverdrawAmount();
}
