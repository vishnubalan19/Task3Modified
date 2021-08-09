package com.bankapplication.logiclayer;

import java.util.List;
import java.util.Map;

import com.bankapplication.customer.Customer;
import com.bankapplication.account.Account;
import com.bankapplication.dbconnection.DbConnection;
import com.bankapplication.mapdata.MapData;

public class LogicLayer {
    private final DbConnection dbConnection = new DbConnection();
    public boolean createTables(String table1, String table2)  {
       return dbConnection.createTables(table1,table2);
    }
    public List<Account> insertUsers(List<Customer> customerList, List<Account> accountList) {
        List<Account> tempAccountList= dbConnection.insertUsers(customerList,accountList);
        if(tempAccountList!=null){
            MapData.mapData.setCustomers(customerList);
            MapData.mapData.setCustomerBranches(accountList);
            return tempAccountList;
        }
        else {
            return null;
        }
    }
    public Map<Integer,Customer> getCustomerMap(){
        return MapData.mapData.getCustomerMap();
    }
    public boolean insertAccount(Account account){
        return dbConnection.insertAccount(account);
    }
    public void retrieveUsers() throws Exception {
        List<Account> accountList = dbConnection.getAccountsList();
        MapData.mapData.clearDbHashMap();
        MapData.mapData.setDbHashValues(accountList);
    }
    public Map<Integer,Map<Long,Account>> getDbHashMap(){
        return MapData.mapData.getDbHashMap();
    }
    public Map<Integer, List<String>> getCustomerBranchMap(){
        return MapData.mapData.getCustomerBranchMap();
    }
    public void closeStatement() throws Exception{
        dbConnection.closeStatement();
    }
    public void closeConnection() throws Exception {
        dbConnection.closeConnection();
    }
}
