package main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import controller.Simulator;
import view.MainView;

/**
 *
 * @author cana0
 */
public class main {
    public String tamañoMemoria = "";
    public String tamañoSO = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new MainView().setVisible(true);
            }
        });
    }

}
