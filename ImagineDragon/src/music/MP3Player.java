/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * class for MP3Player
 * @author Paul
 */
public class MP3Player implements IPlayer{
    
    private Player player = null;    
    private final String filename;
    
    /**
     * constructor of the MP3Player
     * @param filename url of the file
     * @throws IllegalArgumentException if no url was given
     */
    public MP3Player(String filename) throws IllegalArgumentException{
        this.filename = filename;
        InputStream instream = this.getClass().getClassLoader().getResourceAsStream(filename);
        try {
            instream.available();
            instream.close();
        } 
        catch (IOException ex) {
            throw new IllegalArgumentException("Datei '"+filename+"' kann nicht abgespielt werden!");
        }
        
    }
    
    /**
     * plays music
     */
    @Override
    public synchronized void play() {
        new Thread() {
            @Override
            public void run() {
                while(true){                    
                    try { 
                        InputStream instream = this.getClass().getClassLoader().getResourceAsStream(filename);
                        player = new Player(instream);
                        player.play();
                        if(player == null){
                            break;
                        }
                        else{
                            player.close();                        
                        }
                    } 
                    catch (JavaLayerException ex) {
                        throw new IllegalArgumentException("Datei '"+filename+"' kann nicht abgespielt werden!");
                    } 
                }
            }
        }.start();
    }

    /**
     * stops music
     */
    @Override
    public synchronized void stop() {
        if(player != null){                        
            player.close();
            player = null;
        }
    }
    
}
