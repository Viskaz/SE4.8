public class AccountSession {
    private static String username;
    private static String password;
    boolean checkResult;

    /**
     * @return the current username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username that needs to be set
     */
    public void setUsername(String username) {
        AccountSession.username = username;
    }

    /**
     * @return the current user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password the user needs to be set
     */
    public void setPassword(String password) {
        AccountSession.password = password;
    }

    /**
     * @param passwordInput the password the user enters
     * @return return true for the correct password, and return wrong for the wrong password
     */
    public boolean changePasswordCheck(String passwordInput) {

        if(password.equals(passwordInput)){
            password = passwordInput;
            checkResult=true;
        }
        return checkResult;
    }

}
