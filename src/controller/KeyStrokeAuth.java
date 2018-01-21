/**
 *
 * @author Nilanka
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import model.user;
import view.Signin;
import util.UserRepo;

public class KeyStrokeAuth
{

    public static void main(String[] args) throws SQLException
    {
        Signin sign = new Signin();
        sign.runSignIn();
    }

}
