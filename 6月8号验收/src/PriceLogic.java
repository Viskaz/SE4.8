import java.io.*;

public class PriceLogic implements PriceLogicInterface{
    /**
     * @param filename "ele" or "gas
     * @return the corresponding price
     */
    public double readPrice(String filename) {
        double priceResult = 0;
        File priceFile = new File("price", filename + "Price.txt");
        try {
            FileReader fr = new FileReader(priceFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            priceResult = Double.parseDouble(bufferedReader.readLine());
            bufferedReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceResult;
    }

    /**
     * @param filename "ele" or "gas"
     * @param price the new price that needs to write in the file
     * @return
     */
    public boolean writePrice(String filename, String price) {
        File priceFile = new File("price", filename + "Price.txt");
        try {
            FileWriter fw = new FileWriter(priceFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(String.valueOf(price) + "\n");
            bufferedWriter.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
