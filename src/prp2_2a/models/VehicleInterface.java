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
public interface VehicleInterface {
    void simulateStep(double deltaTimeInS);
    double posX();
    double posY();
}
