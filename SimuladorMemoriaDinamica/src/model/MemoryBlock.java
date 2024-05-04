/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author cana0
 */
public class MemoryBlock {
    int id;
    public int sizeInKilobytes;
    int internalFragmentationInKilobytes;
    public ArrayList<model.Process> storedProcesses;

    public MemoryBlock(int id, int sizeInKilobytes) {
        this.id = id;
        this.sizeInKilobytes = sizeInKilobytes;
        this.storedProcesses = new ArrayList<model.Process>();
        this.internalFragmentationInKilobytes=0;
    }
    public void updateinternalFragmentation(){
        int fragmentation=0;
        for(Process iterator:storedProcesses){
            fragmentation=+iterator.sizeInKilobytes;
        }
        this.internalFragmentationInKilobytes = (sizeInKilobytes-fragmentation);
    }
    
    public void insertProcess(Process newProcess){
        storedProcesses.add(newProcess);
        updateinternalFragmentation();
    }
    
    public void fordwardInstant(int currentInstant){
        System.out.println("\tID: "+this.id);        
        System.out.println("\tTamano de Bloque: "+this.sizeInKilobytes);
        System.out.println("\tFragmentacion Interna: "+this.internalFragmentationInKilobytes);
        Iterator<Process> iterator = storedProcesses.iterator();
        while (iterator.hasNext()){
            Process iteratorProcess = iterator.next();
            System.out.println("\t\tProcess:"+iteratorProcess.name+" Cancel:"+String.valueOf((iteratorProcess.durationInInstants + iteratorProcess.initInstant) == currentInstant));
            if ((iteratorProcess.durationInInstants + iteratorProcess.initInstant) == currentInstant) {
                iterator.remove();
            }
        }
        this.updateinternalFragmentation();
        
        System.out.println();
    }
    
    
    public boolean isEmpty(){
        boolean isEmpty=true;
        for(Process iterator:storedProcesses){
            if(!iterator.name.equals("SO")){
                isEmpty=false;
            }
        }
        return isEmpty;
    }
    
    public String toJson(){
        String json="{\"id\":\""+this.id+"\",\"sizeInKilobytes\":\""+this.sizeInKilobytes+"\",\"internalFragmentationInKilobytes\":\""+this.internalFragmentationInKilobytes+"\",\"storedProcesses\":[";
        Iterator<Process> iterator=storedProcesses.iterator();
        while(iterator.hasNext()){
            Process processIterator=iterator.next();
            if(processIterator!=null){
                json=json+processIterator.toJson();
            }
            if(iterator.hasNext()){
                json=json+",";
            }
        }
        json=json+"]}";
        return json;
    }
    
    public void loadFromJson(JsonArray storedProcessesJson){
        model.Process iterator=null;
        for (JsonElement obj : storedProcessesJson) {
            JsonObject gsonObj = obj.getAsJsonObject();
            if(gsonObj.get("sizeInKilobytes")!=null && gsonObj.get("name")!=null && gsonObj.get("arryvalInstant")!=null && gsonObj.get("durationInInstants")!=null && gsonObj.get("initInstant")!=null){
                iterator=new model.Process(gsonObj.get("sizeInKilobytes").getAsInt(),gsonObj.get("name").getAsString(),gsonObj.get("arryvalInstant").getAsInt(),gsonObj.get("durationInInstants").getAsInt());
                iterator.initInstant=gsonObj.get("initInstant").getAsInt();
            }
            this.storedProcesses.add(iterator);
            
        }
    }
}