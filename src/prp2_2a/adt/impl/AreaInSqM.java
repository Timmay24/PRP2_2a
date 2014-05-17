package prp2_2a.adt.impl;

import prp2_2a.adt.interfaces.Area;
import prp2_2a.adt.interfaces.Length;
import prp2_2a.adt.interfaces.Volume;

import prp2_2a.adt.interfaces.MultiplierEnum;
//import static prp2_2a.adt.interfaces.MultiplierEnum.*;

/**
 *
 * @author Timmay
 */
final class AreaInSqM extends AbstractScalar implements Area {
    private final double value;
    
    // PRIVATE CONSTRUCTOR
    /* BASE METHOD */
    private AreaInSqM(double value, double multiplier) {
        this.value = value * multiplier;
    }
    
    
    // STATIC FACTORY METHODS valueOf
    /* BASE METHOD */
    public static Area valueOf(double value, double multiplier) {
        return new AreaInSqM(value, multiplier);
    }
    
    public static Area valueOf(double value, MultiplierEnum multiplier) {
        return new AreaInSqM(value, multiplier.factor());
    }
    
    public static Area valueOf(double value) {
        return new AreaInSqM(value, 1.0);
    }
    
    
    // ARITHMETIC OPERATIONS
    @Override
    public Area add(Area area) {
        return new AreaInSqM(this.value() + area.value());
    }
    
    @Override
    public Area sub(Area area) {
        
    }
    
    @Override
    public Area mul(double factor) {
        
    }
    
    @Override
    public Area div(double factor) {
        
    }
    
    @Override
    public double div(Area area) {
        
    }
    
    @Override
    public Volume mul(Length length) {
        
    }
    
    @Override
    public Length div(Length length) {
        
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
    
}
