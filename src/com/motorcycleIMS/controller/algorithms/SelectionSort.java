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
public class SelectionSort {
    
    
    public SelectionSort(){
        
    }
    
    
    public ArrayList<ItemsModel> sortByItemCode(ArrayList<ItemsModel> unsortedData, boolean isDesc) {
        
        ArrayList<ItemsModel> dataToSort = new ArrayList(); 
        dataToSort.addAll(unsortedData);
        
        for (int i = 0; i < dataToSort.size() - 1; i++) {
            //min value
            int minIndex = i;
            for (int j = i + 1; j < dataToSort.size(); j++) {
                // compare and assign
                if (isDesc) {
                    if (dataToSort.get(j).getItemCode() > dataToSort.get(minIndex).getItemCode()) {
                        minIndex = j;
                    }
                } else {
                    if (dataToSort.get(j).getItemCode() < dataToSort.get(minIndex).getItemCode()) {
                        minIndex = j;
                    }
                }
            }
            //swap
            ItemsModel tempItem = dataToSort.get(minIndex);
            dataToSort.set(minIndex, dataToSort.get(i));
            dataToSort.set(i, tempItem);
        }
        return dataToSort;
    }
    
    public ArrayList<ItemsModel> sortByName(ArrayList<ItemsModel> unsortedData, boolean isDesc) {
        
        ArrayList<ItemsModel> dataToSort = new ArrayList(); 
        dataToSort.addAll(unsortedData);
        
        for (int i = 0; i < dataToSort.size() - 1; i++) {
            //min value
            int minIndex = i;
            for (int j = i + 1; j < dataToSort.size(); j++) {
                // compare and assign
                if (isDesc) {
                    if (dataToSort.get(j).getName().compareToIgnoreCase(dataToSort.get(minIndex).getName())>0 ) {
                        minIndex = j;
                    }
                } else {
                    if (dataToSort.get(j).getName().compareToIgnoreCase(dataToSort.get(minIndex).getName())<0) {
                        minIndex = j;
                    }
                }
            }
            //swap
            ItemsModel tempItem = dataToSort.get(minIndex);
            dataToSort.set(minIndex, dataToSort.get(i));
            dataToSort.set(i, tempItem);
        }
        return dataToSort;
    }
    
    public ArrayList<ItemsModel> sortByPrice(ArrayList<ItemsModel> unsortedData, boolean isDesc) {
        
        ArrayList<ItemsModel> dataToSort = new ArrayList(); 
        dataToSort.addAll(unsortedData);
        
        for (int i = 0; i < dataToSort.size() - 1; i++) {
            //min value
            int minIndex = i;
            for (int j = i + 1; j < dataToSort.size(); j++) {
                // compare and assign
                if (isDesc) {
                    if (dataToSort.get(j).getmPrice() > dataToSort.get(minIndex).getmPrice()) {
                        minIndex = j;
                    }
                } else {
                    if (dataToSort.get(j).getmPrice() < dataToSort.get(minIndex).getmPrice()) {
                        minIndex = j;
                    }
                }
            }
            //swap
            ItemsModel tempItem = dataToSort.get(minIndex);
            dataToSort.set(minIndex, dataToSort.get(i));
            dataToSort.set(i, tempItem);
        }
        return dataToSort;
    }
}
