/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolaritmetico;

import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author cana0
 */
public class GraphicTree extends JPanel{
     private Tree tree;
     public static final int DIAMETER=30, RADIUS=DIAMETER/2,WIDTH=30;
     
     public void setTree(Tree tree){
         this.tree=tree;
         repaint();
     }
     
     @Override
     public void paint(Graphics g){
         super.paint(g);
         paintTree(g,getWidth()/2,20,tree.root);
     }

    private void paintTree(Graphics g, int xStart, int yStart, Node subTree) {
        if(subTree!=null){
            int EXTRA=tree.countFullNodes(subTree)*WIDTH/2;
            g.drawOval(xStart, yStart, DIAMETER, DIAMETER);
            g.drawString(subTree.value,xStart+12,yStart+18);
            if(subTree.left!=null){
                g.drawLine(xStart,yStart+RADIUS,xStart+RADIUS-EXTRA-WIDTH,yStart+WIDTH);
            }
            if(subTree.rigth!=null){
                g.drawLine(xStart+DIAMETER,yStart+RADIUS,xStart+RADIUS+EXTRA+WIDTH,yStart+WIDTH);
            }
            paintTree(g,xStart-WIDTH-EXTRA,yStart+WIDTH,subTree.left);
            paintTree(g,xStart+WIDTH+EXTRA,yStart+WIDTH,subTree.rigth);
        }
    }
     
}
