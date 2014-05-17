package prp2_2a.models;

import prp2_2a.base.KeyConstants;

/**
 *
 * @author Timmay
 */
public class Pedal extends ControlElement implements KeyConstants {
    
    private boolean pushed;

    public Pedal() {
        this(1.0);
    }
    
    public Pedal(double maxPosition) {
        super(maxPosition, 0.0, 0.1);
        this.pushed = false;
    }
    
    @Override
    public void simulateStep(){
        if (isPushed()) {
            this.increase();
            setPushed(false);
        } else {
            this.decrease();
        }
    }
    
    public void push(){
        setPushed(true);
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }
}
