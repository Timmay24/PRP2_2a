package prp2_2a.adt.impl;

import prp2_2a.adt.interfaces.Length;
import prp2_2a.adt.interfaces.Area;

import prp2_2a.adt.interfaces.MultiplierEnum;
import static prp2_2a.adt.interfaces.MultiplierEnum.*;

import prp2_2a.adt.interfaces.LengthUnitEnum;
import static prp2_2a.adt.interfaces.LengthUnitEnum.*;

/**
 *
 * @author Timmay
 */
final class LengthInM extends AbstractScalar implements Length {
    private final double value;
    
    // PRIVATE CONSTRUCTOR
    /* BASE METHOD */
    private LengthInM(double value, double multiplier, LengthUnitEnum unit) {
        this.value = value * multiplier * unit.factor();
    }
    
    private LengthInM(double value, MultiplierEnum multiplier, LengthUnitEnum unit) {
        this(value, multiplier.factor(), unit);
    }
    
    
    // STATIC FACTORY METHODS valueOf
    /* BASE METHOD */
    public static Length valueOf(double value, double multiplier, LengthUnitEnum unit) {
        return new LengthInM(value, multiplier, unit);
    }
    
    /* BASE METHOD w/ Multiplier-Enum */
    public static Length valueOf(double value, MultiplierEnum multiplier, LengthUnitEnum unit) {
        return new LengthInM(value, multiplier, unit);
    }
    
    public static Length valueOf(double value, MultiplierEnum multiplier) {
        return new LengthInM(value, multiplier, M);
    }
    
    public static Length valueOf(double value) {
        return new LengthInM(value, 1.0, M);
    }
    
    
    // ARITHMETIC OPERATIONS
    @Override
    public Length add(Length length) {
        return LengthInM.valueOf(this.value + length.m());
    }
    
    @Override
    public Length sub(Length length) {
        return LengthInM.valueOf(this.value - length.m());
    }
    
    @Override
    public Length mul(double factor) {
        return LengthInM.valueOf(this.value * factor);
    }
    
    @Override
    public Area mul(Length length) {
        return AreaInSqM.valueOf(this.m() * length.m());
    }
    
    @Override
    public Length div(double factor) {
        return LengthInM.valueOf(this.m() / factor);
    }
    
    @Override
    public double div(Length length) {
        return this.m() / length.m();
    }
    
    // ACCESSORS
    /* BASE METHOD */
    public double value(double multiplier, LengthUnitEnum unit) {
        return this.value * multiplier / unit.factor();
    }
    
    public double value(MultiplierEnum multiplier, LengthUnitEnum unit) {
        return LengthInM.this.value(multiplier.factor(), unit);
    }
    
    public double value(MultiplierEnum multiplier) {
        return LengthInM.this.value(multiplier.factor(), M);
    }
    
    public double value(LengthUnitEnum unit) {
        return LengthInM.this.value(1.0, unit);
    }
    
    // STANDARD ACCESSOR
    @Override
    public double value() {
        return LengthInM.this.value(1.0, M);
    }
    
    // SPECIAL ACCESSORS
    @Override
    public double m() {
        return LengthInM.this.value();
    }
    
    @Override
    public double km() {
        return LengthInM.this.value(KILO);
    }
    
    @Override
    public double ft() {
        return LengthInM.this.value();
    }
}