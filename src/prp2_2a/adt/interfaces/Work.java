/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 * work = J = N·m = kg * m2 / s2
 * needs power
 */
public interface Work {
    public Work   add(Work work);
    public Work   sub(Work work);
    public Work   mul(double factor);
    public Work   div(double factor);
    public double div(Work work);
    
    public Power  div(Length length);
}
