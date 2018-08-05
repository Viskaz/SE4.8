import java.io.*;
import java.util.ArrayList;

public class UserDAO {
    ArrayList<EnergyConsumeEntity> returnList = null;

    /**
     * @return a list of users
     */
    public String[] getUserNameList() {
        File myFile = new File("user");
        return myFile.list();
    }

    /**
     * @param userName given username
     * @param fileType "ele" or "gas"
     * @return the consuming record
     */
    public ArrayList<EnergyConsumeEntity> readCurrentFile(String userName, String fileType) {
        BufferedReader bufferedReader;
        returnList = new ArrayList<>();
        String oneLine;
        String[] infoElement;
        EnergyConsumeEntity myEnergy;
        try {
            File myFile = new File("user/" + userName, fileType + ".txt");
            FileReader fileReader = new FileReader(myFile);
            bufferedReader = new BufferedReader(fileReader);
            oneLine = bufferedReader.readLine();
            while (oneLine != null) {

                myEnergy = new EnergyConsumeEntity();
                infoElement = oneLine.split("#");
                double energy = Double.parseDouble(infoElement[0]);
                int year = Integer.parseInt(infoElement[1]);
                int month = Integer.parseInt(infoElement[2]);
                int date = Integer.parseInt(infoElement[3]);
                int hour = Integer.parseInt(infoElement[4]);
                int minute = Integer.parseInt(infoElement[5]);
                int second = Integer.parseInt(infoElement[6]);
                double bill = Double.parseDouble(infoElement[7]);

                myEnergy.setEnergy(energy);
                myEnergy.setYear(year);
                myEnergy.setMonth(month);
                myEnergy.setDay(date);
                myEnergy.setHour(hour);
                myEnergy.setMinute(minute);
                myEnergy.setSecond(second);
                myEnergy.setBill(bill);
                returnList.add(myEnergy);

                oneLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Unable to get the Energy information!");
            e.printStackTrace();
        }
        return returnList;
    }

    /**
     * @param userName the given username
     * @param fileType "ele" or "gas"
     * @param myEnergyConsumeEntity one record
     * @return a boolean value to tell whether or not the process has been successful
     */
    public static boolean writeCurrentFile(String userName, String fileType, EnergyConsumeEntity myEnergyConsumeEntity) {
        File myFile = new File("user/" + userName, fileType + ".txt");
        try {
            FileWriter fr = new FileWriter(myFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fr);
            bufferedWriter.write("\n" + myEnergyConsumeEntity.toString());
            bufferedWriter.close();
            fr.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method deals with the first consuming record when the user is new to the system
     * @param userName given username
     * @param fileType "ele" or "gas"
     * @param myEnergyConsumeEntity one record
     */
    public static void createFirstRecord(String userName, String fileType, EnergyConsumeEntity myEnergyConsumeEntity) {
        File myFile = new File("user/" + userName, fileType + ".txt");
        try {
            FileWriter fr = new FileWriter(myFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fr);
            bufferedWriter.write(myEnergyConsumeEntity.toString());
            bufferedWriter.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userName the given username
     * @return an User object, which contain all the required information
     */
    public UserEntity readUser(String userName) {
        File myFile1 = new File("user/" + userName + "/" + userName + ".txt");
        File myFile2 = new File("user/" + userName + "/budget.txt");
        String oneline;
        UserEntity user = new UserEntity();
        try {
            FileReader fileReader1 = new FileReader(myFile1);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            oneline = bufferedReader1.readLine();
            user.setUsername(userName);
            user.setPassword(oneline);
            FileReader fileReader2 = new FileReader(myFile2);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            oneline = bufferedReader2.readLine();
            String budget[] = oneline.split("#");
            user.setEleBudget(budget[0]);
            user.setGasBudget(budget[1]);
            bufferedReader1.close();
            bufferedReader2.close();
            fileReader1.close();
            fileReader2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @param type "ele" or "gas"
     * @param userName the given username
     * @param value "the intended budget to be set
     * @return a boolean value to decide whether or not the operation has been successful
     */
    public boolean updateBudget(String type, String userName, String value) {
        String budget = null;
        String temEle=null, temGas=null;
        UserEntity user = readUser(userName);
        File myFile = new File("user/" + userName + "/budget.txt");
        try {
            FileWriter writer = new FileWriter(myFile);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(type.equals("electricity")){
                temEle=value;
                temGas=user.getGasBudget();
            }else if(type.equals("gas")){
                temEle=user.getEleBudget();
                temGas=value;
            }

            bufferedWriter.write(temEle+"#"+temGas);
            bufferedWriter.close();
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param password then given new pass word
     * @param type "user" or "admin"
     * @param username the given username
     */
    public void updatePassword(String password,String type,String username){
        if(type.equals("user")){
            File myFile= new File("user/" +username+"/"+username+".txt");

            new AccountSession().setPassword(password);
            try {
                FileWriter writer = new FileWriter(myFile);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(password);
                bufferedWriter.close();
                writer.close();

            }catch(Exception e){
                e.printStackTrace();

            }

        }
        else if (type.equals("admin")){

        }
    }

}
