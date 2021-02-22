/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author 828200
 */
public class HomeItem {

    String line;
    String name,room,item;
    int price;
    public HomeItem() {
    }

    public HomeItem(String line) {
        
        split(line);
    }

    public String getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }
    
    
    
    void split(String line)
    {
        String[] parts=line.split(line);
        name=parts[0];
        room=parts[1];
        item=parts[2];
        price=parseInt(parts[3]);  
        
    }
    public int total(String path) throws FileNotFoundException
    {
        
        File f=new File(path);
        Scanner k=new Scanner(f);
        int total=0;
        while(k.hasNext())
            {
                String liner=k.nextLine();
                String line[]=liner.split(",");
                 try
                {
                    String key=line[3];
                    total+=parseInt(key);
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    total+=0;
                }
                 
            }
        k.close();
        return total;
        
    }
    public int total(String user,String path) throws FileNotFoundException
    {
         File f=new File(path);
        
        Scanner k=new Scanner(f);
        int total=0;
        boolean isTrue=false;
        while(k.hasNext())
            {
                isTrue=false;
                String liner=k.nextLine();
                String line[]=liner.split(",");
                
                if(line[0].equals(user))
                {
                    isTrue=true;
                }
                if(isTrue==true)
                {
                    String key=line[3];
                    total+=parseInt(key); 
                }
           
        }
        k.close();
        return total;
    }

          public void append(String path,String line) throws IOException
    {
        
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        fw.write("\n"+line);
        fw.close();
    }
    public String topUser(String path) throws FileNotFoundException
    {
        File f=new File(path);
        Scanner k=new Scanner(f);
        int highest=0;
        String name="";
        String item_name="";
        String key="";
        while(k.hasNext())
        {
            String line[]=k.nextLine().split(",");
            try
             {
                 int target=parseInt(line[3]);
                 if(highest<target)
                 {
                     highest=target;
                     name=line[0];
                     item_name=line[2];
                 }
                 
             }
             catch(ArrayIndexOutOfBoundsException e)
             {

             }
            
        }
        k.close();
        key=name+","+item_name;
        return key;
        
    }
    
    
}
