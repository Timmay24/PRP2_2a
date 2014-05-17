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
public interface Mass {
    public Mass   add(Mass mass);
    public Mass   sub(Mass mass);
    public Mass   mul(double factor);
    public Mass   div(double factor);
    public double div(Mass mass);
    
}
