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
public interface Angle {
    public Angle  add(Acc angle);
    public Angle  sub(Acc angle);
    public Angle  mul(double factor);
    public Angle  div(double factor);
    public double div(Angle angle);
}
