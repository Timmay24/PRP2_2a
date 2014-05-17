/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.models;

/**
 *
 * @author Timmay
 */
public enum TractionEnum {
    ICE(0.1),
    SNOW(0.3),
    WET(0.7),
    REGULAR(1.0);
    
    double traction;
    
    private TractionEnum(double traction){
        this.traction = traction;
    }
    
    public double value() {
        return traction;
    }
    
    public void value(double value){
        this.traction = value;
    }
}
