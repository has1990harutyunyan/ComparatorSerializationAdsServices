package main;


import constants.AdminCommands;
import constants.UserCommands;
import data.DataStorage;
import model.Advertisement;
import model.AdvertisementCategory;
import model.User;
import model.UserType;


import java.io.IOException;
import java.util.*;


public class AdsServices implements AdminCommands, UserCommands {
    private static DataStorage dataStorage = DataStorage.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {

        User userAdmin = new User("User", "User", "user@mail.ru", "1234", UserType.ADMIN);
        dataStorage.addUsers(userAdmin);
        try {
            dataStorage.serializedData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logIn();

    }


    public static void logIn() {
        System.out.println("Please enter your email and password to log in. ");
        String emailAndPassword = scanner.next();
        String[] emailAndPasswordArray = emailAndPassword.split(",");
        String email = emailAndPasswordArray[0];
        String password = emailAndPasswordArray[1];
        User user = dataStorage.verifyUser(email, password);
        currentUser = user;
        if (user != null) {
            switch (user.getUserType()) {
                case ADMIN:
                    boolean b = true;
                    while (b) {
                        adminCommands();
                        int num = scanner.nextInt();
                        switch (num) {
                            case AdminCommands.BACK_TO_LOGIN:
                                logIn();
                                break;
                            case AdminCommands.REGISTER_USER:
                                registerUsers();
                                break;
                            case AdminCommands.PRINT_USERS:
                                dataStorage.printUsersByNameOrder();
                                break;
                            case AdminCommands.DELETE_USER_BY_EMAIL:
                                deleteUserByEmail();
                                break;
                            default:
                                System.out.println("Invalid number. ");
                                break;
                        }


                    }
                    break;
                case USER:
                    boolean c = true;
                    while (c) {
                        userCommands();
                        int num = scanner.nextInt();
                        switch (num) {
                            case UserCommands.BACK_TO_LOGIN:
                                logIn();
                                break;
                            case UserCommands.ADD_ADVERTISEMENTS:
                                addAdvertisements();
                                break;
                            case UserCommands.PRINT_ADVERTISEMENTS_BY_RECENT_UPDATE:
                                dataStorage.sortAdvertisementsByRecentUpdate();
                                break;
                            case UserCommands.PRINT_ADVERTISEMENTS_BY_CATEGORY:
                                printByCategory();
                                break;
                            case UserCommands.PRINT_OWN_ADVERTISEMENTS:
                                printOwnAdvertisements();
                                break;
                            default:
                                System.out.println("Invalid number.");
                                break;
                        }


                    }


            }

        } else {
            System.out.println("Invalid email or password. Please, try again.");
            logIn();
        }


    }


    public static void adminCommands() {
        System.out.println("Input " + AdminCommands.REGISTER_USER + " to get registered. ");
        System.out.println("Input " + AdminCommands.PRINT_USERS + " to print all the users by name order.");
        System.out.println("Input " + AdminCommands.DELETE_USER_BY_EMAIL + " to delete user by email (i.e key)");
        System.out.println("Input " + AdminCommands.BACK_TO_LOGIN + " to go back to logIn function.");
    }

    public static void userCommands() {
        System.out.println("Input " + UserCommands.ADD_ADVERTISEMENTS + " to addAdvertisements an advertisement.");
        System.out.println("Input " + UserCommands.PRINT_ADVERTISEMENTS_BY_RECENT_UPDATE + " to print all the advertisementsin register time.");
        System.out.println("Input " + UserCommands.PRINT_ADVERTISEMENTS_BY_CATEGORY + " to print all the advertisements by category.");
        System.out.println("Input " + UserCommands.PRINT_OWN_ADVERTISEMENTS + " to print your own advertisements.");
        System.out.println("Input " + UserCommands.BACK_TO_LOGIN + " to go back to logIn function.");
    }


    public static void registerUsers() {
        System.out.println("Please input your name, surname, email and password " + "to get registered!");
        String userData = scanner.next();
        String[] userDataArray = userData.split(",");
        User user = null;
        for (UserType userType : UserType.values()) {
            userType = UserType.USER;
            user = new User(userDataArray[0], userDataArray[1], userDataArray[2], userDataArray[3], userType);
        }
        dataStorage.addUsers(user);

    }

    public static void deleteUserByEmail() {
        System.out.println("List of users in name order.");
        dataStorage.printUsersByNameOrder();
        System.out.println("Choose the email to delete the user.");
        String emailToBeDeleted = scanner.next();

        dataStorage.deleteByEmail(emailToBeDeleted);

        dataStorage.printUsersByNameOrder();


    }

    public static void addAdvertisements() {
        System.out.println("To addAdvertisements an advertisement, please, follow these steps.");
        System.out.println("Choose an Advertisement category.");
        for (AdvertisementCategory ads : AdvertisementCategory.values()) {
            System.out.println(ads.name());
        }
        System.out.println("Input your chosen one.");
        String ads = scanner.next();
        AdvertisementCategory advertisementCategory = null;
        try {
            advertisementCategory = AdvertisementCategory.valueOf(ads);
        } catch (IllegalArgumentException e) {
            System.out.println("Input the right way as suggested.");
        }

        System.out.println("Input more info: title, description, price, address and contactPhone");
        String data = scanner.next();
        String dataArray[] = data.split(",");
        try {
            Advertisement advertisement = new Advertisement(dataArray[0], dataArray[1], advertisementCategory, new Date(), currentUser, Double.parseDouble(dataArray[2]), dataArray[3], dataArray[4]);
            dataStorage.addAdvertisements(advertisement);
        } catch (NumberFormatException e) {
            System.out.println("Input NUMBERS for price");
        }


    }

    public static void printOwnAdvertisements() {
        dataStorage.printAllAdvertisements();
        System.out.println("");

        dataStorage.printOwnAdvertisements(currentUser);
    }


    public static void printByCategory() {
        System.out.println("Choose the category to print all the advertisements.");
        for (AdvertisementCategory ads : AdvertisementCategory.values()) {
            System.out.println(ads);
        }
        String category = scanner.next();
        dataStorage.printAdvertisementsByCategory(category);

    }
}

