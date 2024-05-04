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
public class Memory {
    public ArrayList<MemoryBlock> memoryBlocks;
    public int currentInstant;
    public int freeSpaceInKilobytes;
    public int sizeInKilobytes;
    public int totalInternalFragmentationInKilobytes;    
   
    public Memory(int sizeInKilobytes){
        this.currentInstant=0;
        this.sizeInKilobytes=sizeInKilobytes;
        this.freeSpaceInKilobytes=sizeInKilobytes;
        this.totalInternalFragmentationInKilobytes=0;
        memoryBlocks=new ArrayList<MemoryBlock>();
    }

    public void fordwardInstant() {
        currentInstant++;
        System.out.println("Instante: " + this.currentInstant);
        System.out.println("Memoria Libre: " + this.freeSpaceInKilobytes);
        System.out.println("Fragmentacion total:"+this.totalInternalFragmentationInKilobytes);
        this.totalInternalFragmentationInKilobytes = 0;
        for (MemoryBlock iterator : memoryBlocks) {
            iterator.fordwardInstant(currentInstant);
            totalInternalFragmentationInKilobytes +=iterator.internalFragmentationInKilobytes;
        }
        mergeEmptyMemoryBlocks();
    }

    public void mergeEmptyMemoryBlocks() {
        Iterator<MemoryBlock> iterator = memoryBlocks.iterator();
        while (iterator.hasNext()) {
            MemoryBlock currentBlock = iterator.next();
            int currentIndex = memoryBlocks.indexOf(currentBlock);
            if (currentIndex > 0 && memoryBlocks.get(currentIndex - 1).storedProcesses.isEmpty() && currentBlock.storedProcesses.isEmpty()) {
                MemoryBlock previousBlock = memoryBlocks.get(currentIndex - 1);
                previousBlock.sizeInKilobytes += currentBlock.sizeInKilobytes;
                previousBlock.internalFragmentationInKilobytes = previousBlock.sizeInKilobytes;
                iterator.remove();
            }
        }
    }

    public Boolean insertProcess(Process newProcess) {
        Boolean newProcessIsStored = false;
        newProcess.initInstant=currentInstant;
        System.out.println(totalInternalFragmentationInKilobytes-newProcess.sizeInKylobytes);
        if (newProcess.sizeInKylobytes <= totalInternalFragmentationInKilobytes) {
            for (MemoryBlock iterator : memoryBlocks) {
                if (newProcess.sizeInKylobytes <= iterator.internalFragmentationInKilobytes) {
                    insertProcessInMemoryBlock(newProcess, memoryBlocks.indexOf(iterator));
                    newProcessIsStored = true;
                    break;
                }
            }
        }else if (newProcess.sizeInKylobytes <= freeSpaceInKilobytes) {
            addMemoryBlock(newProcess);
            newProcessIsStored = true;
        }
        
        return newProcessIsStored;
    }

    public void addMemoryBlock(Process newProcess) {
        MemoryBlock newMemoryBlock = new MemoryBlock(memoryBlocks.size(), newProcess.sizeInKylobytes);
        newMemoryBlock.insertProcess(newProcess);
        memoryBlocks.add(newMemoryBlock);
        freeSpaceInKilobytes = freeSpaceInKilobytes - newProcess.sizeInKylobytes;
    }

    public void insertProcessInMemoryBlock(Process newProcess, int memoryBlockId) {
        newProcess.initInstant = currentInstant;
        memoryBlocks.get(memoryBlockId).insertProcess(newProcess);
    }
    public boolean isEmpty(){
        boolean isEmpty=true;
        for (MemoryBlock iterator:memoryBlocks){
            if(!iterator.isEmpty()){
                isEmpty=false;
            }
        }
        return isEmpty;
    }
    
    public void addSO(Process so){
        insertProcess(so);
    }
    public String toJson(){
    String json="{\"currentInstant\":\""+this.currentInstant+"\",\"sizeInKilobytes\":\""+this.sizeInKilobytes+"\",\"freeSpaceInKilobytes\":\""+this.freeSpaceInKilobytes+"\",\"totalInternalFragmentationInKilobytes\":\""+this.totalInternalFragmentationInKilobytes+"\",\"memoryBlocks\":[";
    Iterator<MemoryBlock> iterator=memoryBlocks.iterator();
        while(iterator.hasNext()){
            MemoryBlock memoryBlockIterator=iterator.next();
            json=json+memoryBlockIterator.toJson();
            if(iterator.hasNext()){
                json=json+",";
            }
        }
        json=json+"]}";
    
    return json;
    }
}
