public interface AdminLogicInterface {
    public boolean addUser(UserEntity user);
    public boolean createUserFile(UserEntity user);
    public boolean deleteUser(String username);;
    public boolean initBudget(String userName, String gas, String ele);


}
