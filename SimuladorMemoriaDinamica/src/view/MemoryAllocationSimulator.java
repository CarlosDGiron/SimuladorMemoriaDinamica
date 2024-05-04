/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Simulator;
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
        for (int i = 0; i < (memory.memoryBlocks.size()); i++) {
            MemoryBlock memoryBlock = memory.memoryBlocks.get(i);
            JLabel memoryBlockLabel = new JLabel("Memory Block " + i + " - Size: " + memoryBlock.sizeInKilobytes
                    + "KB - Free Space: " + "KB");
            memoryBlocksPane.add(memoryBlockLabel);

            for (Process process : memoryBlock.storedProcesses) {
                JLabel processLabel = new JLabel("Nombre Proceso: " + process.name + " - Size: "
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
                Simulator sim = new Simulator(72000);
                sim.addSO(2000);
                sim.addProcess(16000, "A", 1, 5);
                sim.addProcess(4000, "B", 2, 2);
                sim.addProcess(12000, "C", 3, 6);
                sim.addProcess(10000, "D", 4, 4);
                sim.addProcess(8000, "E", 5, 5);
                sim.addProcess(14000, "F", 6, 5);
                sim.simulate();
                new MemoryAllocationSimulator();
            }
        });
    }
}
