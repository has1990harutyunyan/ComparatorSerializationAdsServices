package data;


import model.Advertisement;
import model.User;
import comparator.SortByDate;
import comparator.SortUserByName;
import serialize.serializeUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static serialize.serializeUtil.*;

public class DataStorage {

    private static DataStorage instance;


    private Map<String, User> users;
    private List<Advertisement> advertisements;

    private DataStorage() {
        users = new HashMap<>();
        advertisements = new ArrayList<>(16);
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void serializedData() throws IOException {

        File f = new File("D:\\Users\\Hasmik\\Desktop\\Streams\\User.txt");
        if (f.exists() && f.length() != 0) {
          users =  deserializeUserMap();
        }
        File f2 = new File("D:\\Users\\Hasmik\\Desktop\\Streams\\Ads.txt");
        if (f2.exists() && f2.length()!= 0){
            advertisements = deserializeAdvertisementList();
        }

    }

    public void addUsers(User user) {
        if (isEmailExist(user.getEmail())) {
            System.out.println("User with a " + user.getEmail() + "already exists. Try another email.");
        } else {
            users.put(user.getEmail(), user);
            System.out.println("The user with " + user.getEmail() + " is added.");

        }
    }

    public boolean isEmailExist(String email) {
        return users.get(email) != null;

    }

    public void printUsersByNameOrder() {
        List<User> list = new ArrayList<>(users.values());
        Collections.sort(list, new SortUserByName());

        for (User user : list) {
            System.out.println(user);
        }


    }


    public void deleteByEmail(String email) {
        if (!users.isEmpty() && users.get(email) != null) {
            users.remove(email);
            serializeUserMap(users);
        } else {
            System.out.println("No user with " + email + " was found.");
        }


    }


    public User verifyUser(String email, String password) {
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {

            return user;
        }
        return null;
    }


    public void addAdvertisements(Advertisement advertisement) {

        advertisements.add(advertisement);
        serializeAdvertisementList(advertisements);
    }

    public void sortAdvertisementsByRecentUpdate() {
        Collections.sort(advertisements, new SortByDate());


        for (Advertisement advertisement1 : advertisements) {
            System.out.println(advertisement1);
        }
    }

    public void printAdvertisementsByCategory(String category) {
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getAdvertisementCategory().name().equalsIgnoreCase(category))
                System.out.println(advertisement);

        }

    }

    public void printAllAdvertisements() {
        for (Advertisement advertisement : advertisements) {
            System.out.println(advertisement);

        }
    }

    public void printOwnAdvertisements(User currentUser) {
        for (Advertisement advertisement : advertisements) {
            if (advertisement.getCreatedUser().equals(currentUser))
                System.out.println(advertisement);
        }
    }


}
