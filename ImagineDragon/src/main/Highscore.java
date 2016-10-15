/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import pictures.ImageLoader;
import pictures.Pictures;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import music.MP3Player;

/**
 * Form that showa the highscore
 * @author Paul
 */
public class Highscore extends javax.swing.JFrame implements ActionListener{
    
    private pictures.ImageLoader loader;
    private music.MP3Player player;
    private Connection connect;
    private Statement order;
    private String statement;
    private ResultSet result;
    private String output;
    private byte place;

    /**
     * Creates new form Highscore
     */
    public Highscore(){
        this.setUndecorated(true);
        this.loader = ImageLoader.getInstance();
        this.setContentPane(new JLabel(new ImageIcon(loader.getImage(Pictures.Normal))));
        this.initComponents();
        this.init();
    }
    
    /**
     * inits the form and sql
     */
    private void init(){
        this.close.addActionListener(this);
        this.statement = null;
        this.result = null;
        this.output = "<html>";
        this.place = 1;
        this.setSize(1500, 1000);
        this.setResizable(false);
        this.player = new MP3Player("music/files/Gold.mp3");
        this.player.play();
        this.connect = null;
        this.order = null;
        try{
            Class.forName("org.sqlite.JDBC");
            this.connect = DriverManager.getConnection("jdbc:sqlite:Memory.db");
            this.order = connect.createStatement();
            this.statement = "SELECT * FROM Memory order by Score desc";
            this.result = order.executeQuery(statement);
            while (this.result.next()){
                this.output += String.valueOf(this.place) + ". "+ this.result.getString("Name") + "   Level:" + String.valueOf(this.result.getInt("Level")) + "   Score:" + String.valueOf(this.result.getInt("Score") + "<br>");     
                if (this.place > 9){
                    break;
                }
                this.place++;
            }
            this.output = this.output.concat("</html>");
            System.out.println(this.output);
            highscoreView.setLocation(750, 100);
            
            highscoreView.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
            this.highscoreView.setText(output);
        }   
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        highscoreView = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        close.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        close.setText("Close");
        close.setBorderPainted(false);

        jLabel1.setFont(new java.awt.Font("Wide Latin", 3, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("Highscore");

        highscoreView.setText("Highscore");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(828, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(highscoreView, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(132, 132, 132)
                .addComponent(highscoreView, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Highscore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Highscore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Highscore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Highscore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Highscore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JLabel highscoreView;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    /**
     * KeyListener
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.close){
            this.player.stop();
            this.setVisible(false);
            Menue menue = new Menue();
            menue.setVisible(true);
        }
    }
}
