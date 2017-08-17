package serialize;

import model.Advertisement;
import model.User;

import java.io.*;
import java.util.List;
import java.util.Map;


public class serializeUtil {

    public static void serializeUserMap(Map<String, User> userMap) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Users\\Hasmik\\Desktop\\Streams\\User.txt"))) {
            out.writeObject(userMap);


        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception while serialization.");
            e.printStackTrace();
        }


    }

    public static Map<String, User> deserializeUserMap() {
        Map<String, User> userMap = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\Users\\Hasmik\\Desktop\\Streams\\User.txt"))) {

            userMap = (Map<String, User>) in.readObject();

        } catch (IOException e) {
            System.out.println("Exception while deserialization.");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userMap;
    }

    public static void serializeAdvertisementList (List<Advertisement> adsList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\Users\\Hasmik\\Desktop\\Streams\\Ads.txt"))) {
            outputStream.writeObject(adsList);

        } catch (IOException e) {
            System.out.println("Exception while serialization.");
            e.printStackTrace();
        }

    }

    public static List<Advertisement> deserializeAdvertisementList () {
        List<Advertisement> adsList = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\Users\\Hasmik\\Desktop\\Streams\\Ads.txt"))) {
           adsList = (List<Advertisement>)inputStream.readObject();


        } catch (IOException e) {
            System.out.println("Exception while deserialization.");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        return adsList;
    }
}
