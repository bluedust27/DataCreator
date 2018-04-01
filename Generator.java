/*
Creating random data
"Name,Surname,Address,City,Telephone,Email,Date of birth,Sex,Marital Status,Nationality,Country of birth,Gross annual income,Currency"
*/

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Generator {


        public static void main(String[] args) {

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("City.txt"));
                //String content = "Name,Surname,Address,City,Telephone,Email,Date of birth,Sex,Marital Status,Nationality,Country of birth,Gross annual income,Currency";
                String path = "Generator.csv";
                String readLine;
                File file = new File(path);

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                //bw.write(content);
                MyRandomGenerator msr = new MyRandomGenerator();

                while ((readLine = bufferedReader.readLine()) != null)
                {
                    long fone = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                    int income = (int) Math.floor(Math.random() * 50000) + 50000;
                    String lines= ""+msr.generateRandomString()+","+msr.generateRandomString()+","+msr.getRandomNumber()+"-"+msr.generateRandomString()+" St,"+readLine+","+msr.getCountryCode()+","+fone+","+msr.generateRandomString()+"."+msr.generateRandomString()+"@gl.com,"+msr.randomBirthday()+","+msr.getRandomGender()+","+msr.getRandomMaritial()+","+msr.getNationality()+","+msr.getCountry()+","+income+","+msr.getCurrency()+"";
                    bw.write(lines);
                    bw.newLine();
                }

                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}

 class MyRandomGenerator {

    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    public String generateRandomString() {

        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

     public String getCountryCode(){
         String[] names = { "IN", "US","FI","EN" };
         return names[(int) (Math.random() * names.length)];
     }

     public LocalDate randomBirthday() {
         return LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
     }

     public String getRandomGender(){
        String[] names = { "Male", "Female" };
         return names[(int) (Math.random() * names.length)];
     }

     public String getRandomMaritial(){
         String[] names = { "Single", "Married" };
         return names[(int) (Math.random() * names.length)];
     }

     public String getNationality(){
         String[] names = { "Indian", "Finnish","American","English" };
         return names[(int) (Math.random() * names.length)];
     }

     public String getCountry(){
         String[] names = { "India", "Finland","USA","England" };
         return names[(int) (Math.random() * names.length)];
     }

     public String getCurrency(){
         String[] names = { "USD", "CAD","INR" };
         return names[(int) (Math.random() * names.length)];
     }
}