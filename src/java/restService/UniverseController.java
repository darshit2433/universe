/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restService;

import Dao.SystemMasterDao;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import detail.Universe;
import detail.Family;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * REST Web Service
 *
 * @author DPadhya
 */
@Path("universe")
public class UniverseController {

    @Context
    private UriInfo context;
    
    
    
    
         @GET
    @Path("/getFamilies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Family>  getFamilies() throws IOException, ClassNotFoundException, SQLException{
   SystemMasterDao systemMasterDao =new SystemMasterDao();
     return  systemMasterDao.getFamilies(); 
        
    
    }
         @GET
    @Path("/checkBalance")
    @Produces(MediaType.APPLICATION_JSON)
    public String  checkBalance(@QueryParam("id")String id) throws IOException, ClassNotFoundException, SQLException{
   SystemMasterDao systemMasterDao =new SystemMasterDao();
   if(systemMasterDao.checkBalance(id)){
     return  "Balanced"; 
   }
   else{
     return  "Unbalanced";   
   }
      
    }
    @GET
    @Path("/findUnbalanced")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Family>   findUnbalanced() throws IOException, ClassNotFoundException, SQLException{
   SystemMasterDao systemMasterDao =new SystemMasterDao();
    return systemMasterDao.findUnbalanced();
 
      
    }
}
