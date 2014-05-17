

package prp2_2a.adt.impl;
import prp2_2a.adt.interfaces.*;

/**
 *
 * @author Timmay
 */
public final class Values {
    
    private Values() {}
    
    // PUBLIC CONSTANTS
    public static final Length ZERO_LENGTH = lengthInM(0.0);
    public static final TimeDiff ZERO_TIMEDIFF = timeDiffInS(0.0);
    
    // PRIVATE CONSTANTS
    static final double FEET_IN_METERS = 0.3048;
    
    public static Length lengthInM(double meters) { 
        //TODO: fit method call to actual implementation (val, multi, unit)
        return LengthInM.valueOf(meters); 
    } 
 
    public static Length lengthInFt(double feet) { 
        //TODO: fit method call to actual implementation (val, multi, unit)
        return LengthInM.valueOf(feet * FEET_IN_METERS); 
    }
}
