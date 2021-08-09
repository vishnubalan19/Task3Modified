package com.bankapplication.mapdata;

import com.bankapplication.account.Account;
import com.bankapplication.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public enum MapData{
    mapData;
    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private final Map <Integer,Map<Long, Account>> dbHashMap = new HashMap<>();
    private final Map<Integer,List<String>> customerBranchMap = new HashMap<>();
    public void setCustomers(List <Customer> customerList){
        for(Customer customer : customerList){
            setCustomerMap(customer);
        }
    }
    public void setCustomerMap(Customer customer){
        customerMap.put(customer.getCustomerId(),customer);
    }
    public Map<Integer,Customer> getCustomerMap(){
        return customerMap;
    }
    public void setCustomerBranches( List<Account> accountList){
        for(Account account : accountList){
            setCustomerBranchMap(account.getCustomerId(),account.getBranch());
        }
    }
    public void setCustomerBranchMap(int customerId,String branch){
        List<String > branchList = customerBranchMap.getOrDefault(customerId,new ArrayList<>());
        branchList.add(branch);
        customerBranchMap.put(customerId,branchList);
    }
    public Map<Integer,List<String>> getCustomerBranchMap(){
        return customerBranchMap;
    }
    public void clearDbHashMap(){
        dbHashMap.clear();
    }
	public void setDbHashValues(List<Account> accountList){
        for(Account account : accountList){
            setDbHashMap(account);
        }
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