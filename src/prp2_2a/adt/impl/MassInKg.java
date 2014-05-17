package prp2_2a.adt.impl;

import prp2_2a.adt.interfaces.Mass;

import prp2_2a.adt.interfaces.MultiplierEnum;

/**
 *
 * @author Timmay
 */
final class MassInKg extends AbstractScalar implements Mass {
    private final double value;
    
    
    // PRIVATE CONSTRUCTOR
    /* BASE METHOD */
    private MassInKg(double value, double multiplier) {
        this.value = value * multiplier;
    }
    
    
    // STATIC FACTORY METHODS valueOf
    /* BASE METHOD */
    public static Mass valueOf(double value, double multiplier) {
        return new MassInKg(value, multiplier);
    }
    
    public static Mass valueOf(double value, MultiplierEnum multiplier) {
        return new MassInKg(value, multiplier.factor());
    }
    
    public static Mass valueOf(double value) {
        return new MassInKg(value, 1.0);
    }
    
    
    // ARITHMETIC OPERATIONS
    @Override
    public Mass add(Mass mass) {
        return MassInKg.valueOf(this.value + mass.value());
    }
    
    @Override
    public Mass sub(Mass mass) {
        return MassInKg.valueOf(this.value - mass.value());
    }
    
    @Override
    public Mass mul(double factor) {
        return MassInKg.valueOf(this.value * factor);
    }
    
    @Override
    public Mass div(double factor) {
        return MassInKg.valueOf(this.value / factor);
    }
    
    @Override
    public double div(Mass mass) {
        return this.value / mass.value();
    }
    
    
    // ACCESSORS
    /* BASE METHOD */
    public double value(double multiplier) {
        return this.value * multiplier;
    }
    
    // STANDARD ACCESSOR
    @Override
    public double value() {
        return value(1.0);
    }
    
    // SPECIAL ACCESSORS
}