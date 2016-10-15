/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.States;

/**
 * Oberserver interface
 * @author Paul
 */
public interface IObserver {
    
    /**
     * update methode of the observer pattern
     * @param states state of the state machine
     */
    void update(States states);
}
