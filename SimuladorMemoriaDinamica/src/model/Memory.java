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
public class Memory {
    ArrayList<MemoryBlock> memoryBlocks;
    public int currentInstant;
    int freeSpaceInKilobytes;
    int sizeInKilobytes;
    int totalInternalFragmentationInKilobytes;    
   
    public Memory(int sizeInKilobytes){
        this.currentInstant=0;
        this.sizeInKilobytes=sizeInKilobytes;
        this.freeSpaceInKilobytes=sizeInKilobytes;
        this.totalInternalFragmentationInKilobytes=0;
        memoryBlocks=new ArrayList<MemoryBlock>();
    }
    
    public void fordwardInstant(){
        currentInstant++;
        this.totalInternalFragmentationInKilobytes=0;
        for(MemoryBlock iterator:memoryBlocks){
            iterator.fordwardInstant(currentInstant);
            totalInternalFragmentationInKilobytes=+iterator.internalFragmentationInKilobytes;
        }
    }
    
    public Boolean insertProcess(Process newProcess){
        Boolean newProcessIsStored=false;
        if(newProcess.sizeInKylobytes<=freeSpaceInKilobytes){
            addMemoryBlock(newProcess);
            newProcessIsStored=true;
        }else{
            if(newProcess.sizeInKylobytes<=totalInternalFragmentationInKilobytes){
                for(MemoryBlock iterator:memoryBlocks){
                    if(newProcess.sizeInKylobytes<=iterator.internalFragmentationInKilobytes){
                        insertProcessInMemoryBlock(newProcess,memoryBlocks.indexOf(iterator));
                        newProcessIsStored=true;
                        break;
                    }
                }
            }
        }
        return newProcessIsStored;
    }
    
    public void addMemoryBlock(Process newProcess){
        MemoryBlock newMemoryBlock=new MemoryBlock(memoryBlocks.size(),newProcess.sizeInKylobytes);
        newMemoryBlock.insertProcess(newProcess);
        memoryBlocks.add(newMemoryBlock);
        freeSpaceInKilobytes=-newProcess.sizeInKylobytes;
    }
    
    public void insertProcessInMemoryBlock(Process newProcess, int memoryBlockId){
        newProcess.initInstant=currentInstant;
        memoryBlocks.get(memoryBlockId).insertProcess(newProcess);
    }
   
}
