import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AdminLogic implements AdminLogicInterface {
    final static int METER_DAY = 8;

    /**
     * This is the method to detect whether or not admin has successfully received the meter readings or not
     * @param userName the username that needs to be sent to the user
     * @return
     */
    public static String checkReceiveMeters(String userName) {
        String returnFlag;
        //0: not the meter_day
        //"321@983": meter day and not yet sent to the admin
        //-1: meter day but already sent to the admin
        MyDate x = new MyDate();
        if (x.getDate()==METER_DAY) {
            UserHistoryLogic userHistoryLogicEle = new UserHistoryLogic(userName, "ele");
            UserHistoryLogic userHistoryLogicGas = new UserHistoryLogic(userName, "gas");

            userHistoryLogicEle.HistoryByMonth();
            String monthStamp = userHistoryLogicEle.returnHistoryList.get(userHistoryLogicEle.returnHistoryList.size() - 1).getBillType();
            Double lastMonthMeterEle = userHistoryLogicEle.returnHistoryList.get(
                    userHistoryLogicEle.returnHistoryList.size() - 1
            ).getUsage();

            userHistoryLogicGas.HistoryByMonth();
            Double lastMonthMeterGas = userHistoryLogicGas.returnHistoryList.get(
                    userHistoryLogicGas.returnHistoryList.size() - 1
            ).getUsage();

            File myFile = new File("./admin/", userName + "-" + monthStamp);
            if (myFile.exists()) {
                returnFlag = "-1";
            }
            else {
                try {
                    myFile.createNewFile();
                    FileWriter fileWriter = new FileWriter(myFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write("" + String.format("%.0f", lastMonthMeterEle) + "\n");
                    bufferedWriter.write("" + String.format("%.0f", lastMonthMeterGas));
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                returnFlag = String.format("%.0f", lastMonthMeterEle) + "#" + String.format("%.0f", lastMonthMeterGas);
            }
        }
        else {
            returnFlag = "0";
        }
        return returnFlag;
    }

    /**
     * @param user the user object that needs to be add to the system
     * @return whether or not the option has been success
     */
    public boolean addUser(UserEntity user) {
        try {
            File userDir = new File("./user/" + user.getUsername());
            if (!userDir.exists()) {
                userDir.mkdir();
            }
            createUserFile(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param user the user object that needs a file to be created
     * @return whether the process has been successful
     */
    public boolean createUserFile(UserEntity user) {
        try {
            File budgetTxt = new File("./user/" + user.getUsername() + "/" + "budget.txt");
            File userTxt = new File("./user/" + user.getUsername() + "/" + user.getUsername() + ".txt");
            File eleTxt = new File("./user/" + user.getUsername() + "/" + "ele.txt");
            File gasTxt = new File("./user/" + user.getUsername() + "/" + "gas.txt");

            budgetTxt.createNewFile();
            userTxt.createNewFile();

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

            eleTxt.createNewFile();
            gasTxt.createNewFile();
            UserDAO.createFirstRecord(user.getUsername(), "ele", energyConsumeEntity);
            UserDAO.createFirstRecord(user.getUsername(), "gas", energyConsumeEntity);
            new UserDAO().updatePassword(user.getPassword(), "user", user.getUsername());
            initBudget(user.getUsername(), user.getGasBudget() + "kwh", user.getEleBudget() + "kwh");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param username the username that the admin wishes to delete
     * @return whether the operation has been successful
     */
    public boolean deleteUser(String username) {
        try {
//            File file = new File("./user/"+username);
            File dirFile = new File("./user/"+username);
            File userTxt = new File("./user/" + username+ "/" + username + ".txt");
            File budgetTxt = new File("./user/" + username + "/" + "budget.txt");
            File eleTxt = new File("./user/" + username + "/" + "ele.txt");
            File gasTxt = new File("./user/" + username + "/" + "gas.txt");
            if(userTxt.exists()) {
                userTxt.delete();
            }
            if(eleTxt.exists()){
                eleTxt.delete();
            }
            if(budgetTxt.exists()){
                budgetTxt.delete();
            }
            if(gasTxt.exists()){
                gasTxt.delete();
            }
            if(dirFile.exists()){
                dirFile.delete();
            }


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param userName the username indicates the file to be operated
     * @param gas the gas budget
     * @param ele the electricity budget
     * @return whether the operation has been successful
     */
    public  boolean initBudget(String userName, String gas, String ele){
        File myFile = new File("user/" + userName + "/budget.txt");
        try {
            FileWriter writer = new FileWriter(myFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(ele+"#"+gas);
            bufferedWriter.close();
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
