package prp2_2a.adt.impl;

import prp2_2a.adt.interfaces.Mass;
import prp2_2a.adt.interfaces.Length;
import prp2_2a.adt.interfaces.Volume;

import prp2_2a.adt.interfaces.MultiplierEnum;
import static prp2_2a.adt.interfaces.MultiplierEnum.*;

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
    public Mass add(Mass mass) {
        
    }
    
    public Mass sub(Mass mass) {
        
    }
    
    public Mass mul(double factor) {
        
    }
    
    public Mass div(double factor) {
        
    }
    
    public double div(Mass mass) {
        
    }
    
    
    // ACCESSORS
    /* BASE METHOD */
    public double getValue(double multiplier) {
        return this.value * multiplier;
    }
    
    // STANDARD ACCESSOR
    @Override
    public double getValue() {
        return getValue(1.0);
    }
    
    // SPECIAL ACCESSORS
}
