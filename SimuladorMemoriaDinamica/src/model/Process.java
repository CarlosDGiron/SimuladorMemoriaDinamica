/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cana0
 */
public class Process {

    public int sizeInKylobytes;
    String name;
    public int initInstant;
    public int arryvalInstant;
    int durationInInstants;

    public Process(int memoryUsageInKylobytes, String name, int arryvalInstant, int durationInInstants) {
        this.sizeInKylobytes = memoryUsageInKylobytes;
        this.name = name;
        this.arryvalInstant = arryvalInstant;
        this.durationInInstants = durationInInstants;
        this.initInstant = 0;
    }

    public String toJson() {
        String json = "{\"sizeInKilobytes\":\"" + this.sizeInKylobytes + "\",\"name\":\"" + this.name + "\",\"initInstant\":\"" + this.initInstant + "\",\"arryvalInstant\":\"" + this.arryvalInstant + "\",\"durationInInstants\":\"" + this.durationInInstants + "\"}";
        return json;
    }
}
