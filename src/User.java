import java.sql.Timestamp;

public class User {
    public String user;
    public String pass;
    public String timestamp;
    
    public User(String user, String pass)
    {
        this.user = user;
        this.pass = pass;
        Timestamp times = new Timestamp(System.currentTimeMillis());
        this.timestamp = times.toString();
    }
    
    public User()
    {
        this.user = null;
        this.pass = null;
        Timestamp times = new Timestamp(System.currentTimeMillis());
        this.timestamp = times.toString();
    }
}
