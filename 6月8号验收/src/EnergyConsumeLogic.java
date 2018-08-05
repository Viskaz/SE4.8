import java.util.ArrayList;


public class EnergyConsumeLogic implements EnergyConsumeLogicInterface {

    ArrayList<EnergyConsumeEntity> energyConsumeEntityList;

    /**
     * This method tries to "get back the data" during the period that the user didn't log in.
     * @param userName the given username
     * @param fileType "ele" or "gas"
     */
    public void init(String userName, String fileType) {
    	energyConsumeEntityList = new UserDAO().readCurrentFile(userName, fileType);

        //get all the record before
        EnergyConsumeEntity lastEnergyEntity = energyConsumeEntityList.get(energyConsumeEntityList.size() - 1);

        //get the last record
        // like 7.6#2018#4#3#11#14#3#27
        MyDate lastEnergyDate = new MyDate(lastEnergyEntity.getYear(),
                                        lastEnergyEntity.getMonth(),
                                        lastEnergyEntity.getDay(),
                                        lastEnergyEntity.getHour(),
                                        lastEnergyEntity.getMinute(),
                                        lastEnergyEntity.getSecond());

        // get the date
        // like 2018, 4, 3, 11, 14, 3, 27

        System.out.print("上次更新时间:" + lastEnergyDate.getYear() + " " + lastEnergyDate.getMonth() + " " + lastEnergyDate.getDate() + "\n");
        //print out the last record date.

        MyDate nowDate = new DateLogic().getCurrentTime();

        double energyDateDiffer = new DateLogic().DifferTransform2Day(nowDate, lastEnergyDate);

        if (energyDateDiffer < 1) {return; }

        int energyDateDifferInt = (int) Math.floor(energyDateDiffer);
        int energyDateDifferDouble = (int)Math.floor((energyDateDiffer - energyDateDifferInt) * 86400);
        System.out.println(energyDateDifferInt);
        ArrayList<MyDate> newDateRecordList = new DateLogic().ergodicDate(lastEnergyDate, energyDateDifferInt);
        // newDateRecordList contains all the date
        EnergyConsumeEntity tempEnergyConsumeEntity;
        for (int j = 0; j < newDateRecordList.size(); j += 1) {
            MyDate tempDate = newDateRecordList.get(j);
            tempEnergyConsumeEntity = generateRecord(tempDate,fileType, 3.0);
            UserDAO.writeCurrentFile(userName, fileType, tempEnergyConsumeEntity);
        }
    }

    public EnergyConsumeEntity generateRecord(MyDate x, String fileType, Double baseNumber) {
        EnergyConsumeEntity tempEnergyConsumeEntity = new EnergyConsumeEntity();
        double price = new PriceLogic().readPrice(fileType);
        double temEnergy=Math.random()*baseNumber;
        tempEnergyConsumeEntity.setEnergy(temEnergy);
        tempEnergyConsumeEntity.setYear(x.getYear());
        tempEnergyConsumeEntity.setMonth(x.getMonth());
        tempEnergyConsumeEntity.setDay(x.getDate());
        tempEnergyConsumeEntity.setHour(x.getHour());
        tempEnergyConsumeEntity.setMinute(x.getMinute());
        tempEnergyConsumeEntity.setSecond(x.getSecond());
        tempEnergyConsumeEntity.setBill(price * temEnergy);
        // print out every possible record
        return tempEnergyConsumeEntity;
    }
}
