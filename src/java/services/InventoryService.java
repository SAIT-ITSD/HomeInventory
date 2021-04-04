/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 828200
 */
import dataaccess.ItemDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Item;
import dataaccess.UserDB;
import java.util.List;
import models.User;
public class InventoryService {
    public boolean authentification(int Item_id,String email)
    {
        boolean isTrue=false;
        ItemDB idb=new ItemDB();
        UserDB udb=new UserDB();
        try {
            Item item=idb.get(Item_id);
           User user=udb.get(item.getOwner());
           if(user.getEmail().equals(email))
           {
                isTrue=true;
           }
        } catch (Exception ex) {
            Logger.getLogger(InventoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isTrue;
    }
}
