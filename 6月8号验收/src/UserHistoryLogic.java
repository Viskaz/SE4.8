import java.util.ArrayList;

public class UserHistoryLogic {
    private String enquiryType;
    private String userName;
    ArrayList<EnergyConsumeEntity> currentUserConsumeList;
    public ArrayList<UserHistoryEntity> returnHistoryList = new ArrayList<>();

    public UserHistoryLogic(String userName, String enquiryType) {
        /*
            enquiry type can be "ele" or "gas"
         */
        this.userName = userName;
        this.enquiryType = enquiryType;

    }

    public void HistoryByMonth() {
        returnHistoryList.clear();
        currentUserConsumeList = new UserDAO().readCurrentFile(userName, enquiryType);
        System.out.println("Reading success!");
        String billType = "" + currentUserConsumeList.get(0).getYear() + "-" + currentUserConsumeList.get(0).getMonth();
        int currentMonthFlag = currentUserConsumeList.get(0).getMonth();
        double currentMonthTotalUsage = 0;
        double currentMonthTotalPay = 0;
        //System.out.println(currentUserConsumeList.size());
        for (int i = 0; i < currentUserConsumeList.size(); i++) {

            EnergyConsumeEntity oneRecordEnergyConsumeEntity = currentUserConsumeList.get(i);
            //System.out.println(currentMonthFlag);
            if (oneRecordEnergyConsumeEntity.getMonth() != currentMonthFlag) {
                //this means that we enter into a new month

                UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
                newHistoryRecord.setUserName(userName);
                newHistoryRecord.setBillType(billType);
                newHistoryRecord.setUsage(currentMonthTotalUsage);
                newHistoryRecord.setBill(currentMonthTotalPay);
                //System.out.println("Set success");
                returnHistoryList.add(newHistoryRecord);
                //System.out.println("Add success");
                currentMonthFlag = oneRecordEnergyConsumeEntity.getMonth();
                billType = "" + currentUserConsumeList.get(0).getYear() + "-" + currentMonthFlag;
                currentMonthTotalPay = 0;
                currentMonthTotalUsage = 0;
            }
            currentMonthTotalUsage += oneRecordEnergyConsumeEntity.getEnergy();
            currentMonthTotalPay += oneRecordEnergyConsumeEntity.getBill();

        }
        //last month
        UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
        newHistoryRecord.setUserName(userName);
        newHistoryRecord.setBillType(billType);
        newHistoryRecord.setUsage(currentMonthTotalUsage);
        newHistoryRecord.setBill(currentMonthTotalPay);
        //System.out.println("Set success");
        returnHistoryList.add(newHistoryRecord);
        //System.out.println("Add success");

    }

    public void HistoryByWeek() {
        returnHistoryList.clear();
        currentUserConsumeList = new UserDAO().readCurrentFile(userName, enquiryType);
        System.out.println("Reading success!");
        EnergyConsumeEntity startDateConsumeEntity = currentUserConsumeList.get(0);
        /*int currentWeekFlag = new DateLogic().calcWeek(
                startDateConsumeEntity.getYear(), startDateConsumeEntity.getMonth(), startDateConsumeEntity.getDay()
        );*/
        int weekNumber;
        weekNumber = new DateLogic().calcDayOfYear(
                startDateConsumeEntity.getYear(), startDateConsumeEntity.getMonth(), startDateConsumeEntity.getDay()
        ) / 7 + 1;
        double currentWeekUsage = 0;
        double currentWeekPay = 0;
        String billType = "Week " + weekNumber + " of " + startDateConsumeEntity.getYear();

        for (int i = 0; i < currentUserConsumeList.size(); i++) {
            EnergyConsumeEntity oneRecordEnergyConsumeEntity = currentUserConsumeList.get(i);
            int currentWeekNumber = new DateLogic().calcDayOfYear(
                    oneRecordEnergyConsumeEntity.getYear(),
                    oneRecordEnergyConsumeEntity.getMonth(),
                    oneRecordEnergyConsumeEntity.getDay()
            ) / 7 + 1;
            if (currentWeekNumber!=weekNumber) {
                UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
                newHistoryRecord.setUserName(userName);
                newHistoryRecord.setBillType(billType);
                newHistoryRecord.setUsage(currentWeekUsage);
                newHistoryRecord.setBill(currentWeekPay);
                //System.out.println("Set success");
                returnHistoryList.add(newHistoryRecord);
                //System.out.println("Add success");
                weekNumber = currentWeekNumber;
                billType = "Week " + weekNumber + " of " + startDateConsumeEntity.getYear();
                currentWeekPay = 0;
                currentWeekUsage = 0;
            }
            currentWeekUsage += oneRecordEnergyConsumeEntity.getEnergy();
            currentWeekPay += oneRecordEnergyConsumeEntity.getBill();
        }
        UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
        newHistoryRecord.setUserName(userName);
        newHistoryRecord.setBillType(billType);
        newHistoryRecord.setUsage(currentWeekUsage);
        newHistoryRecord.setBill(currentWeekPay);
        //System.out.println("Set success");
        returnHistoryList.add(newHistoryRecord);
        //System.out.println("Add success");
    }

    public void HistoryByDay() {
        returnHistoryList.clear();
        currentUserConsumeList = new UserDAO().readCurrentFile(userName, enquiryType);
        System.out.println("Reading success!");
        String billType = "" + currentUserConsumeList.get(0).getYear() + "-" + currentUserConsumeList.get(0).getMonth() + "-"
                + currentUserConsumeList.get(0).getDay();
        int currentDateFlag = currentUserConsumeList.get(0).getDay();
        double currentDayTotalUsage = 0;
        double currentDayTotalPay = 0;

        for (int i = 0; i < currentUserConsumeList.size(); i++) {

            EnergyConsumeEntity oneRecordEnergyConsumeEntity = currentUserConsumeList.get(i);
            //System.out.println(currentMonthFlag);
            if (oneRecordEnergyConsumeEntity.getDay() != currentDateFlag) {

                UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
                newHistoryRecord.setUserName(userName);
                newHistoryRecord.setBillType(billType);
                newHistoryRecord.setUsage(currentDayTotalUsage);
                newHistoryRecord.setBill(currentDayTotalPay);
                //System.out.println("Set success");
                returnHistoryList.add(newHistoryRecord);
                //System.out.println("Add success");
                currentDateFlag = oneRecordEnergyConsumeEntity.getDay();
                billType = "" + oneRecordEnergyConsumeEntity.getYear() + "-" +
                                oneRecordEnergyConsumeEntity.getMonth() + "-" +
                                oneRecordEnergyConsumeEntity.getDay();
                currentDayTotalPay = 0;
                currentDayTotalUsage = 0;
            }
            currentDayTotalUsage += oneRecordEnergyConsumeEntity.getEnergy();
            currentDayTotalPay += oneRecordEnergyConsumeEntity.getBill();

        }

        //last month
        UserHistoryEntity newHistoryRecord = new UserHistoryEntity();
        newHistoryRecord.setUserName(userName);
        newHistoryRecord.setBillType(billType);
        newHistoryRecord.setUsage(currentDayTotalUsage);
        newHistoryRecord.setBill(currentDayTotalPay);
        //System.out.println("Set success");
        returnHistoryList.add(newHistoryRecord);
        //System.out.println("Add success");
    }
}
