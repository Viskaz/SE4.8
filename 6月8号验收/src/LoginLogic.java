import java.io.*;

public class LoginLogic {

    /**
     * @param username given username
     * @param password given password
     * @param filepath "admin" or "user"
     * @return a boolean value to to tell whether the password is correct or not
     */
    public static boolean checkPassword(String username, String password, String filepath) {
        boolean checkResult = false;
        String rightPassword;
        AccountSession session = new AccountSession();
        session.setUsername(username);
        session.setPassword(password);

        if (filepath.equals("admin")){

            File myFile = new File("./" + filepath, username + ".txt");
            if (myFile.exists()) {
                try {
                    FileReader fr = new FileReader(myFile);
                    BufferedReader buffReader = new BufferedReader(fr);
                    rightPassword = buffReader.readLine();
                    if (password.equals(rightPassword)) checkResult = true;
                    else checkResult = false;
                    buffReader.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                System.out.println("file not exists");
                checkResult = false;
            }
        }
        else if (filepath.equals("user")){

            File myFile= new File("./"+filepath+"/"+username,username+".txt");
            if (myFile.exists()) {
                try {
                    FileReader fr = new FileReader(myFile);
                    BufferedReader buffReader = new BufferedReader(fr);
                    rightPassword = buffReader.readLine();
                    if (password.equals(rightPassword)) checkResult = true; else checkResult = false;
                    buffReader.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("file not exists");
                checkResult = false;
            }
        }
        return checkResult;

    }

}
