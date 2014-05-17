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
public interface TimeDiff {
    public TimeDiff add(TimeDiff timeDiff);
    public TimeDiff sub(TimeDiff timeDiff);
    public TimeDiff mul(double factor);     // s * 1 = s
    public TimeDiff div(double factor);     // s / 1 = s
    public double   div(TimeDiff timeDiff); // s / s = 1
}
