/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculationWS;

import java.util.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author patil
 */
public class DataHandler {
    public DB db;
    public DBCollection booktable;
    public DataHandler(){
    try{	
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        
        // Now connect to your databases
        db = mongoClient.getDB( "test" );
        System.out.println("Connect to database successfully");
        if(!db.collectionExists("userCol"))
           db.createCollection("userCol", new BasicDBObject());  
         
	if(db.collectionExists("bookCol")){
            db.getCollection("bookCol").drop();
        }
        booktable = db.createCollection("bookCol", new BasicDBObject()); 
//        String path = System.getProperty("user.dir").toString();
//        System.out.println(path);
//        path = path.replaceAll("\\\\","/" );
//        path = path.replaceAll(" ","%20");
//        System.out.println(path);
//        URI uri = new URI("file:///"+path+"/Books.txt");
//        JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
        JSONArray root = new JSONArray(new data().getData());
        System.out.println("inside creation"+root.toString());
        for(int i =0;i<root.length();i++){            
            DBObject dbObject = (DBObject)JSON.parse(root.getJSONObject(i).toString());
            booktable.insert(dbObject);    
        }
      
        }catch(Exception e){
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public String GET(String author) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // GET method to query list of books by year
    public ArrayList<BookInfo> GET(int year) {
        BasicDBObject searchQuery = new BasicDBObject();
        System.out.println("inside GET");
        searchQuery.put("YEAR", year);
        DBCursor cursor = booktable.find(searchQuery);
        ArrayList<BookInfo> result = new ArrayList<BookInfo>();
	while (cursor.hasNext()) {
            JSONObject a = new JSONObject(cursor.next().toString());
            if(!a.getBoolean("isRented")){
                BookInfo bInfo = new BookInfo();
                bInfo.setId(a.getInt("BOOK_ID"));
                bInfo.setTitle(a.getString("TITLE"));
                bInfo.setAuthor(a.getString("AUTHOR"));         
                bInfo.setYear(a.getInt("YEAR"));
                bInfo.setIsRented(a.getBoolean("isRented"));
                result.add(bInfo);
            }
	}
        return result;
    }
    
    public void UPDATE(int id){
        BasicDBObject newDocument = new BasicDBObject().append("$set",   
            new BasicDBObject().append("isRented", "TRUE"));  
        booktable.update(new BasicDBObject().append("BOOK_ID", id), newDocument);  
    }

    boolean INSERT(String username) {
        System.out.println("inside INSERT");
        DBCollection userDB = db.getCollection("userCol"); 
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("UserName", username);
        DBCursor cursor = userDB.find(searchQuery);
        
        if(!cursor.hasNext()){
            //create new
            BasicDBObject document = new BasicDBObject();
            document.put("UserName", username);
            document.put("BooksRented", 1);
            document.put("MaxRent", 5);
            userDB.insert(document);
            return true;
        }
        return false;
    } 

    boolean UPDATE_USER(String username) {
        DBCollection userDB = db.getCollection("userCol"); 
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("UserName", username);
        DBCursor cursor = userDB.find(searchQuery);
        JSONObject a = new JSONObject(cursor.next().toString());
        int n = 0;
        n = a.getInt("BooksRented");
        int max = a.getInt("MaxRent");
        if(n<max){
            n++;
            BasicDBObject newDocument = new BasicDBObject().append("$set",   
            new BasicDBObject().append("BooksRented", n));  
            userDB.update(new BasicDBObject().append("UserName", username), newDocument); 
            return true;   
        }else{
            return false;
       }            
    }
}
