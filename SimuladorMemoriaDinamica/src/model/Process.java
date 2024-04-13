/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cana0
 */
public class Process{
    int sizeInKylobytes;
    int id;
    String name;
    int initInstant;
    public int arryvalInstant;
    int durationInInstants;

    public Process(int id,int memoryUsageInKylobytes, String name, int arryvalInstant, int durationInInstants){
        this.id = id;
        this.sizeInKylobytes=memoryUsageInKylobytes;
        this.name = name;
        this.arryvalInstant = arryvalInstant;
        this.durationInInstants = durationInInstants;
        this.initInstant=0;
    }          
}