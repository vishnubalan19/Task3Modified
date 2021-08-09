package com.bankapplication.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bankapplication.account.Account;
import com.bankapplication.customer.Customer;

public class DbConnection{
    private final String url = "jdbc:mysql://127.0.0.1:3306/app";
    private final String login = "root";
    private final String pass = "Vishnu@007";
    private final String createTable = "create table if not exists ";
    private String table1,table2 ;
    private Connection con = null;
    private PreparedStatement ps1 = null, ps2=null;
    public Connection getConnection()throws Exception{
        if(con==null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, login, pass);
        }
        return con;
    }
    public void closeConnection() throws Exception{
        if(con!=null) {
            con.close();
        }
    }
    public PreparedStatement getStatement(String sql,PreparedStatement preparedStatement) throws Exception{
        if(preparedStatement == null){
            preparedStatement = getConnection().prepareStatement(sql,preparedStatement.RETURN_GENERATED_KEYS);
        }
        return preparedStatement;
    }
    public void closePreparedStatement(PreparedStatement preparedStatement) throws Exception{
        if(preparedStatement!=null){
            preparedStatement.close();
        }
    }
    public void closeStatement() throws Exception{
        closePreparedStatement(ps1);
        closePreparedStatement(ps2);
    }
    public boolean createTables(String table1,String table2){
        try{
            this.table1=table1;
            this.table2=table2;
            String sql1 = createTable+table1+" (customerId INTEGER not null AUTO_INCREMENT, name VARCHAR(255) not null,mobileNo BIGINT unsigned not null,PRIMARY KEY(customerId))";
            String sql2 = createTable+table2+" ( accountNo BIGINT unsigned not null AUTO_INCREMENT, balance DOUBLE not null,branch VARCHAR(255) not null, customerId INTEGER not null,PRIMARY KEY(accountNo), FOREIGN KEY (customerId) REFERENCES "+this.table1+" (customerId) )";
            createTable(sql1);
            createTable(sql2);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public void createTable(String sql) throws Exception{
        try(Statement statement = getConnection().createStatement()){
            statement.executeUpdate(sql);
        }
    }
    public List<Account> insertUsers(List <Customer> customerList,List<Account> accountList){
        if(customerList == null) {
            return null;
        }
		try{
            for(int i=0; i<customerList.size();i++){
                Customer customer = customerList.get(i);
                insertUser(customer);
                Account account = accountList.get(i);
                account.setCustomerId(customer.getCustomerId());
                if(!insertAccount(account)){
                    return null;
                }
            }
        }
		catch (Exception e){
		    System.out.println(e.getMessage());
		    return null;
        }
		return accountList;
    }
	public void insertUser(Customer customer) throws Exception{
		if(customer == null){
			return;
		}
		String sql = "insert into "+this.table1+"(name,mobileNo) values(?,?)";
		ps1 = getStatement(sql,ps1);
		ps1.setString(1,customer.getName());
		ps1.setLong(2,customer.getMobileNo());
		ps1.executeUpdate();
		try(ResultSet rs = ps1.getGeneratedKeys()){
		    if(rs.next()){
                customer.setCustomerId(rs.getInt(1));
            }
        }
	}
	public boolean insertAccount(Account account){
		if(account==null){
			return false;
		}
		try{
            String sql = "insert into "+this.table2+"(balance,customerId,branch) values(?,?,?)";
            ps2 = getStatement(sql,ps2);
            ps2.setDouble(1,account.getBalance());
            ps2.setInt(2,account.getCustomerId());
            ps2.setString(3,account.getBranch());
            ps2.executeUpdate();
            try(ResultSet rs = ps2.getGeneratedKeys()){
                if(rs.next()){
                    account.setAccountNo(rs.getInt(1));
                }
            }
        }
		catch (Exception e){
		    System.out.println(e.getMessage());
		    return false;
        }
		return true;
	}
    public List<Account> getAccountsList()throws Exception{
        List<Account> accountList = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();ResultSet rs = statement.executeQuery("select customerId,accountNo,balance,branch from "+table2)){
            while(rs.next()){
                Account account = new Account();
                account.setCustomerId(rs.getInt("customerId"));
                account.setAccountNo(rs.getLong("accountNo"));
                account.setBalance(rs.getDouble("balance"));
                account.setBranch(rs.getString("branch"));
                accountList.add(account);
            }
        }
        return accountList;
    }
}

//package com.bankapplication.dbconnection;
//
//        import java.sql.Connection;
//        import java.sql.DriverManager;
//        import java.sql.PreparedStatement;
//        import java.sql.Statement;
//        import java.sql.ResultSet;
//        import java.util.List;
//
//
//        import com.bankapplication.account.Account;
//        import com.bankapplication.customer.Customer;
//        import com.bankapplication.mapdata.MapData;
//
//public class DbConnection{
//    private final String url = "jdbc:mysql://127.0.0.1:3306/app";
//    private final String login = "root";
//    private final String pass = "Vishnu@007";
//    private String table1,table2 ;
//    private Connection con = null;
//    private PreparedStatement ps1 = null, ps2=null;
//    //private Map <Integer,Map<Long,Account>> dbHashMap = new HashMap<>();
//    private static boolean flag = false;
//    public Connection getConnection()throws Exception{
//        if(con==null) {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(url, login, pass);
//        }
//        return con;
//    }
//    public void closeConnection() throws Exception{
//        if(con!=null)
//            con.close();
//        //con=null;
//    }
//    public PreparedStatement getStatement(String sql,PreparedStatement preparedStatement) throws Exception{
//        /*if(ps1==null)
//            //System.out.println("hi");
//            ps1 = con.prepareStatement("insert into "+this.table1+" values(?,?)");
//        if(ps2==null)
//            ps2 = con.prepareStatement("insert into "+this.table2+" values(?,?,?)");*/
//        if(preparedStatement == null){
//            preparedStatement = getConnection().prepareStatement(sql);
//        }
//        return preparedStatement;
//    }
//    public void closeStatement() throws Exception{
//        if(ps1!=null&&ps2!=null){
//            ps1.close();
//            ps2.close();
//        }
//    }
//    public void createTables(String table1,String table2) throws Exception{
//		/*DatabaseMetaData dbm = con.getMetaData();
//		ResultSet tables = dbm.getTables(null, null,table1, null);
//		if(tables.next())
//			return false;
//		tables = dbm.getTables(null, null,table2, null);
//		if(tables.next())
//			return false;
//		tables.close();*/
//        this.table1=table1;
//        this.table2=table2;
//        try(Statement statement = getConnection().createStatement()){
//            String sql1 = "create table if not exists "+this.table1+" (id INTEGER not null, name VARCHAR(255) not null,PRIMARY KEY(id))";
//            statement.executeUpdate(sql1);
//            String sql2 = "create table if not exists "+this.table2+" ( accountNo BIGINT unsigned not null, balance INTEGER not null, id INTEGER not null,PRIMARY KEY(accountNo), FOREIGN KEY (id) REFERENCES "+this.table1+" (id) )";
//            statement.executeUpdate(sql2);
//        }
//        //return true;
//    }
//    public void insertUser(List <Customer> customerList) throws Exception{
//        if(customerList == null)
//            return ;
//		/*try(Statement statement = con.createStatement()){
//			String sql="insert into "+table1+" values("+customer.getId()+",'"+customer.getName()+"')";
//			statement.executeUpdate(sql);
//		}*/
//        //con.setAutoCommit(false);
//        String sql = "insert into "+this.table1+" values(?,?)";
//        ps1 = getStatement(sql,ps1);
//        for(Customer customer : customerList){
//            ps1.setInt(1,customer.getId());
//            ps1.setString(2,customer.getName());
//            //ps1.executeUpdate();
//            ps1.addBatch();
//        }
//        ps1.executeBatch();
//        System.out.println("Customer details inserted successfully");
//        flag = true;
//    }
//    public void insertAccountDetails(List <Account> accountList) throws Exception{
//        if(accountList==null)
//            return ;
//		/*try(Statement statement = con.createStatement()){
//			String sql = "insert into "+table2+" values("+account.getAccountNo()+","+account.getBalance()+","+account.getId()+")";
//			statement.executeUpdate(sql);
//		}*/
//        String sql = "insert into "+this.table2+" values(?,?,?)";
//        ps2 = getStatement(sql,ps2);
//        for (Account account : accountList){
//            ps2.setLong(1,account.getAccountNo());
//            ps2.setInt(2,account.getBalance());
//            ps2.setInt(3,account.getId());
//            //ps2.executeUpdate();
//            ps2.addBatch();
//        }
//        ps2.executeBatch();
//        System.out.println("Account details inserted successfully");
//    }
//    //    public boolean commitValues() throws SQLException {
////        try{
////            con.commit();
////        }
////        catch (Exception exception){
////            con.rollback();
////            return false;
////        }
////        return true;
////    }
//    public /*Map<Integer,Map<Long,Account>>*/ void  retrieveUsers()throws Exception{
//        //if(con==null)
//        //System.out.println("bb");
//        if(flag){
//            System.out.println("hi");
//            getConnection();
//            try(Statement statement = con.createStatement();ResultSet rs = statement.executeQuery("select id,accountNo,balance from "+table2)){
//                while(rs.next()){
//				/*System.out.print(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getLong("no")+" "+rs.getInt("balance"));
//				System.out.println();*/
//                    Account account = new Account();
//                    account.setId(rs.getInt("id"));
//                    account.setAccountNo(rs.getLong("accountNo"));
//                    account.setBalance(rs.getInt("balance"));
//                    MapData.mapData.setDbHashMap(account);
//                /*Map<Long,Account> userMap = dbHashMap.getOrDefault(rs.getInt("id"),new HashMap<Long,Account>());
//                userMap.put(rs.getLong("accountNo"),account);
//                dbHashMap.put(rs.getInt("id"),userMap);*/
//
//                }
//			/*for(int k : hm.keySet()){
//				System.out.println(k);
//				HashMap<Long,Account> temp = hm.get(k);
//				for(Long t : temp.keySet()){
//					System.out.print(t+" "+temp.get(t).getName()+" "+temp.get(t).getBalance()+" ");
//				}
//				System.out.println();
//			}*/
//            }
//            flag=false;
//        }
//
//        //return dbHashMap;
//    }
//}