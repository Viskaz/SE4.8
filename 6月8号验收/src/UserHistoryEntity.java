import static java.lang.String.format;

public class UserHistoryEntity {
    // zhourui 2018-2 136.12 12361
    private String userName;
    private String billType;
    private double usage; //the usage of gas or ele
    private double bill; //the money it takes

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        String returnString = this.billType + " " + format("%.2f", this.usage) + " " + format("%.2f", this.bill);
        return returnString;
    }
}
