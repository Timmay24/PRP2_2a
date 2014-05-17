
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
public enum LengthUnitEnum {
    M(1.0),
    FT(0.3048),
    NM(1.0d / 1852.0d);

    private double factor = 1.0;

    private LengthUnitEnum(double factor) {
            this.factor = factor;
    }

    public double factor() {
        return factor;
    }
}
