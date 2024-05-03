/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author cana0
 */
public class MemoryBlock {
    int id;
    int sizeInKilobytes;
    int internalFragmentationInKilobytes;
    ArrayList<Process> storedProcesses;

    public MemoryBlock(int id, int sizeInKilobytes) {
        this.id = id;
        this.sizeInKilobytes = sizeInKilobytes;
        this.storedProcesses = new ArrayList<Process>();
        this.internalFragmentationInKilobytes=0;
    }
    public void updateinternalFragmentation(){
        int fragmentation=0;
        for(Process iterator:storedProcesses){
            fragmentation=+iterator.sizeInKylobytes;
        }
        this.internalFragmentationInKilobytes = (sizeInKilobytes-fragmentation);
    }
    
    public void insertProcess(Process newProcess){
        storedProcesses.add(newProcess);
    }
    
    public void fordwardInstant(int currentInstant){
        System.out.println("ID: "+this.id);        
        System.out.println("Tamaño de Bloque: "+this.sizeInKilobytes);
        System.out.println("Fragmentación Interna: "+this.internalFragmentationInKilobytes);
        Iterator<Process> iterator = storedProcesses.iterator();
        while (iterator.hasNext()){
            Process iteratorProcess = iterator.next();
            System.out.println("Process:"+iteratorProcess.name+" Cancel:"+String.valueOf((iteratorProcess.durationInInstants + iteratorProcess.initInstant) == currentInstant));
            if ((iteratorProcess.durationInInstants + iteratorProcess.initInstant) == currentInstant) {
                iterator.remove();
            }
        }
        this.updateinternalFragmentation();
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
}