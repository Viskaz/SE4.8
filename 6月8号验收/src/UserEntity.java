public class UserEntity {
    private String username;
    private String password;
    private String gasBudget;
    private String eleBudget;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String gasBudget, String eleBudget) {
        this.username= username;
        this.password= password;
        this.gasBudget= gasBudget;
        this.eleBudget= eleBudget;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGasBudget() {
        return gasBudget;
    }

    public void setGasBudget(String gasBudget) {
        this.gasBudget = gasBudget;
    }

    public String getEleBudget() {
        return eleBudget;
    }

    public void setEleBudget(String eleBudget) {
        this.eleBudget = eleBudget;
    }


}
