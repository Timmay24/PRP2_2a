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
    
    MICRO(1E-6),
    MILLI(1E-3),
    CENTI(1E-2),
    DECI(1E-1),
    DEKA(1E1),
    HECTO(1E2),
    KILO(1E3),
    MEGA(1E6),
    GIGA(1E9);
    
    private double factor = 1.0;
    
    private MultiplierEnum(double factor) {
        this.factor = factor;
    }
    
    public double factor() {
        return factor;
    }
}
