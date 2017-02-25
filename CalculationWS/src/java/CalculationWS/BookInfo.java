/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculationWS;

/**
 *
 * @author patil
 */
public class BookInfo {
    int id;
    String title;
    String author;
    int year;
    boolean isRented;
   
    public BookInfo(){
        
    }
    public BookInfo(int id, String title, String author, int year, boolean isRented){
        this.id=id;
        this.title=title;
        this.author =author;
        this.year=year;
        this.isRented=isRented;      
    }
    
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }
    
    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return year;
    }
    
    public void setIsRented(boolean isRented){
        this.isRented=isRented;
    }
    public boolean getIsRented(){
        return isRented;
    }
}
