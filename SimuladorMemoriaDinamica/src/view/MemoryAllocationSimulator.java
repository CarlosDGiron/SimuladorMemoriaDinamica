/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Memory;
import model.MemoryBlock;
import model.Process;

public class MemoryAllocationSimulator extends JFrame {

    private Memory memory;

    public MemoryAllocationSimulator() {
        memory = new Memory(1024); // Ejemplo: memoria de 1024 KB

        JPanel memoryBlocksPane = new JPanel();
        memoryBlocksPane.setLayout(new BoxLayout(memoryBlocksPane, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(memoryBlocksPane);

        JButton nextInstantButton = new JButton("Next Instant");
        nextInstantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memory.fordwardInstant();
                updateMemoryBlocksPane(memoryBlocksPane);
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(nextInstantButton);

        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Memory Allocation Simulator");
        setSize(600, 400);
        setVisible(true);
    }

    private void updateMemoryBlocksPane(JPanel memoryBlocksPane) {
        memoryBlocksPane.removeAll();
        for(int i=0; i < (memory.memoryBlocks.size()) ; i++) {
            MemoryBlock memoryBlock = memory.memoryBlocks.get(i);
            JLabel memoryBlockLabel = new JLabel("Memory Block " + i + " - Size: " + memoryBlock.sizeInKilobytes
                    + "KB - Free Space: " + "KB");
            memoryBlocksPane.add(memoryBlockLabel);

            for (Process process : memoryBlock.storedProcesses) {
                JLabel processLabel = new JLabel("Process ID: " + process.id+ " - Size: "
                        + process.sizeInKylobytes + "KB - Init Instant: " + process.initInstant);
                memoryBlocksPane.add(processLabel);
            }
        }

        // Show overall memory status
        JLabel memoryStatusLabel = new JLabel("Total Memory Size: " + memory.sizeInKilobytes + "KB - "
                + "Free Space: " + memory.freeSpaceInKilobytes + "KB - Internal Fragmentation: "
                + memory.totalInternalFragmentationInKilobytes + "KB");
        memoryBlocksPane.add(memoryStatusLabel);

        memoryBlocksPane.revalidate();
        memoryBlocksPane.repaint();
    
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MemoryAllocationSimulator();
            }
        });
    }
}