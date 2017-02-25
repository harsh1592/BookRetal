/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapp;

import calculationws.BookInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 *
 * @author patil
 */
public class LibraryApp {

   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String sessionID = UUID.randomUUID().toString();
        
        List<calculationws.BookInfo> result = new ArrayList<>();
        Scanner src = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = src.next();
        
      
        System.out.println("Enter Operation to perform");
        System.out.println("1.Search Book by Year 2.Rent Book by id 3. EXIT");
        int option = src.nextInt();
        while(option==1||option==2||option==3){
            if(option==1){
                System.out.println("Search book by year");
                int year = src.nextInt();
                System.out.println("Fetching result...");
                result = getInfoByYear(year,username,sessionID);

                System.out.println("Books by year:"+year);
                for(BookInfo obj: result){
                    System.out.println(obj.id+": "+obj.title+": by "+obj.author);
                }
                System.out.println("Do you wish to rent");
                System.out.println("1.Yes 2.No");
                int o = src.nextInt();
                if(o==1){
                    System.out.println("Enter book id to rent");
                    int id = src.nextInt();
                    String resultMessage = rentBook(id,username,sessionID);
                    if(resultMessage.equals("0")){
                        System.out.println("Book Rented");
                         System.exit(0);
                    }else if(resultMessage.equals("1")){
                        System.out.println("Max count reached");
                    }else{
                        System.out.println("Illegal sequence of operations, check for availabilty first!");
                        System.out.println("Enter Operation to perform");
                        System.out.println("1.Search Book by Year 2.Rent Book by id 3. EXIT");
                        option = src.nextInt();
                        continue;
                    }
                }else{
                    System.exit(0);
                }
            }else if(option==2){
                System.out.println("Enter book id to rent");
                int id = src.nextInt();
                String resultMessage = rentBook(id,username,sessionID);
                if(resultMessage.equals("0")){
                    System.out.println("Book Rented");
                    System.exit(0);
                }else if(resultMessage.equals("1")){
                    System.out.println("Max count reached");
                }else{
                    System.out.println("Illegal sequence of operations, check for availabilty first!");
                    System.out.println("Enter Operation to perform");
                    System.out.println("1.Search Book by Year 2.Rent Book by id 3. EXIT");
                    option = src.nextInt();
                    continue;
                }
            }else{
                System.exit(0);
            }
        } 
    }
    private static java.util.List<calculationws.BookInfo> getInfoByYear(int year,
            String username, String sessionId) {
        calculationws.CalWS_Service service = new calculationws.CalWS_Service();
        HeaderHandlerResolver handlerResolver;
        handlerResolver = new HeaderHandlerResolver();
        service.setHandlerResolver(handlerResolver);
        calculationws.CalWS port = service.getCalWSPort();
        return port.getInfoByYear(year,username,sessionId);
    }
    

    private static String rentBook(int id, String username, String sessionId) {
        calculationws.CalWS_Service service = new calculationws.CalWS_Service();
        HeaderHandlerResolver handlerResolver;
        handlerResolver = new HeaderHandlerResolver();
        service.setHandlerResolver(handlerResolver);
        calculationws.CalWS port = service.getCalWSPort();
        return port.rentBook(id,username,sessionId);
    }

  

    
    public static class HeaderHandlerResolver implements HandlerResolver {
        public List<Handler> getHandlerChain(PortInfo portInfo) {
            List<Handler> handlerChain = new ArrayList<Handler>();
            MyMessageHandler hh = new MyMessageHandler();
            handlerChain.add(hh);
            return handlerChain;
        } 
    }
}
