package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListStudents extends JPanel {
    private MainFrame parent;
    private JButton back;

    private String header[] = {"ID", "Name", "Surname", "Age"};
    private JTable table;
    private JScrollPane scrollPane;

    public ListStudents(MainFrame parent){
        this.parent = parent;
        setSize(500,500);
        setLayout(null);

        back = new JButton("Back");
        back.setSize(100,30);
        back.setLocation(200, 400);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getListStudents().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 200);
        scrollPane.setLocation(100,100);
        add(scrollPane);
    }
    public void generateTable(Students[] students){
        Object data[][] = new Object[students.length][4];

        for (int i = 0; i < students.length; i++){
            data[i][0] = students[i].getId();
            data[i][1] = students[i].getName();
            data[i][2] = students[i].getSurname();
            data[i][3] = students[i].getAge();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        table.setModel(tableModel);
    }
}
