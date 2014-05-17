/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 * speed = m/s
 */
public interface Speed {
    public Speed  add(Speed speed);
    public Speed  sub(Speed speed);
    public Speed  mul(double factor);     //  m/s * 1 = m/s
    public Speed  div(double factor);     // (m/s)/ 1 = m/s
    public double div(Speed speed);       // (m/s)/(m/s) = 1
    
    public Length mul(TimeDiff timeDiff); //  m/s * s = m
    public Acc    div(TimeDiff timeDiff); //  m/s / s = m/s2   
}
