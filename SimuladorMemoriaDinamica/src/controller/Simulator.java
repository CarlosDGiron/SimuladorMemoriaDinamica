/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    String jsonData;
    
    public Simulator(int memorySizeInKilobytes){
        memory=new Memory(memorySizeInKilobytes);
        simulationSaveFileName="Prueba1.json";
        memoryInstantList = new ArrayList<model.Memory>();
        pendingProcessList = new ArrayList<model.Process>();
        jsonData="[";
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
        memory.fordwardInstant();
        checkSpaceForPendingProcess();
        if(memory.isEmpty()){
            if(!pendingProcessList.isEmpty()){
                jsonData=jsonData+","+memory.toJson();
                return true;
            }else{
                jsonData=jsonData+","+memory.toJson()+"]";
                saveInJson();
                return false;
            }
        }else{
            jsonData=","+jsonData+","+memory.toJson();
            return true;
        }
    }
    
    public void addSO(int soMemorySizeInKilobytes){
        model.Process so=new model.Process(soMemorySizeInKilobytes,"SO",0,-1);
        memory.addSO(so);
        jsonData=jsonData+memory.toJson();
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
        try (FileWriter writer = new FileWriter(this.simulationSaveFileName)) {
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadFromJson(String jsonData){
        this.memoryInstantList=new ArrayList();
        Memory iterator;
        JsonParser parser=new JsonParser();
        JsonArray memoriesJson = parser.parse(jsonData).getAsJsonArray();
        for (JsonElement obj : memoriesJson) {            
            JsonObject gsonObj = obj.getAsJsonObject();
            iterator=new Memory(gsonObj.get("sizeInKilobytes").getAsInt());
            iterator.currentInstant=gsonObj.get("currentInstant").getAsInt();
            iterator.sizeInKilobytes=gsonObj.get("sizeInKilobytes").getAsInt();
            iterator.totalInternalFragmentationInKilobytes=gsonObj.get("totalInternalFragmentationInKilobytes").getAsInt();
            iterator.loadFromJson(gsonObj.get("memoryBlocks").getAsJsonArray());
            this.memoryInstantList.add(iterator);
        }
        for (Memory iteratorMemoryList : memoryInstantList) {
            System.out.println(iteratorMemoryList.toJson());
        }
        
    }
}
