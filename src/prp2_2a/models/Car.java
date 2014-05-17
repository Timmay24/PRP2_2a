/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prp2_2a.models;

import prp2_2a.utils.Helper;
import java.text.DecimalFormat;
import java.util.Objects;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Car implements VehicleInterface {
    
    // CONSTANT ATTRIBUTES
    private final double   mass;
    private final double   powerPropMax;  
    private final double   speedMaxInMPS;
    private final String   name;
    private final SteeringWheel steeringWheel;
    private final Pedal    acceleratorPedal;
    private final Pedal    brakePedal;
    private final double   curveRadius;
    private final JGObject sprite;
    private final String   tblFile; //wird von JGEngine in defineMedia() benutzt
    
    // CONSTANTS
    private final double accEarth = 9.81;
    private final DecimalFormat f = new DecimalFormat("#000.00"); 
    
    // VARIABLE ATTRIBUTES
    private double speed;
    private double proplevel;
    private double acc;
    private TractionEnum traction;
    private boolean asrOn;
    private boolean absOn;
    private double posX;
    private double posY;
    private double courseAngle;
    private boolean absEngaged;
    private boolean asrEngaged;
    private final JGEngine engine;

    // CONSTRUCTOR
    //Wieso geht dort keine Delegation?? - cannot find symbol
//    public Car() {
//        Car(CarEnum.Porsche911GT2RS);
//    }
    
    public Car(CarEnum carEnum, JGEngine engine){
        this.mass             = carEnum.mass;
        this.powerPropMax     = carEnum.powerPropMax;
        this.speedMaxInMPS    = carEnum.speedMaxInMPS;
        this.steeringWheel    = new SteeringWheel();
        this.acceleratorPedal = new Pedal();
        this.brakePedal       = new Pedal();
        this.name             = carEnum.name;
        this.curveRadius      = carEnum.curveRadius;
        this.asrOn            = true;
        this.absOn            = true;
        this.tblFile          = carEnum.tblFile;
        this.traction         = TractionEnum.REGULAR;
        this.courseAngle            = 0.0;
        this.engine           = engine;
        engine.defineMedia(carEnum.tblFile);
        this.sprite           = new JGObject(this.name, true, 400, 304, 0, "p911-0");
    }
    
    // OPERATIONS
    @Override
    public void simulateStep(double deltaTimeInS){
        
        steeringWheel.simulateStep();
        acceleratorPedal.simulateStep();
        brakePedal.simulateStep();
        
        double accPedLevel = acceleratorPedal.position();
        double brkPedLevel = brakePedal.position();
        double steerLevel  = steeringWheel.position();
        
        // Antriebsleistung, je nach Stellung des Gaspedals
        double powerProp   = accPedLevel * powerPropMax(); // ppMax = 456000
        
        if (speed() <= 0.1) {
            if (powerProp < powerPropMax() * 0.1) {
                Car.this.speed(0);
            } else {
                Car.this.speed(0.1);
            }
        }
        
        double maxAcc = accEarth() * traction.value();

        // maximale Kraft, die aufgrund der Traktion übertragen werden kann
        double forceTraction  =  mass() * maxAcc;
        
        // tatsächlich ausgeübte Kraft (ASR reguliert forcePropAbs, falls Reifen durchdrehen würden)
        double forcePropAbs;
        if (speed() >= 0.1){
            forcePropAbs = powerProp / speed();
            if (asrOn) {
                if (forcePropAbs > forceTraction) {
                    forcePropAbs = forceTraction;
                    asrEngaged = true;
                } else {
                    asrEngaged = false;
                }
            }
        } else {
            forcePropAbs = 0.0;
        }
        
        // Antriebskraft des Autos (ggf. reguliert durch ASR)
        double forceProp = forcePropAbs; //* Helper.sign(accPedLevel);
        
        // Bremskraft (wird durch ABS nach unten korrigiert, falls aktiviert)
        double forceBrake;//    = (getSpeed() >= 0.1)?(getMass() * getAccEarth() * brkPedLevel * 10):(0);
        if (speed() >= 0.1) {
            forceBrake = forceTraction * brkPedLevel;
            if (absOn) {
                if (forceBrake > forceTraction) {
                    forceBrake = forceTraction;
                    absEngaged = true;
                } else {
                    absEngaged = false;
                }
            }
        } else {
            forceBrake = 0.0;
        }
        
        // Luftwiderstandskonstante
        double dragConst =  Math.abs(powerPropMax() / Math.pow(speedMax(), 3.0));
        
        // Kraft des Luftwiderstand
        double forceDrag =  dragConst * Math.pow(speed(), 2.0) * Helper.sign(-speed());
        
        // resultierende Kraft
        double force     =  forceProp + forceDrag - forceBrake;
        
        // Beschleunigung berechnen
        acc(force / mass());
        
        // Geschwindigkeit berechnen
        speed(speed() + (acc() * deltaTimeInS));
        
        // Positionsveränderung (ungerichtet) berechnen
        double deltaPos = speed() * deltaTimeInS;
        
        // Kursänderung erfassen
        /*
            Auto dreht sich noch heftig im kleinen Kreis.
            Möglicherweise hängt es mit der Skalierung der Werte im Bezug
            auf das Spielfeld zusammen, da nun die Position direkt über
            setPos() beim Sprite gesetzt wird und keine Richtungsgeschwindigkeit
            per setSpeed().
        
            AUßERDEM fehlt noch die Ansprache des Lenkrades per Tastatur.
            Zudem muss geprüft werden, ob das Lenkrad richtig reagiert und
            der Rückstellungsalgorithmus korrekt funktioniert.
        */
        double deltaCourseAngle;
        if (steerLevel < 0.1 & 0.1 > steerLevel) {
            deltaCourseAngle = 0.0;
        } else {
            deltaCourseAngle = deltaPos / curveRadius * (steeringWheel.maxPosition() / steerLevel);
        }
        
        // Neuen Kurs berechnen
        courseAngle(courseAngle() + deltaCourseAngle);
        
        // Positionsänderung (gerichtet) anhand aktuellen Kurses berechnen
        double deltaX = deltaPos * Math.cos(courseAngle());
        double deltaY = deltaPos * Math.sin(courseAngle());
        
        /*sprite.setSpeed((Math.cos(angleInDeg(courseAngle())) * (speed() / 3.6)),
                        (Math.sin(angleInDeg(courseAngle())) * (speed() / 3.6)) );*/
        
        sprite.setPos(sprite.getLastX() + deltaX,
                      sprite.getLastY() + deltaY);

        // Sprite gemäß Winkel ausrichten
        rotateSprite();
    }
    
    public double angleInDeg(double angle) {
        return Math.PI / 180 * angle;
    }
    
    public double accEarth() {
        return Helper.accEarth;
    }
    
    public double acc() {
        return acc;
    }

    public void acc(double acc) {
        this.acc = acc;
    }
    
    public String absState(){
        return (absOn)?("on"):("off");
    }
    
    public String asrState(){
        return (asrOn)?("on"):("off");
    }
    
    public Pedal acceleratorPedal() {
        return acceleratorPedal;
    }
    
    public double courseAngle() {
        return this.courseAngle;
    }
    
    public void courseAngle(double angle) {
        angle = Helper.moduloCyclic(angle, 360);
        this.courseAngle = angle;
        System.out.println("courseAngle: " + courseAngle());
    }
    
    public boolean isAbsEngaged() {
        return absEngaged;
    }

    public boolean isAsrEngaged() {
        return asrEngaged;
    }

    public Pedal brakePedal() {
        return brakePedal;
    }

    public JGObject sprite() {
        return sprite;
    }

    public void pushAccelerator(){
        acceleratorPedal.push();
    }
    
    public void pushBrake(){
        brakePedal.push();
    }
    
    @Override
    public double posX() {
        return posX;
    }

    public void posX(double posX) {
        this.posX = posX;
    }

    @Override
    public double posY() {
        return posY;
    }
    
    public void posY(double posY) {
        this.posY = posY;
    }
    
    public void rotateSprite(){
        sprite.setImage("p911-" + (int) courseAngle());
    }

    private void steering(int direction){
        if (speed() > 1.0) {
            courseAngle(courseAngle() + (direction * (1)));
        }
    }
    
    public void steerLeft(){
        steering(-1);
    }
    
    public void steerRight(){
        steering(1);
    }
    
    public void toggleASR(){
        if (asrOn){
            asrOn = false;
        }else{
            asrOn = true;
        }
        System.err.println("ASR toggled " + asrOn);
    }
    
    public void toggleABS(){
        if (absOn){
            absOn = false;
        }else{
            absOn = true;
        }
        System.err.println("ABS toggled " + absOn);
    }

    public TractionEnum traction() {
        return traction;
    }

    public void traction(TractionEnum traction) {
        this.traction = traction;
    }

    
    public String name() {
        return name;
    }

    public double speed() {
        return speed;
    }

    public void speed(double speed) {
        this.speed = speed;
    }

    public double proplevel() {
        return proplevel;
    }

    public void proplevel(double proplevel) {
        this.proplevel = proplevel;
    }

    public double mass() {
        return mass;
    }

    public double powerPropMax() {
        return powerPropMax;
    }

    public double speedMax() {
        return speedMaxInMPS;
    }

    @Override
    public String toString() {
        return "Porsche911GT2RS{" + 
                "speed=" + f.format(speed()) + 
                "m/s / " + f.format(speed() * 3.6) + 
                "km/h , acc=" + f.format(acc()) + 
                "m/s², proplevel=" + f.format(Car.this.proplevel()) + '}';
    }
    
    public void printStats() {
        System.out.println(toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.mass) ^ (Double.doubleToLongBits(this.mass) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.powerPropMax) ^ (Double.doubleToLongBits(this.powerPropMax) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.speedMaxInMPS) ^ (Double.doubleToLongBits(this.speedMaxInMPS) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.acceleratorPedal);
        hash = 59 * hash + Objects.hashCode(this.brakePedal);
        hash = 59 * hash + Objects.hashCode(this.sprite);
        hash = 59 * hash + Objects.hashCode(this.tblFile);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.accEarth) ^ (Double.doubleToLongBits(this.accEarth) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.f);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.speed) ^ (Double.doubleToLongBits(this.speed) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.proplevel) ^ (Double.doubleToLongBits(this.proplevel) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.acc) ^ (Double.doubleToLongBits(this.acc) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.traction);
        hash = 59 * hash + (this.asrOn ? 1 : 0);
        hash = 59 * hash + (this.absOn ? 1 : 0);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.posX) ^ (Double.doubleToLongBits(this.posX) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.posY) ^ (Double.doubleToLongBits(this.posY) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.courseAngle) ^ (Double.doubleToLongBits(this.courseAngle) >>> 32));
        hash = 59 * hash + (this.absEngaged ? 1 : 0);
        hash = 59 * hash + (this.asrEngaged ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.engine);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (Double.doubleToLongBits(this.mass) != Double.doubleToLongBits(other.mass)) {
            return false;
        }
        if (Double.doubleToLongBits(this.powerPropMax) != Double.doubleToLongBits(other.powerPropMax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.speedMaxInMPS) != Double.doubleToLongBits(other.speedMaxInMPS)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.acceleratorPedal, other.acceleratorPedal)) {
            return false;
        }
        if (!Objects.equals(this.brakePedal, other.brakePedal)) {
            return false;
        }
        if (!Objects.equals(this.sprite, other.sprite)) {
            return false;
        }
        if (!Objects.equals(this.tblFile, other.tblFile)) {
            return false;
        }
        if (Double.doubleToLongBits(this.accEarth) != Double.doubleToLongBits(other.accEarth)) {
            return false;
        }
        if (!Objects.equals(this.f, other.f)) {
            return false;
        }
        if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed)) {
            return false;
        }
        if (Double.doubleToLongBits(this.proplevel) != Double.doubleToLongBits(other.proplevel)) {
            return false;
        }
        if (Double.doubleToLongBits(this.acc) != Double.doubleToLongBits(other.acc)) {
            return false;
        }
        if (this.traction != other.traction) {
            return false;
        }
        if (this.asrOn != other.asrOn) {
            return false;
        }
        if (this.absOn != other.absOn) {
            return false;
        }
        if (Double.doubleToLongBits(this.posX) != Double.doubleToLongBits(other.posX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.posY) != Double.doubleToLongBits(other.posY)) {
            return false;
        }
        if (Double.doubleToLongBits(this.courseAngle) != Double.doubleToLongBits(other.courseAngle)) {
            return false;
        }
        if (this.absEngaged != other.absEngaged) {
            return false;
        }
        if (this.asrEngaged != other.asrEngaged) {
            return false;
        }
        if (!Objects.equals(this.engine, other.engine)) {
            return false;
        }
        return true;
    }
}
