package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private MainFrame parent;

    private JButton addStudent;
    private JButton listStudent;
    private JButton exit;

    public MainMenu(MainFrame parent){
        this.parent = parent;
        setSize(500,500);
        setLayout(null);

        addStudent = new JButton("Add Student");
        addStudent.setSize(300,30);
        addStudent.setLocation(100,100);
        add(addStudent);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getAddStudent().setVisible(true);
            }
        });

        listStudent = new JButton("List Students");
        listStudent.setSize(300,30);
        listStudent.setLocation(100,150);
        add(listStudent);
        listStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getListStudents().generateTable(parent.getStudents());
                parent.getMainMenu().setVisible(false);
                parent.getListStudents().setVisible(true);
            }
        });

        exit = new JButton("Exit");
        exit.setSize(300,30);
        exit.setLocation(100,200);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        repaint();
    }

}
