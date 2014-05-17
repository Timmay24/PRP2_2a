/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 * volume = m3
 */
public interface Volume {
    public Volume add(Volume volume);
    public Volume sub(Volume volume);
    public Volume mul(double factor);
    public Volume div(double factor); // m3 / 1  = m3
    public double div(Volume volume); // m3 / m3 = m
    
    public Length div(Area area);     // m3 / m2 = m
    public Area   div(Length length); // m3 / m  = m2
}
