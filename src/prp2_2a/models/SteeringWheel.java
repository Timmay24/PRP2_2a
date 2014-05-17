package prp2_2a.models;

import prp2_2a.base.KeyConstants;

/**
 *
 * @author Timmay
 */
public class SteeringWheel extends ControlElement implements KeyConstants {

    private final double deadZone = 0.1;
    private boolean changedPos;
    
    public SteeringWheel(double maxPosition, double minPosition) {
        super(maxPosition, minPosition, 0.1);
    }
    
    public SteeringWheel() {
        this(1.0, -1.0);
    }
    
    @Override
    public void simulateStep() {
        if (!changedPos) {
            if (position() > deadZone) {
                decrease();
            } else if (position() < deadZone) {
                increase();
            } else {
                position(0.0);
            }
        }
        changedPos = false;
    }
    
    public void steerLeft() {
        decrease();
    }
    
    public void steerRight() {
        increase();
    }
}
