import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {

    public boolean checkSetPrice(String str) {
//        boolean ret=true;
//        if(str == null || str.equals("") ){
//            ret = false;
//            new CheckPriceGUI().setVisible(true);
//        }
//        else if(str != null && str.length()>0) {
//
//            for (int i = 0; i < str.length(); i++) {
//                char ch = str.charAt(i);
//                if (!Character.isDigit(ch)) {
//                    ret = false;
//                    break;
//                }
//            }
//        }
        boolean ret=true;
        if(str == null || str.equals("") ){
            ret = false;
            new CheckPriceGUI().setVisible(true);
        }
        else if(str != null && str.length()>0) {

//            for (int i = 0; i < str.length(); i++) {
//                char ch = str.charAt(i);
//                if (!Character.isDigit(ch)||!Character.) {
//                    ret = false;
//                    break;
//                }
            String regex="^\\d+(\\.\\d+)?$";
            Matcher m=Pattern.compile(regex).matcher(str);
            if(m.matches()==false) {
                new CheckPriceGUI().setVisible(true);
                return false;
            }
            return true;

        }

        new CheckPriceGUI().setVisible(true);
        return false;

    }


    /**
     * This method tells whether the input is a correct double number or not
     * @param num a String that needs to be checked
     * @return a boolean value
     */
    public boolean checkPositiveDouble(String num){   //check if the input is a double
        if(num == null || num.equals("") ){
            return false;
        }
        else {

            String regex="^\\d+(\\.\\d+)?$";
            Matcher m=Pattern.compile(regex).matcher(num);
            return m.matches();
        }
    }


    /**
     * @param str the input username
     * @return a boolean value
     */
    public boolean checkUserName(String str){
        String[] userNameList = new UserDAO().getUserNameList();
        boolean ret = true;
        if(str != null && str.length()>0){
            for(int i =0; i < userNameList.length; i++){
                if(userNameList[i].equals(str)){
                    ret = false;
                    break;
                }
            }
            for(int i=0;i<str.length();i++){
                char ch= str.charAt(i);
                if(!Character.isDigit(ch)&&!Character.isLetter(ch)){
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * @param str1 the first password
     * @param str2 the second password
     * @return a boolean value
     */
    public static boolean checkPasswordSetting(String str1, String str2){
        boolean ret;
        ret = str1.equals(str2);
        return ret;
    }
}