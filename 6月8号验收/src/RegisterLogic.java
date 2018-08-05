import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
public class RegisterLogic {

    /**
     * @param username the given username
     * @param password the given password
     * @param filepath "user"
     * @return
     */
    public static boolean createUser(String username, String password, String filepath) {
        boolean userFlag = false;
        if(username == null||username.equals("")) {
            new CheckPriceGUI().setVisible(true);
        }
        else{
            if (filepath.equals("user")) {

                File myFolder = new File("./" + filepath, username);
                if (myFolder.exists()) {
                    System.out.println("This name is already used");

                    userFlag = false;
                }
                else {
                    myFolder.mkdirs();
                    File myFile = new File("./" + filepath + "/" + username, username + ".txt");
                    try {
                        myFile.createNewFile();
                        FileWriter fw = new FileWriter(myFile, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(password);
                        bw.close();
                        fw.close();
                        userFlag = true;
                        File budget = new File("./" + filepath + "/" + username, "budget.txt");
                        budget.createNewFile();
                        FileWriter fw1 = new FileWriter(budget, true);
                        BufferedWriter bw1 = new BufferedWriter(fw1);
                        bw1.write("100kwh#100kwh");
                        bw1.close();
                        fw1.close();
                        userFlag = true;
                        MyDate x = new MyDate();
                        EnergyConsumeEntity energyConsumeEntity = new EnergyConsumeEntity(
                                0.0,
                                x.getYear(),
                                x.getMonth(),
                                x.getDate(),
                                x.getHour(),
                                x.getMinute(),
                                x.getSecond(),
                                0.0
                        );
                        File ele = new File("./" + filepath + "/" + username, "ele.txt");
                        ele.createNewFile();
                        UserDAO.createFirstRecord(username ,"ele", energyConsumeEntity);
                        File gas = new File("./" + filepath + "/" + username, "gas.txt");
                        gas.createNewFile();
                        UserDAO.createFirstRecord(username, "gas", energyConsumeEntity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                System.out.println("error");
                userFlag = false;
            }
        }
        return userFlag;
    }
}
