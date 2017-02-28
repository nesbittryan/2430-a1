/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1nesbittr;

import java.util.*;

/**
 * 
 * @author Ryan
 */
public class eStoreSearch {

    /**
     * the main function of the EStoreSearch
     * requires the Electronics and Book classes
     * gets input from user, and does specified command
     * @param args allows for command line input
     */
    public static void main(String[] args) {
        ArrayList <Electronics> electronicList = new ArrayList <> ();
        ArrayList <Book> bookList = new ArrayList <> ();
        Scanner scan = new Scanner(System.in);
        String input = "";
        
        while(!input.toLowerCase().contains("q")) {
            String searchID;
            String searchKey;
            String searchYear;
            String junk;
            
            System.out.println("Please either 'add', 'search', or 'quit/q'");
            input = scan.nextLine();
            switch (input.toLowerCase()) {
                case "add":
                    System.out.println("Please type in 'B' for a book or 'E' for electronic item");
                    junk = scan.nextLine();
                    switch (junk.toUpperCase()) {
                        case "E":
                            System.out.println("Electronic");
                            electronicList.add(createElec(bookList, electronicList));
                            break;
                        case "B":
                            System.out.println("BOOK");
                            bookList.add(createBook(bookList, electronicList));
                            break; 
                        default:
                            System.out.println("Incorrect Input, returning to main menu");
                            break;
                    }
                    break;
                case "search":
                    System.out.println("Please type in a productID");
                    searchID = scan.nextLine();
                    System.out.println("Please type in a keyword");
                    searchKey = scan.nextLine();
                    System.out.println("Please type in a year or range");
                    searchYear = scan.nextLine();
                    search(searchID, searchKey, searchYear, electronicList, bookList);
                    break;
                default:
                    System.out.println("Invalid command. Try 'add', 'search', or 'quit/q'\n");
                    break;
            }
        }
    }

    
    private static void search(String searchID, String searchKey, String searchYear, ArrayList <Electronics> electronicList, ArrayList <Book> bookList) {
        
        System.out.println("Electronics Matched: ");
        System.out.println("----------------------");
        
        for (Electronics electronic: electronicList) {
            
            int flag = 1;               //flag set to determine matches
                                        //default is set too no match
            
            if("".equals(searchKey)) {
                flag = 0;
            } else {
                
                String [] splitSearch = searchKey.toLowerCase().split(" +");
                String [] splitName = electronic.getName().toLowerCase().split(" +");
                String [] splitMaker = electronic.getMaker().toLowerCase().split(" +");
                
                for(int j = 0; j <splitName.length; ++j) {
                    for(int k = 0; k <splitSearch.length; ++k) {
                        if(splitName[j].contentEquals(splitSearch[k])) {
                            flag = 0;       //set to zero if found in name
                        }
                    }
                }
                for(int j = 0; j <splitMaker.length; ++j) {
                    for(int k = 0; k <splitSearch.length; ++k) {
                        if(splitMaker[j].contentEquals(splitSearch[k])) {
                            flag = 0;       //set to zero if found in maker
                        }
                    }
                }
            }
            
            if(!(searchID.contentEquals("")) && !(searchID.contentEquals(electronic.getID()))) {
               flag++;                      //if searchID != "" and doesnt equal electronic flag is raised
            }
         
            if(checkYear(searchYear, electronic.getYear()) != 0) {
                flag++;                     //calls a functions that returns 0 if year is in range
            }                               //else if adds to the flag
            
            if(flag == 0) {                 //if all of the flags havent been tripped it will print
                System.out.println(electronic.getID());
                System.out.println(electronic.getName());
                System.out.println(electronic.getPrice());
                System.out.println(electronic.getYear());
                System.out.println(electronic.getMaker() + "\n");
            }
        }
        
        System.out.println("----------------");
        System.out.println("Books Matched: ");
        System.out.println("----------------");
        
        for (Book book: bookList) {
            
            int flag = 1;               //flag set to determine matches
                                        //default is set too no match
            
            if("".equals(searchKey)) {
                flag = 0;
            } else {
                
                String [] splitSearch = searchKey.toLowerCase().split(" +");
                String [] splitName = book.getName().toLowerCase().split(" +");
                String [] splitAuthor = book.getAuthor().toLowerCase().split(" +");
                String [] splitPub = book.getPub().toLowerCase().split(" +");
                
                for(int j = 0; j <splitName.length; ++j) {
                    for(int k = 0; k <splitSearch.length; ++k) {
                        if(splitName[j].contentEquals(splitSearch[k])) {
                            flag = 0;           //searching for match in name
                        }
                    }
                }
                for(int j = 0; j <splitAuthor.length; ++j) {
                    for(int k = 0; k <splitSearch.length; ++k) {
                        if(splitAuthor[j].contentEquals(splitSearch[k])) {
                            flag = 0;           //searching for match in Author
                        }
                    }
                }
                for(int j = 0; j <splitPub.length; ++j) {
                    for(int k = 0; k <splitSearch.length; ++k) {
                        if(splitPub[j].contentEquals(splitSearch[k])) {
                            flag = 0;           //searching for match in publisher
                        }
                    }
                }
            }
            
            if(!(searchID.contentEquals("")) && !(searchID.contentEquals(book.getID()))) {
               flag++;                          //searching for match in ID
            }
            
            if(checkYear(searchYear, book.getYear()) != 0) {
                flag++;                         //checking if year is inside the range, else flag ++
            }
            
            if(flag == 0) {                     //priting if no flags tripped
                System.out.println(book.getID());
                System.out.println(book.getName());
                System.out.println(book.getPrice());
                System.out.println(book.getYear());
                System.out.println(book.getAuthor());
                System.out.println(book.getPub() + "\n");
            }
        }
    }

    private static int checkYear(String searchYear, int year) {
        String yearString = Integer.toString(year);
        int firstYear;
        int lastYear;
        
        if(searchYear.contains(yearString)) {
            return(0);          //if the search string contains the year return 0
        }
        
        if("".equals(searchYear)) {
            return(0);          //if the search string is blank, return 0
        }
        
        String[] split = searchYear.split("-");
        
        if(searchYear.charAt(searchYear.length() - 1) == '-') {       
            firstYear = Integer.parseInt(split[0]);
            lastYear = 9999;
            if(year >= firstYear && year <= lastYear) {
                return(0);      //if the last char is a -, check if year is above year before
            }
        } else if(searchYear.contains("-")) {   
            if("".equals(split[0])) {
                firstYear = 0;  //if first split is blank, first year is set to 0
            } else {
                firstYear = Integer.parseInt(split[0]); //else its set to what it was
            }
            if("".equals(split[1])) {
               lastYear = 9999; //sets last year to highest range if its blank
            } else {
                lastYear = Integer.parseInt(split[1]);  //or else set to given upper range
            }
            if(year >= firstYear && year <= lastYear) {
                return(0); //if year is in between first and last year, return 0
            }
        }
        return(1); //if 0 has not been returned, it will get here indicating no match, return 1
    }
    
    /**
     * Gets users input for each of the required values,
     * and checking to make sure they are in the valid format
     * Needs book class and constructor to work
     * @param bookList used to check current ID numbers
     * @param electronicList used to check current ID numbers
     * @return constructed Book
     */
    public static Book createBook(ArrayList <Book> bookList, ArrayList <Electronics> electronicList) {
        Scanner scan = new Scanner(System.in);
        String junk;
        String idIN;
        int numCheck = 0;
        int yearIN;
        float priceIN;
        String nameIN;
        String authorIN;
        String publisherIN;
        
        do {
            System.out.println("Enter valid ID (ex.123456):");
            while (!scan.hasNextInt()) {
                System.out.println("Invalid Int");
                scan.next();
            }
            idIN = scan.nextLine();
            numCheck = Integer.parseInt(idIN);
            for (Book book: bookList) {             //comparing to all books
                if(book.getID().equals(idIN)) {
                    idIN = "0";
                    System.out.println("ID already in use");
                }
            }
            for (Electronics electronic: electronicList) {  //comparing to electronics
                if(electronic.getID().equals(idIN)) {
                    idIN = "0";
                    System.out.println("ID already in use");
                }
            }
        } while (numCheck > 999999 || idIN.length() != 6);  //checking length
        
        do {
            System.out.println("Enter a valid year (ex. 2014): ");
            while (!scan.hasNextInt()) {
                System.out.println("Invalid Int");
                scan.next();
            }
            numCheck = scan.nextInt();
        } while(numCheck >= 10000 || numCheck <= 999);      //checking year range
        yearIN = numCheck;
        junk = scan.nextLine();
        
        do {
            System.out.println("Enter Price(ex 19.99):");
            while (!scan.hasNextFloat()) {
               System.out.println("Invalid Float");
                scan.next();
            }
            priceIN = scan.nextFloat();
            junk = scan.nextLine();                         //checking price
        } while(priceIN < 0.0);
        
        do {
            System.out.println("Enter Name:");
            nameIN = scan.nextLine();
        } while("".equals(nameIN));                         //getting name
        
        System.out.println("Enter Author:");
        authorIN = scan.nextLine();
        
        System.out.println("Enter Publisher:");
        publisherIN = scan.nextLine();
        
        Book b = new Book(idIN, yearIN, priceIN, nameIN, authorIN, publisherIN);
        System.out.println("BOOK CREATED");
        return b;
    }
    
    /**
     * Gets users input for each of the required values,
     * and checking to make sure they are in the valid format
     * Needs electronic class and constructor to work
     * @param bookList used to check current ID values
     * @param electronicList used to check current ID values
     * @return constructed electronic 
     */
    public static Electronics createElec(ArrayList <Book> bookList, ArrayList <Electronics> electronicList) {   
        
        Scanner scan = new Scanner(System.in);
        String junk;
        String idIN;
        int yearIN;
        int numCheck = 0;                           //Same process as book
        float priceIN;
        String nameIN;
        String makerIN;                 
        
        do {
            System.out.println("Enter valid ID (ex 123456):");
            while (!scan.hasNextInt()) {
                System.out.println("Invalid Int");
                scan.next();
            }
            idIN = scan.nextLine();
            numCheck = Integer.parseInt(idIN);
            for (Book book: bookList) {
                if(book.getID().equals(idIN)) {
                    idIN = "0";
                    System.out.println("ID already in use");
                }
            }
            for (Electronics electronic: electronicList) {
                if(electronic.getID().equals(idIN)) {
                    idIN = "0";
                    System.out.println("ID already in use");
                }
            }
        } while (numCheck > 999999 || idIN.length() != 6);
        
        do {
            System.out.println("Enter a valid year (ex 2014): ");
            while (!scan.hasNextInt()) {
                System.out.println("Invalid Int");
                scan.next();
            }
            numCheck = scan.nextInt();
        } while(numCheck >= 10000 || numCheck <= 999);
        yearIN = numCheck;
        junk = scan.nextLine();
        
        do {
            System.out.println("Enter Price(ex 19.99):");
            while (!scan.hasNextFloat()) {
               System.out.println("Invalid Float");
                scan.next();
            }
            priceIN = scan.nextFloat();
            junk = scan.nextLine();
        } while(priceIN < 0.0);
        
        do{
            System.out.println("Enter Name:");
            nameIN = scan.nextLine();
        } while("".equals(nameIN)); 
        
        System.out.println("Enter Maker:");
        makerIN = scan.nextLine();
        
        Electronics e = new Electronics(idIN, yearIN, priceIN, nameIN, makerIN);
        System.out.println("ELECTRONIC CREATED");
        return e;
    }
}
