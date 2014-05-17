package prp2_2a.base;

import prp2_2a.models.Car;
import prp2_2a.models.CarEnum;
import java.text.DecimalFormat;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import prp2_2a.models.TractionEnum;

/**
 *
 * @author Marvin
 */
public class Engine extends JGEngine implements KeyConstants {

    private long lastTimeMillis;
    private double diff;
    private double gameTime;
    private Car car;
    private final DecimalFormat f = new DecimalFormat("#000.00"); 
    private final int LINE = 20;
    
    public Engine(JGPoint size) {
        // This inits the engine as an application.
        initEngine(size.x, size.y);
    }

    @Override
    public void initCanvas() {
        setCanvasSettings(
                50, // width of the canvas in tiles
                38, // height of the canvas in tiles
                16, // width of one tile
                16, // height of one tile
                //    (note: total size = 50*16=800  x  38*16=608)
                null,// foreground colour -> use default colour white
                new JGColor(150, 150, 150),// background colour
                null // standard font -> use default font
                );
        
    }

    @Override
    public void initGame() {
        setFrameRate(
                100, // 100 = frame rate, 100 frames per second
                  6  //  6  = frame skip, skip at most 6 frames before displaying
                     //       a frame again
                );
        
        // Tastenzustands Array init.
        initKeyStates();

        // Hintergrund laden
        defineImage("bgimage","-",0,"racetrack.png","-");
        setBGImage("bgimage");
        
        // Porsche anmelden und initialisieren
        car = new Car(CarEnum.Porsche911GT2RS, this);
        
        lastTimeMillis = System.currentTimeMillis();
    }

    /** Game logic is done here.  No painting can be done here, define
     * paintFrame to do that. */
    @Override
    public void doFrame() {
        
        // aktuellen Systemzeitstempel holen
        long currentMillis = System.currentTimeMillis();

        if (getKey(KeyUP)){
            car.pushAccelerator();
        }
        
        if (getKey(KeyDOWN)){
            car.pushBrake();
        }
        
        if (getKey(KeyLEFT)){
            car.steerLeft();
        }
        
        if (getKey(KeyRIGHT)){
            car.steerRight();
        }
        
        if (keyPressed(Key1)) {
            car.traction(TractionEnum.REGULAR);
        }
        
        if (keyPressed(Key2)) {
            car.traction(TractionEnum.WET);
        }
        
        if (keyPressed(Key3)) {
            car.traction(TractionEnum.SNOW);
        }
        
        if (keyPressed(Key4)) {
            car.traction(TractionEnum.ICE);
        }
        
        if (keyPressed(KeyQ)) {
            car.toggleABS();
        }
        
        if (keyPressed(KeyW)) {
            car.toggleASR();
        }
        
        if (getKey(KeyESC)) {
            exitEngine("Programm wird heruntergefahren...");
        }
        
        // simulateStep rechnet intern mit Sekunden, daher muss die Delta-Zeit
        // vorher in Milisekunden umgerechnet werden, da sonst in einer Sekunde
        // ein Zeitraum von 1000 Sekunden simuliert werden würde
        diff = (currentMillis - lastTimeMillis) / 1000D;
        gameTime += diff;
        
        car.simulateStep(diff); 
        
        moveObjects(null,0);
        
        lastTimeMillis = System.currentTimeMillis();
        
        setPFWrap(
                true,       // horizontal wrap
                true,       // vertical wrap
                -10, -10    // shift the center of the view to make objects wrap at
                            // the right moment (sprite size / 2).
        );
        
        setViewOffset(
			(int) car.sprite().x,
                        (int) car.sprite().y, // the position within the playfield
			true       // true means the given position is center of the view,
			           // false means it is topleft.
                    );
    }

    /** Any graphics drawing beside objects or tiles should be done here.
     * Usually, only status / HUD information needs to be drawn here. */
    @Override
    public void paintFrame() {
        setColor(JGColor.black);
        
        JGFont font = new JGFont(null,0,12);
        JGColor jgcWhite  = new JGColor(255, 255, 255);
        JGColor jgcYellow = new JGColor(255, 255,   0);
        
        // Tastatureingaben im Array aktualisieren
        for (int i=0; i< getKeyStates().length; i++) {
            updateKey(i);
        }
        
        // Status in der GUI ausgeben
        
        int line = 1;
        
        drawString("Speed: " + f.format(car.speed() * 3.6) + " km/h  " +
                   "ABS: " + car.absState() + "  " +
                   "ASR: " + car.asrState(),
                    10, LINE*line++, -1, font, jgcWhite);
        
        drawString("AccPedal: " + f.format(car.acceleratorPedal().position()) + "  " +
                   "BrkPedal: " + f.format(car.brakePedal().position()),
                    10, LINE*line++, -1, font, jgcWhite);
                    
        drawString("Game Time: " + f.format(gameTime) + " s",
                    10, LINE*line++, -1, font, jgcWhite);
        
        drawString("Ground: " + car.traction().name(),
                    10, LINE*line++, -1, font, jgcWhite);
        
        drawString("Angle: " + car.courseAngle(),
                    10, LINE*line++, -1, font, jgcWhite);
        
        if (car.isAbsEngaged()) {
            drawString("ABS engaged", 10, LINE*line, -1, font, jgcYellow);
        }
        
        if (car.isAsrEngaged()) {
            drawString("ASR engaged", 100, LINE*line, -1, font, jgcYellow);
        }
        
    }
    
    /*** Tastendruckbehandlung ***/
    
    private KeyState[] keyStates;
    
    // Tastendruck Zustandskonstanten
    private enum KeyState {
        PRESSED,
        RELEASED,
        KEYDOWN,
        KEYUP;
    }
    
    private boolean initKeyStates() {
        setKeyStates(new KeyState[90]);
        for (int i=0; i<90; i++) {
            getKeyStates()[i] = KeyState.KEYUP;
        }
        return true;
    }
    
    public KeyState[] getKeyStates() {
        return keyStates;
    }
    
    private boolean updateKey(int keyCode) {
         boolean result = getKey(keyCode);
         
         if (result) {
             if (keyDown(keyCode))
                 getKeyStates()[keyCode] = KeyState.KEYDOWN;
             else
                 getKeyStates()[keyCode] = KeyState.PRESSED;
         } else {
             if (keyDown(keyCode))
                 getKeyStates()[keyCode] = KeyState.RELEASED;
             else
                 getKeyStates()[keyCode] = KeyState.KEYUP;
         }
         
         return result;
    }

    public void setKeyStates(KeyState[] keyStates) {
        this.keyStates = keyStates;
    }
    
        public boolean keyPressed(int keyCode) {
        return (getKeyStates()[keyCode] == KeyState.PRESSED);
    }
    
    public boolean keyReleased(int keyCode) {
        return (getKeyStates()[keyCode] == KeyState.RELEASED);
    }
    
    public boolean keyDown(int keyCode) {
        return ((getKeyStates()[keyCode] == KeyState.PRESSED) ||
                (getKeyStates()[keyCode] == KeyState.KEYDOWN));
    }
    
    public boolean keyUp(int keyCode) {
        return !keyDown(keyCode);
    }
}