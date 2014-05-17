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
public interface Area {
    public Area   add(Area area);
    public Area   sub(Area area);
    public Area   mul(double factor);
    public Area   div(double factor);
    public double div(Area area);
    
    public Volume mul(Length length);
    public Length div(Length length);
}
