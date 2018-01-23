/**
*
* @author Nilanka
*/
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user;

public class UserRepo
{

    private String dbUrl = "jdbc:derby:keyauth;create=true;user=keyauth;password=keyauth";
    private String table = "users";
    private Connection conn = null;
    private Statement stmt = null;
    private final int keySize =6;

    public UserRepo() throws SQLException
    {
        createConnection();
        Statement stmtt=conn.createStatement();
        if(!conn.getMetaData().getSchemas().next())
        {
            stmtt.execute("create schema keyauth");
        }
        if(!conn.getMetaData().getTables(null, null, "USERS", null).next())
        {
            stmtt.execute("CREATE TABLE USERS(ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1),NAME VARCHAR(100),PRESS_TIMES VARCHAR(100),INTER_TIMES VARCHAR(100),AUTH_KEY VARCHAR(100), LOGINS INT DEFAULT 0 NOT NULL)");
        }
        System.out.println(conn.getMetaData().getURL());

    }
    
    private void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(dbUrl);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void saveUser(user user)
    {
        try
        {
            PreparedStatement preStmt = conn.prepareStatement("INSERT INTO "+table
            +"(NAME,AUTH_KEY,PRESS_TIMES,INTER_TIMES) VALUES"
            +"(?,?,?,?)");

            preStmt.setString(1,user.getName());
            preStmt.setString(2,user.getCharacters().toString().replace("[", "").replace("]", ""));
            preStmt.setString(3,user.getPressTimes().toString().replace("[", "").replace("]", ""));
            preStmt.setString(4,user.getInterKeyTimes().toString().replace("[", "").replace("]", ""));

            preStmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<user> getUsers(String key) throws SQLException
    {
        List<user> users = new ArrayList<>();
        PreparedStatement preStmt = conn.prepareStatement("SELECT * FROM USERS WHERE AUTH_KEY =?");
        preStmt.setString(1, key);
        ResultSet rs = preStmt.executeQuery();
        while(rs.next())
        {
            user user=new user();
            user.setName(rs.getString(2));
            user.setPressTimes(getList(rs.getString(3).split(",")));
            user.setInterKeyTimes(getList(rs.getString(4).split(",")));
            user.setLogins(rs.getInt(6));
            users.add(user);
        }
        return users;
    }

    private List<Long> getList(String[] arr)
    {
        List<Long> list = new ArrayList<>();
        for(String e:arr)
        {
            list.add(Long.parseLong(e.trim()));
        }
        return list;
    }
    
    public void updateUser(user user,List<Long> pressTimes, List<Long> interKeyTimes) throws SQLException{
        PreparedStatement preStmt = conn.prepareStatement("UPDATE USERS SET PRESS_TIMES=?, INTER_TIMES=?,LOGINS=? WHERE AUTH_KEY=? AND PRESS_TIMES=? AND INTER_TIMES=? AND NAME=?");
        preStmt.setString(1, getNewValues(user.getPressTimes(), pressTimes,user.getLogins()).toString().replace("[", "").replace("]", ""));
        preStmt.setString(2, getNewValues(user.getInterKeyTimes(), interKeyTimes,user.getLogins()).toString().replace("[", "").replace("]", ""));
        preStmt.setInt(3, user.getLogins()+1);
        preStmt.setString(4, user.getCharacters().toString().replace("[", "").replace("]", ""));
        preStmt.setString(5, user.getPressTimes().toString().replace("[", "").replace("]", ""));
        preStmt.setString(6, user.getInterKeyTimes().toString().replace("[", "").replace("]", ""));
        preStmt.setString(7,user.getName());
    }
    private List<Long> getNewValues(List<Long> oldLst, List<Long> newLst, int logins){
        List<Long> updLst = new ArrayList<>();
        for(int i=0;i<oldLst.size();i++){
            updLst.add((((3+logins)*oldLst.get(i))+newLst.get(i))/(4+logins));
        }
        return updLst;
    }
}
