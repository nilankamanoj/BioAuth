/**
*
* @author Nilanka
*/
package util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.user;

public class Authenticater
{
    private final int keySize=6;

    public user authenticate(List<user> users,List<Long> pressTimes, List<Long> interKeyTimes) throws SQLException
    {
        user user=null;

        for(user u : users)
        {
            int point = 0;
            for(int i =0;i<keySize;i++)
            {
                if(u.getPressTimes().get(i)*8/10 < pressTimes.get(i) && pressTimes.get(i)<u.getPressTimes().get(i)*12/10)
                {
                    point++;
                }
            }
            for(int i =0;i<keySize-2;i++)
            {
                if(u.getInterKeyTimes().get(i)*8/10 < interKeyTimes.get(i) && interKeyTimes.get(i)<u.getInterKeyTimes().get(i)*12/10)
                {
                    point++;
                }
            }

            System.out.println(u.getName()+" : "+ point);

            if(point>2*keySize-5)
            {
                user=u;
                UserRepo userRepo=new UserRepo();
                userRepo.updateUser(user, pressTimes, interKeyTimes);
                
            }
        }

        return user;
    }

}
