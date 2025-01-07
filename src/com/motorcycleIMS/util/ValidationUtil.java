/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorcycleIMS.util;


/**
 *
 * @author USER
 */
public class ValidationUtil {
    
    
    public static int validateItemCode(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        try{
            int check=Integer.parseInt(value.trim());
            int count=0;
            while(check != 0){
                check /= 10;
                ++count;
            }
            if (count<5 || count>5){
                validate=2;
                return validate;
            }
        }
        catch(NumberFormatException e){
            validate = 3;
            return validate;
        }
        return 0;
    }
    
    
    public static int validateName(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        return 0;
    }
    
    
    public static int validateQuantity(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        try{
        if(Integer.parseInt(value.trim())<=0){
            validate = 2;
            return validate;
        }
        }
        catch (NumberFormatException e){
            validate = 3;
            return validate;
        }
        return 0;
    }
    
    
    public static int validateMP(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        try{
        if(Integer.parseInt(value.trim())<=0){
            validate = 2;
            return validate;
        }
        }
        catch (NumberFormatException e){
            validate = 3;
            return validate;
        }
        return 0;
    }
    
    
    public static int validateSupplier(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        return 0;
    }
    
    
    public static int validateBikeModel(String value){
        int validate;
        if (value.trim().isEmpty()){
            validate = 1;
            return validate;
        }
        return 0;
    }
}
