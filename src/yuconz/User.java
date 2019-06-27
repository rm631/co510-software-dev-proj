package yuconz;

/**
 *
 * @author rm631, ol61, mb859, ra466
 */
public class User {
    // Username/password are for the sake of testing auth in stage 3
    private String username;
    private String password;
    private String department;
    private String authLevel;
    
    /**
     * Constructor for objects of class User
     */
    public User(String username, String password, String department, String authLevel) {
        this.username = username;
        this.password = password;
        this.department = department;
        this.authLevel = authLevel;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getAuth() {
        return authLevel;
    }
    
    /**
     * authLevel should **only ever be set by Authenticate**
     * as users shouldn't be able to change auth level
     * without re-authenticating as per the specification
     * @param newAuth to set authLevel to
     */
    public void setAuthLevel(String newAuth) {
        authLevel = newAuth;
    }
}
