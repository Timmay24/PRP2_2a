/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.base;

import jgame.platform.JGEngine;

/**
 *
 * @author Timmay
 */
public final class KeyHandler implements KeyConstants {
    
    public enum KeyState {
        PRESSED,
        RELEASED,
        KEYDOWN,
        KEYUP;
    }
    
    private KeyState[] keyStates;
    private final JGEngine engineRef;

    public KeyHandler(JGEngine engineRef) {
        setKeyStates(new KeyState[90]);
        for (int i=0; i<90; i++) {
            getKeyStates()[i] = KeyState.KEYUP;
        }
        this.engineRef = engineRef;
    }

    public KeyState[] getKeyStates() {
        return keyStates;
    }
    
    private boolean updateKey(int keyCode) {
         boolean result = engineRef.getKey(keyCode);
         
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
