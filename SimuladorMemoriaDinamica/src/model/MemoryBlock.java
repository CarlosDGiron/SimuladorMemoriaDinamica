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
        for(Process iterator:storedProcesses){
            if((iterator.durationInInstants+iterator.initInstant)==currentInstant){
                storedProcesses.remove(iterator);
            }
        }
        this.updateinternalFragmentation();
    }
}