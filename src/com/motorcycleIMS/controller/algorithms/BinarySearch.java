/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorcycleIMS.controller.algorithms;

import com.motorcycleIMS.model.ItemsModel;
import java.util.ArrayList;

/**
 *
 * @author Suyogya Shrestha
 */

public class BinarySearch {
     public ItemsModel searchByItemCode(int searchValue, ArrayList<ItemsModel> itemList,
            int low, int high) {
        
        while(low <= high){
            int mid = (low + high) / 2;
            int midID = itemList.get(mid).getItemCode();
            if (searchValue == midID){
                return itemList.get(mid);
            }
            else if (searchValue < midID){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
            
        }
        return null;
    }
}
