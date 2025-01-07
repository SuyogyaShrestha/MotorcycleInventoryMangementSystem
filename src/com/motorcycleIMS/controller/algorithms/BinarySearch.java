/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorcycleIMS.controller.algorithms;

import com.motorcycleIMS.model.Items;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class BinarySearch {
     public Items searchByItemCode(int searchValue, ArrayList<Items> itemList,
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
