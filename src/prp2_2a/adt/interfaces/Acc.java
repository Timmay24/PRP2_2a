/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.adt.interfaces;

/**
 *
 * @author Timmay
 * acc = m * s-2
 */
public interface Acc {
    public Acc    add(Acc acc);
    public Acc    sub(Acc acc);
    public Acc    mul(double factor);
    public Acc    div(double factor);
    public double div(Acc acc);
    
    public Speed  div(TimeDiff timeDiff); // m/s2 * 1/s = m/s
}
