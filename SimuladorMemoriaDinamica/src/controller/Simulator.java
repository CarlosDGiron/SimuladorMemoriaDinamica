/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author cana0
 */
public class Simulator {
    Memory memory;
    ArrayList<model.Process> pendingProcessList;
    
    public Simulator(int memorySizeInKilobytes){
        memory=new Memory(memorySizeInKilobytes);
        pendingProcessList=new ArrayList<model.Process>();
    }
    
    public Simulator(){
        pendingProcessList=new ArrayList<model.Process>();
    }
    
    public void addProcess(int id, int memoryUsageInKylobytes, String name, int arryvalInstant, int durationInInstants){
        model.Process newProcess= new model.Process(id, memoryUsageInKylobytes, name, arryvalInstant, durationInInstants);
        this.pendingProcessList.add(newProcess);
    }
    
    public void startSimulation(){
        this.checkSpaceForPendingProcess();
        memory.fordwardInstant();
        saveInJson();
    }
    
    public void checkSpaceForPendingProcess(){
        if(!pendingProcessList.isEmpty()){
            for(model.Process iterator:pendingProcessList){
                if(memory.currentInstant>=iterator.arryvalInstant){
                    memory.insertProcess(iterator);
                    pendingProcessList.remove(iterator);
                }
            }
        }
    }
    
    public void saveInJson(){
     //TO DO agregar referencia metodo para guardar memoria actual en json enviando la memoria;
    }
    
    public void loadFromJson(int memorySizInKilobytes, ArrayList<model.Process> jsonListOfProcesses){
        memory=new Memory(memorySizInKilobytes);
        this.pendingProcessList=jsonListOfProcesses;
    }
    
}
