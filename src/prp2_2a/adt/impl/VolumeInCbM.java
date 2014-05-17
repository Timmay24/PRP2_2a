package prp2_2a.adt.impl;

import prp2_2a.adt.interfaces.Volume;
import prp2_2a.adt.interfaces.Length;
import prp2_2a.adt.interfaces.Area;

import prp2_2a.adt.interfaces.MultiplierEnum;

/**
 *
 * @author Timmay
 */
final class VolumeInCbM extends AbstractScalar implements Volume {
    private final double value;
    
    
    // PRIVATE CONSTRUCTOR
    /* BASE METHOD */
    private VolumeInCbM(double value, double multiplier) {
        this.value = value * multiplier;
    }
    
    
    // STATIC FACTORY METHODS valueOf
    /* BASE METHOD */
    public static Volume valueOf(double value, double multiplier) {
        return new VolumeInCbM(value, multiplier);
    }
    
    public static Volume valueOf(double value, MultiplierEnum multiplier) {
        return new VolumeInCbM(value, multiplier.factor());
    }
    
    public static Volume valueOf(double value) {
        return new VolumeInCbM(value, 1.0);
    }
    
    
    // ARITHMETIC OPERATIONS
    @Override
    public Volume add(Volume volume) {
        return VolumeInCbM.valueOf(this.value + volume.value());
    }
    
    @Override
    public Volume sub(Volume volume) {
        return VolumeInCbM.valueOf(this.value - volume.value());
    }
    
    @Override
    public Volume mul(double factor) {
        return VolumeInCbM.valueOf(this.value * factor);
    }
    
    @Override
    public Volume div(double factor) {
        return VolumeInCbM.valueOf(this.value / factor);
    }
    
    @Override
    public double div(Volume volume) {
        return this.value / volume.value();
    }
    
    @Override
    public Length div(Area area) {
        return LengthInM.valueOf(this.value / area.value());
    }
    
    @Override
    public Area div(Length length) {
        return AreaInSqM.valueOf(this.value / length.value());
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
