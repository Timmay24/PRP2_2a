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
public interface Length extends Scalar/*, Comparable <Length>*/ {
    public Length add(Length length);
    public Length sub(Length length);
    public Length mul(double factor);
    public Length div(double factor);
    public double div(Length length);
    
    public Area   mul(Length length);

    public double m();
    public double km();
    public double ft();
}
