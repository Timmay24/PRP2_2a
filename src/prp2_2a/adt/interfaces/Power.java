/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 * power = N = kg * m / s2
 */
public interface Power {
    public Power  add(Power power);
    public Power  sub(Power power);
    public Power  mul(double factor);
    public Power  div(double factor);
    public double div(Power power);
    
    public Work   mul(Length length);
}
