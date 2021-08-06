package com.bankapplication.dbapplication;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

import com.bankapplication.dbconnection.DbConnection;
import com.bankapplication.account.Account;
import com.bankapplication.customer.Customer;
import com.bankapplication.mapdata.MapData;

public class DbApplication{
    private static int customerId = 1;
    private DbConnection dbConnection = new DbConnection();
    private Map <Integer,Customer> customerMap;
    private Map <Integer,Map<Long,Account>> dbHashMap ;
    private List <Customer> customerList = new ArrayList<>();
    private List <Account> accountList = new ArrayList<>();
    public void getUserChoice()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Database Application");
        System.out.println("1. Create User details table and Account table ");
        System.out.println("2. Insert values into User details table and Account table");
        System.out.println("3. Get User and Account information");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                String table1,table2;
                System.out.println("Enter the table name which contains user id and user name");
                table1=scanner.next();
                System.out.println("Enter the table name which contains account no, account balance and id");
                table2=scanner.next();
                dbConnection.createTables(table1,table2);
                System.out.println("Tables are created successfully");
                getUserChoice();
                break;
            case 2:
                System.out.println("Enter the no. of users");
                int noOfUsers=scanner.nextInt();
                while(noOfUsers-->0){
                    scanner.nextLine();
                    System.out.println("Enter the user name");
                    String name = scanner.nextLine();
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);
                    customer.setName(name);
                    MapData.mapData.setCustomerMap(customer);
                    customerList.add(customer);
                    System.out.println("Enter the no. of accounts for user");
                    int noOfAccounts = scanner.nextInt();
                    while(noOfAccounts-->0){
                        System.out.println("Enter the accountNo and balance");
                        long accountNo = scanner.nextLong();
                        int balance = scanner.nextInt();
                        Account account = new Account();
                        account.setAccountNo(accountNo);
                        account.setCustomerId(customerId);
                        account.setBalance(balance);
                        accountList.add(account);
                    }
                    customerId++;
                }
                dbConnection.insertUser(customerList);
                dbConnection.insertAccountDetails(accountList);
                customerList.clear();
                accountList.clear();
                getUserChoice();
                break;
            case 3:
                dbConnection.retrieveUsers();
                boolean flag=true;
                while(flag){
                    System.out.println("1. Find users");
                    System.out.println("2. Exit");
                    System.out.println("Enter your choice");
                    int curChoice = scanner.nextInt();
                    switch(curChoice){
                        case 1 :
                            System.out.println("Enter id");
                            int id = scanner.nextInt();
                            dbHashMap=MapData.mapData.getDbHashMap();
                            customerMap=MapData.mapData.getCustomerMap();
                            if(dbHashMap.containsKey(id)){
                                Map<Long,Account> tempMap = dbHashMap.get(id);
                                for(Long t : tempMap.keySet()){
                                    System.out.print(tempMap.get(t).getCustomerId()+" "+customerMap.get(tempMap.get(t).getCustomerId()).getName()+" "+tempMap.get(t).getAccountNo()+" "+tempMap.get(t).getBalance());
                                    System.out.println();
                                }
                            }
                            else{
                                System.out.println("Enter valid user id");
                            }
                            break;
                        case 2 :
                            flag=false;
                            break;
                        default :
                            System.out.println("Enter valid choice");
                            break;
                    }
                }
                getUserChoice();
                break;
            case 4:
                dbConnection.closeStatement();
                dbConnection.closeConnection();
                break;
            default:
                System.out.println("Enter appropriate choice");
                getUserChoice();
                break;
        }
    }
    public static void main(String [] args) throws Exception{
        new DbApplication().getUserChoice();
    }
}


//package com.bankapplication.dbapplication;
//        import java.util.ArrayList;
//        import java.util.Map;
//
////import java.util.HashMap;
//        import java.util.Scanner;
//        import java.util.List;
////import java.util.ArrayList;
//
//        import com.bankapplication.dbconnection.DbConnection;
//        import com.bankapplication.account.Account;
//        import com.bankapplication.customer.Customer;
//        import com.bankapplication.mapdata.MapData;
//
//public class DbApplication{
//    private static int id = 1;
//    private DbConnection dbConnection = new DbConnection();
//    private Map <Integer,Customer> customerMap;
//    private Map <Integer,Map<Long,Account>> dbHashMap ;
//    private List <Customer> customerList = new ArrayList<>();
//    private List <Account> accountList = new ArrayList<>();
//    public void getUserChoice()throws Exception{
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Database Application");
//        System.out.println("1. Create User details table and Account table ");
//        System.out.println("2. Insert values into User details table and Account table");
//        System.out.println("3. Get User and Account information");
//        System.out.println("4. Exit");
//        int choice = scanner.nextInt();
//        switch(choice){
//            case 1:
//                String table1,table2;
//                System.out.println("Enter the table name which contains user id and user name");
//                table1=scanner.next();
//                System.out.println("Enter the table name which contains account no, account balance and id");
//                table2=scanner.next();
//                //if(dbConnection.createTables(table1,table2))
//                dbConnection.createTables(table1,table2);
//                System.out.println("Tables are created successfully");
//                //else
//                //System.out.println("Enter unique table names");
//                getUserChoice();
//                break;
//            case 2:
//                System.out.println("Enter the no. of users");
//                int noOfUsers=scanner.nextInt();
//                while(noOfUsers-->0){
//                    scanner.nextLine();
//                    System.out.println("Enter the user name");
//                    String name = scanner.nextLine();
//                    //System.out.println(name);
//                    Customer customer = new Customer();
//                    customer.setCustomerId(id);
//                    customer.setName(name);
//                    //customerMap.put(id,customer);
//                    MapData.mapData.setCustomerMap(customer);
//                    customerList.add(customer);
//                    //dbConnection.insertUser(customer);
//                    //scanner.nextLine();
//                    System.out.println("Enter the no. of accounts for user");
//                    //scanner.nextLine();
//                    int noOfAccounts = scanner.nextInt();
//                    while(noOfAccounts-->0){
//                        System.out.println("Enter the accountNo and balance");
//                        long accountNo = scanner.nextLong();
//                        int balance = scanner.nextInt();
//                        Account account = new Account();
//                        account.setAccountNo(accountNo);
//                        account.setCustomerId(id);
//                        account.setBalance(balance);
//                        accountList.add(account);
//                        //dbConnection.insertAccountDetails(account);
//                    }
//                    id++;
//                }
//                dbConnection.insertUser(customerList);
//                dbConnection.insertAccountDetails(accountList);
//                customerList.clear();
//                accountList.clear();
////                boolean status = dbConnection.commitValues();
////                if(status)
////                    System.out.println("Data inserted successfully");
////                else
////                    System.out.println("Data failed to insert");
//                getUserChoice();
//                break;
//            case 3:
//                //dbHashMap = dbConnection.retrieveUsers();
//                dbConnection.retrieveUsers();
//                boolean flag=true;
//                while(flag){
//                    System.out.println("1. Find users");
//                    System.out.println("2. Exit");
//                    System.out.println("Enter your choice");
//                    int curChoice = scanner.nextInt();
//                    switch(curChoice){
//                        case 1 :
//                            System.out.println("Enter id");
//                            int id = scanner.nextInt();
//                            dbHashMap=MapData.mapData.getDbHashMap();
//                            customerMap=MapData.mapData.getCustomerMap();
//                            if(dbHashMap.containsKey(id)){
//                                Map<Long,Account> tempMap = dbHashMap.get(id);
//                                for(Long t : tempMap.keySet()){
//                                    System.out.print(tempMap.get(t).getCustomerId()+" "+customerMap.get(tempMap.get(t).getCustomerId()).getName()+" "+tempMap.get(t).getAccountNo()+" "+tempMap.get(t).getBalance());
//                                    System.out.println();
//                                }
//                            }
//                            else{
//                                System.out.println("Enter valid user id");
//                            }
//                            break;
//                        case 2 :
//                            flag=false;
//                            break;
//                        default :
//                            System.out.println("Enter valid choice");
//                            break;
//                    }
//                }
//                getUserChoice();
//                break;
//            case 4:
//                dbConnection.closeStatement();
//                dbConnection.closeConnection();
//                break;
//            default:
//                System.out.println("Enter appropriate choice");
//                getUserChoice();
//                break;
//        }
//        //System.out.println("");
//    }
//    public static void main(String [] args) throws Exception{
//        new DbApplication().getUserChoice();
//    }
//}
//

