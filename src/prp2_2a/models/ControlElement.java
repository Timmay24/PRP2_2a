/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.models;

/**
 *
 * @author Timmay
 */
public abstract class ControlElement {
    private double  position;
    private double  maxPosition;
    private double  minPosition;
    private double  alterationPerTick;
    
    public ControlElement(double maxPosition, double minPosition, double alterationPerTick) {
        this.position = 0.0;
        this.maxPosition = maxPosition;
        this.minPosition = minPosition;
        this.alterationPerTick = alterationPerTick;
    }
    
    public abstract void simulateStep();
    
    public void increase() {
        if (position < this.maxPosition()) {
            position += this.alterationPerTick();
        } else {
            position = this.maxPosition();
        }
    }

    public void decrease() {
        if (position > this.minPosition()) {
            position -= this.alterationPerTick();
        } else {
            position = this.minPosition();
        }
    }

    public double position() {
        return position;
    }
    
    public void position(double position) {
        this.position = position;
    }

    public double alterationPerTick() {
        return this.alterationPerTick;
    }

    public void alterationPerTick(double alterationPerTick) {
        this.alterationPerTick = alterationPerTick;
    }

    public double maxPosition() {
        return this.maxPosition;
    }

    public void maxPosition(double maxPosition) {
        this.maxPosition = maxPosition;
    }

    public double minPosition() {
        return minPosition;
    }

    public void minPosition(double minPosition) {
        this.minPosition = minPosition;
    }
    
    
}
