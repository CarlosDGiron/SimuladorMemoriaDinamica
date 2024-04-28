/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolaritmetico;

/**
 *
 * @author cana0
 */
public class StackTree {
    private Stack inicio;
    
    public StackTree(){
        inicio=null;
        
    }
    
    public void insert(Node node){
        Stack nuevo=new Stack(node);
        nuevo.next=inicio;
        inicio=nuevo;
    }
    
    public boolean isEmpty(){
        return inicio==null;
    }
    
    public Node getStart(){
        return inicio.node;
    }
    
    public void reset(){
        inicio=null;
    }
    
    public Node getNext(){
        Node aux=null;
        if(!isEmpty()){
            aux=inicio.node;
            inicio=inicio.next;
        }
        return aux;
    }

}
