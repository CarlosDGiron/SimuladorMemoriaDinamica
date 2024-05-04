/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import model.*;

/**
 *a
 * @author cana0
 */
public class Simulator {
    public Memory memory;
    String simulationSaveFileName;
    ArrayList<Memory> memoryInstantList;
    ArrayList<model.Process> pendingProcessList;
    Gson gson;
    
    public Simulator(int memorySizeInKilobytes){
        memory=new Memory(memorySizeInKilobytes);
        simulationSaveFileName="Prueba.json";
        memoryInstantList = new ArrayList<model.Memory>();
        pendingProcessList = new ArrayList<model.Process>();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    public String getDateTime(){
        String formatString = "yyyyMMddHHmmss";
        long dt = Date.parse(formatString);
        return String.valueOf(dt);
    }
    
    public void addProcess(int memoryUsageInKylobytes, String name, int arryvalInstant, int durationInInstants){
        model.Process newProcess= new model.Process(memoryUsageInKylobytes, name, arryvalInstant, durationInInstants);
        this.pendingProcessList.add(newProcess);
    }
    
    public boolean simulate(){
        Memory  iterator= new Memory(0);
        checkSpaceForPendingProcess();
        memory.fordwardInstant();
        checkSpaceForPendingProcess();
        iterator=memory;
        memoryInstantList.add(iterator);
        if(memory.isEmpty()){
            if(!pendingProcessList.isEmpty()){
                return true;
            }else{
                saveInJson();
                return false;
            }
        }else{
            return true;
        }
    }
    
    public void addSO(int soMemorySizeInKilobytes){
        addProcess(soMemorySizeInKilobytes,"SO",0,-1);
    }
    
    public void checkSpaceForPendingProcess(){
        
        Iterator<model.Process> iterator = pendingProcessList.iterator();
        while (iterator.hasNext()) {
            model.Process pendingProcess = iterator.next();
            if (memory.currentInstant >= pendingProcess.arryvalInstant) {
                if(memory.insertProcess(pendingProcess)){
                iterator.remove();
                }
            }
        }
    }
    
    public void saveInJson(){     
        String json = gson.toJson(memoryInstantList);
        try (FileWriter writer = new FileWriter(this.simulationSaveFileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
