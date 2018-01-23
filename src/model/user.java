/**
 *
 * @author Nilanka
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class user
{
    private String name;
    private int logins;
    private List<String> characters = new ArrayList<>();
    private List<Long> pressTimes = new ArrayList<>();
    private List<Long> interKeyTimes = new ArrayList<>();
    
    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins = logins;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getCharacters()
    {
        return characters;
    }

    public void setCharacters(List<String> characters)
    {
        this.characters = characters;
    }

    public List<Long> getPressTimes()
    {
        return pressTimes;
    }

    public void setPressTimes(List<Long> pressTimes)
    {
        this.pressTimes = pressTimes;
    }

    public List<Long> getInterKeyTimes()
    {
        return interKeyTimes;
    }

    public void setInterKeyTimes(List<Long> interKeyTimes)
    {
        this.interKeyTimes = interKeyTimes;
    }


}
