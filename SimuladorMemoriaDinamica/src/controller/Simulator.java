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
import model.*;

/**
 *
 * @author cana0
 */
public class Simulator {
    Memory memory;
    String simulationSaveFileName;
    ArrayList<Memory> memoryInstantList;
    ArrayList<model.Process> pendingProcessList;
    Gson gson;
    
    public Simulator(int memorySizeInKilobytes){
        memory=new Memory(memorySizeInKilobytes);
        simulationSaveFileName=getDateTime()+".json";
        memoryInstantList = new ArrayList<model.Memory>();
        pendingProcessList = new ArrayList<model.Process>();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public String getDateTime(){
        String formatString = "yyyyMMddHHmmss";
        long dt = Date.parse(formatString);
        return String.valueOf(dt);
    }
    public Simulator(){
        pendingProcessList=new ArrayList<model.Process>();
    }
    
    public void addProcess(int id, int memoryUsageInKylobytes, String name, int arryvalInstant, int durationInInstants){
        model.Process newProcess= new model.Process(id, memoryUsageInKylobytes, name, arryvalInstant, durationInInstants);
        this.pendingProcessList.add(newProcess);
    }
    
    public void simulate(){
        Memory  iterator;
        this.checkSpaceForPendingProcess();
        memory.fordwardInstant();
        iterator=memory;
        memoryInstantList.add(iterator);
        if(memory.isEmpty()){
            if(!pendingProcessList.isEmpty()){
                simulate();
            }else{
                saveInJson();
            }
        }else{
            simulate();
        }
    }
    
    public void addSO(int soMemorySizeInKilobytes){
        addProcess(0,soMemorySizeInKilobytes,"SO",0,-1);
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
        String json = gson.toJson(memoryInstantList);
        try (FileWriter writer = new FileWriter(this.simulationSaveFileName)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
