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

    public int sizeInKilobytes;
    public String name;
    public int initInstant;
    public int arryvalInstant;
    int durationInInstants;

    public Process(int sizeInKylobytes, String name, int arryvalInstant, int durationInInstants) {
        this.sizeInKilobytes = sizeInKylobytes;
        this.name = name;
        this.arryvalInstant = arryvalInstant;
        this.durationInInstants = durationInInstants;
        this.initInstant = 0;
    }

    public String toJson() {
        String json = "{\"sizeInKilobytes\":\"" + this.sizeInKilobytes + "\",\"name\":\"" + this.name + "\",\"initInstant\":\"" + this.initInstant + "\",\"arryvalInstant\":\"" + this.arryvalInstant + "\",\"durationInInstants\":\"" + this.durationInInstants + "\"}";
        return json;
    }
}
