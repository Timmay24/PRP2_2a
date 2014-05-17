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
public enum CarEnum {
    
    Porsche911GT2RS(1445.0,456.0,330.0,10.9,"Porsche 911 GT2 RS", "../models/p911/p911.tbl");
    
    double mass, powerPropMax, speedMaxInMPS, curveRadius, KILO = 1000.0;
    String name, tblFile;

    private CarEnum(double mass, double powerPropMax, double speedMaxInMPS, double curveRadius, String name, String tblFile){
        this.mass          = mass;
        this.powerPropMax  = powerPropMax * KILO;
        this.speedMaxInMPS = speedMaxInMPS / 3.6;
        this.curveRadius = curveRadius;
        this.name = name;
        this.tblFile = tblFile;
    }

    @Override
    public String toString() {
        return "CarEnum{" + "mass=" + mass + ", powerPropMax=" + powerPropMax + ", speedMaxInMPS=" + speedMaxInMPS + ", curveRadius=" + curveRadius + ", KILO=" + KILO + ", name=" + name + ", tblFile=" + tblFile + '}';
    }
}
