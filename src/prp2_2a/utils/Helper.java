/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.utils;

/**
 *
 * @author Timmay
 */
public class Helper {
    
    public static void println(String msg){
        System.out.println(msg);
    }
    
    public static void println(String msg, boolean isErr){
        System.err.println(msg);
    }
    
    public static double sign(double val) {
        return (val<0)?(-1):(+1);
    }
    
    public static double moduloCyclic(double num, double base) {
        return (num % base + base) % base;
    }
    
    public static int moduloCyclic(int num, int base) {
        return (num % base + base) % base;
    }
    
    public static double accEarth = 9.81;
    
}
