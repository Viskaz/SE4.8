public class EnergyConsumeEntity {
    public EnergyConsumeEntity(){}
    public EnergyConsumeEntity(double energy,int year,int month,int day, int hour,int minute,int second,double bill){
        this.energy=energy;
        this.year= year;
        this.month= month;
        this.day=day;
        this.hour= hour;
        this.minute= minute;
        this.second=second;
        this.bill= bill;
    }
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    public void setBill(double bill) { this.bill = bill; }

    public double getBill() { return bill; }

    private double energy;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private double bill;

    @Override
    public String toString() {
        String entityResult;
        entityResult = "" + energy + "#" + year + "#" + month +  "#" + day + "#" + hour + "#" + minute + "#" + second + "#" + bill;
        return entityResult;
    }

}
