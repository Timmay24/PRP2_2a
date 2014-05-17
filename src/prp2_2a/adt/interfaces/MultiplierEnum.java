/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 */
public enum MultiplierEnum {
    
    MICRO(0.000001),
    MILLI(0.001),
    CENTI(0.01),
    DECI(0.1),
    //BASE(1.0),
    DEKA(10),
    HECTO(100),
    KILO(1000),
    MEGA(1000000),
    GIGA(1000000000);
    
    private double factor = 1.0;
    
    private MultiplierEnum(double factor) {
        this.factor = factor;
    }
    
    public double factor() {
        return factor;
    }
}
