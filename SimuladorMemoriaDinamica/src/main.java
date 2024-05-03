/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import controller.Simulator;
/**
 *
 * @author cana0
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulator sim=new Simulator(72000);
        sim.addSO(2000);
        sim.addProcess(1, 16000, "A", 1, 5);
        sim.addProcess(2, 4000, "B", 2,2);
        sim.addProcess(3, 12000, "C",3, 6);
        sim.addProcess(4, 10000, "D", 4, 4);
        sim.addProcess(5, 8000, "E", 5, 6);
        sim.addProcess(6, 14000, "F", 6, 5);
        sim.simulate();
    }
    
}
