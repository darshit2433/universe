/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mysql.jdbc.Connection;
import detail.Universe;
import detail.Family;
import detail.Person;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author DPadhya
 */
@Component("SystemMasterDao")
public class SystemMasterDao {
  //MongoClient mongo =  new MongoClient( "127.0.0.1" ,27017 );
  //MongoDatabase database = mongo.getDatabase("test");  
    //Connection con;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/universe";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "vedantu123";
      Connection conn = null;
      Statement stmt = null;
  
    public SystemMasterDao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();      
    }
    public List<Family>  getFamilies() throws SQLException{
         String sql = "select * from family";
          ResultSet rs = stmt.executeQuery(sql);
      List<Family> familyList =  new ArrayList();
      while(rs.next()){
          Family family = new Family();
          family.setId(rs.getString("id"));
         family.setName(rs.getString("name"));
          
        familyList.add(family);
      }   
      
      
      return    familyList;
         
    }
    
    
     public Boolean  checkBalance(String id) throws SQLException{
         String sql = "select IFNULL(sum(power),0) power from (select * from person  where familyId=" + id +" ) p right outer JOIN universe u on u.id=p.universeId  group by u.name";
         System.out.println(sql);
          ResultSet rs = stmt.executeQuery(sql);
      Set <String> powers =  new HashSet();
      while(rs.next()){

        powers.add(rs.getString("power"));

      }   
      // Set contains only unique values so if it has more then 1 values so minimum 2 universe has different sum
      if(powers.size() != 1){
      return    false;
      }
      
      return    true;    
      
         
    }
     
     
    
    public  List<Family>  findUnbalanced() throws SQLException{
         String sql = "select * from family";
         System.out.println(sql);
          ResultSet rs1 = stmt.executeQuery(sql);
      List<Family> familyList =  new ArrayList();
      Map<String,String> familyMap = new HashMap();
      while(rs1.next()){
  
          familyMap.put(rs1.getString("id"),rs1.getString("name"));
  
      }      
      for(String  key : familyMap.keySet()){
          if(!checkBalance(key)){
              Family family = new  Family(key,familyMap.get(key));
              familyList.add(family);
          }
      }
      return familyList;
         
    }
    
    
//    public void createOrder(OrderDetail order){
//             
//     
//      // Creating Credentials 
//       
//         MongoCollection<org.bson.Document> collection = database.getCollection("orders"); 
//        org.bson.Document document = new org.bson.Document("title", "MongoDB") 
//      .append("uname", order.getClintUserName())
//      .append("item", order.getOrderItem()) 
//      .append("cnt", order.getCnt()); 
//
//      collection.insertOne(document); 
//    }
//     public List <OrderDetail>  getOrder( ){
//             
//     
//      // Creating Credentials 
//      MongoCollection<org.bson.Document> collection = database.getCollection("orders"); 
// FindIterable<org.bson.Document> iterDoc = collection.find(); 
//      int i = 1; 
//    Iterator it=iterDoc.iterator();
//    List <OrderDetail>  orderLt =new ArrayList <>();
//while (it.hasNext()) {  
//             Document doc=(  Document) it.next();
//        
//                           OrderDetail order = new OrderDetail();
//           order.setClintUserName((String)doc.get("uname")  );
//           order.setOrderItem((String)doc.get("item")  );
//            orderLt.add(order);
//
//}
//return  orderLt;
//    }
//    
//     
//     
//    public void createInventory(InventoryDetail inventory){
//             
//     
//      // Creating Credentials 
//        
//         MongoCollection<org.bson.Document> collection = database.getCollection("inventory"); 
//        org.bson.Document document = new org.bson.Document("title", "MongoDB") 
//      .append("itemName", inventory.getItemName())
//      .append("cnt", inventory.getCnt()) 
//      .append("lock", inventory.getIsLock())
//                .append("price", inventory.getPrice()); 
//
//      collection.insertOne(document); 
//    }
//     public List <InventoryDetail>  getInventory( ){
//             
//     
//      // Creating Credentials 
//      MongoCollection<org.bson.Document> collection = database.getCollection("inventory"); 
// FindIterable<org.bson.Document> iterDoc = collection.find(); 
//      int i = 1; 
//    Iterator it=iterDoc.iterator();
//    List <InventoryDetail>   inventoryLt =new ArrayList <>();
//while (it.hasNext()) {  
//             Document doc=(  Document) it.next();
//        
//                           InventoryDetail inventory = new InventoryDetail((String)doc.get("itemName"),
//                                    (Integer)doc.get("cnt"),(Boolean)doc.get("lock"), (Integer)doc.get("price"));
//            inventoryLt.add( inventory);
//
//}
//return   inventoryLt;
//    }
//     
//    public void createAccount(AccountDetail account){
//             
//     
//      // Creating Credentials 
//        
//         MongoCollection<org.bson.Document> collection = database.getCollection("account"); 
//        org.bson.Document document = new org.bson.Document("title", "MongoDB") 
//      .append("username",  account.getUserName()); 
//      
//
//      collection.insertOne(document); 
//    }
//     public List <AccountDetail>  getAccount( ){
//             
//     
//      // Creating Credentials 
//      MongoCollection<org.bson.Document> collection = database.getCollection("account"); 
// FindIterable<org.bson.Document> iterDoc = collection.find(); 
// 
// 
//    Iterator it=iterDoc.iterator();
//    List <AccountDetail>  accountLt =new ArrayList <>();
//while (it.hasNext()) {  
//             Document doc=(  Document) it.next();
//        
//                          AccountDetail account = new AccountDetail((String) doc.get("username"),
//                           (Integer)doc.get("cnt"));
//           
//            accountLt .add(account);
//
//}
//return  accountLt ;
//    }
//     
//     
//     public int  placeOrder(AccountDetail accountDetail,InventoryDetail invetoryDetail){
//             
//     
//      // Creating Credentials 
//      MongoCollection<org.bson.Document> inventoryCollection = database.getCollection("inventory"); 
// MongoCollection<org.bson.Document> accountCollection = database.getCollection("account");
// MongoCollection<org.bson.Document> orderCollection = database.getCollection("order");
// List <Bson> bson =new ArrayList<>();
// //lock down the item befor updtating it so another person can not change the count 
// UpdateResult updateResult=inventoryCollection.updateMany(Filters.and(Filters.eq("itemName", invetoryDetail.getItemName()),Filters.gte("cnt", invetoryDetail.getCnt()),Filters.eq("lock", false)), Updates.set("lock", "true"));       
// System.out.println(updateResult.getModifiedCount());
// if(updateResult.getModifiedCount()==1){
// FindIterable<org.bson.Document> iterDoc =inventoryCollection.find(Filters.eq("itemName", invetoryDetail.getItemName()));
// 
// Iterator it=iterDoc.iterator();
// InventoryDetail inventory=new InventoryDetail() ;
// if(it.hasNext()){
//             Document doc=(  Document) it.next();
//        
//                           inventory.setCnt((Integer)doc.get("cnt"));
//                                    
// }
// OrderDetail orderdetail =new OrderDetail(accountDetail.getUserName(),invetoryDetail.getItemName(),invetoryDetail.getCnt());
// createOrder(orderdetail);
// //we can also update persons total due to sytem 
//inventoryCollection.updateOne(Filters.eq("itemName", invetoryDetail.getItemName()), Updates.set("lock", "true"));           
//inventoryCollection.updateOne(Filters.eq("itemName", invetoryDetail.getItemName()), Updates.set("cnt",inventory.getCnt()- invetoryDetail.getCnt() ));           
//return  1;
//}
//return  0;
//   }

    }
     
    

