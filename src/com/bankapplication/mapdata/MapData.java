package com.bankapplication.mapdata;

import com.bankapplication.account.Account;
import com.bankapplication.customer.Customer;

import java.util.HashMap;
import java.util.Map;

public enum MapData{
    mapData;
    private Map<Integer, Customer> customerMap = new HashMap<>();
    private Map <Integer,Map<Long, Account>> dbHashMap = new HashMap<>();
    public void setCustomerMap(Customer customer){
        customerMap.put(customer.getCustomerId(),customer);
    }
    public Map<Integer,Customer> getCustomerMap(){
        return customerMap;
    }
    public void clearDbHashMap(){
        dbHashMap.clear();
    }
    public void setDbHashMap(Account account){
        Map<Long,Account> userMap = dbHashMap.getOrDefault(account.getCustomerId(), new HashMap<>());
        userMap.put(account.getAccountNo(),account);
        dbHashMap.put(account.getCustomerId(),userMap);
    }
    public Map<Integer,Map<Long,Account>> getDbHashMap(){
        return dbHashMap;
    }
}


//package com.bankapplication.mapdata;
//
//        import com.bankapplication.account.Account;
//        import com.bankapplication.customer.Customer;
//
//        import java.util.HashMap;
//        import java.util.Map;
//
///*public class MapData {
//    private MapData(){
//
//    }
//    private static MapData mapData=null;
//    public static MapData getInstance(){
//        if(mapData==null)
//            mapData=new MapData();
//        return mapData;
//    }
//    private Map<Integer, Customer> customerMap = new HashMap<>();
//    private Map <Integer,Map<Long, Account>> dbHashMap = new HashMap<>();
//    public void setCustomerMap(Customer customer){
//        customerMap.put(customer.getId(),customer);
//    }
//    public Map<Integer,Customer> getCustomerMap(){
//        return customerMap;
//    }
//
//    public void setDbHashMap(Account account){
//        Map<Long,Account> userMap = dbHashMap.getOrDefault(account.getId(), new HashMap<>());
//        //System.out.println("1");
//        userMap.put(account.getAccountNo(),account);
//        dbHashMap.put(account.getId(),userMap);
//    }
//    public Map<Integer,Map<Long,Account>> getDbHashMap(){
//        return dbHashMap;
//    }
//}*/
//public enum MapData{
//    mapData;
//    private Map<Integer, Customer> customerMap = new HashMap<>();
//    private Map <Integer,Map<Long, Account>> dbHashMap = new HashMap<>();
//    public void setCustomerMap(Customer customer){
//        customerMap.put(customer.getCustomerId(),customer);
//    }
//    public Map<Integer,Customer> getCustomerMap(){
//        return customerMap;
//    }
//
//    public void setDbHashMap(Account account){
//        Map<Long,Account> userMap = dbHashMap.getOrDefault(account.getCustomerId(), new HashMap<>());
//        //System.out.println("1");
//        if(!userMap.containsKey(account.getAccountNo())){
//            System.out.println("hi from md");
//            userMap.put(account.getAccountNo(),account);
//            dbHashMap.put(account.getCustomerId(),userMap);
//        }
//    }
//    public Map<Integer,Map<Long,Account>> getDbHashMap(){
//        return dbHashMap;
//    }
//}