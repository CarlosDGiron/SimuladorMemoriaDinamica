/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author cana0
 */
public class MemoryBlock {
    int id;
    int sizeInKilobytes;
    int internalFragmentationInKilobytes;
    ArrayList<Process> storedProcesses;

    MemoryBlock(int id, int sizeInKilobytes) {
        this.id = id;
        this.sizeInKilobytes = sizeInKilobytes;
        this.storedProcesses = new ArrayList<Process>();
        this.internalFragmentationInKilobytes=0;
    }
    public void updateinternalFragmentation(){
        int spaceUsed=0;
        for(Process iterator:storedProcesses){
            spaceUsed=+iterator.sizeInKylobytes;
        }
        this.internalFragmentationInKilobytes = (sizeInKilobytes-spaceUsed);
    }
    
    public void insertProcess(Process newProcess){
        storedProcesses.add(newProcess);
    }
    
    public void fordwardInstant(int currentInstant){
        for(Process iterator:storedProcesses){
            if((iterator.durationInInstants+iterator.initInstant)==currentInstant){
                storedProcesses.remove(iterator);
            }
        }
        this.updateinternalFragmentation();
    }
}