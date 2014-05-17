/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prp2_2a.models;

import jgame.JGObject;

/**
 *
 * @author Timmay
 */
public class Sprite extends JGObject {
    
    private String name, gfxname;
    private boolean unique_id;
    private double x, y;
    private int collisionid;
    
    private double xspeed, yspeed;
    
    public Sprite(String name, boolean unique_id, double x, double y,
                  int collisionid, String gfxname){
        
        super(name, unique_id, x, y, collisionid, gfxname);
        this.name = name;
        this.unique_id = unique_id;
        this.x = x;
        this.y = y;
        this.collisionid = collisionid;
        this.gfxname = gfxname;
    }
    
    @Override
    public void setGraphic(String gfxname){
        super.setGraphic(gfxname);
    }
    
    @Override
    public void setSpeed(double xspeed, double yspeed){
       super.setSpeed(xspeed, yspeed);
    }
}
