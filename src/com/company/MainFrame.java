package com.company;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private AddStudent addStudent;
    private ListStudents listStudents;
    private Students[] students;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("STUDENTS APPLICATION");
        setSize(500, 500);
        setLayout(null);

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        addStudent = new AddStudent(this);
        addStudent.setVisible(false);
        add(addStudent);

        listStudents = new ListStudents(this);
        listStudents.setVisible(false);
        add(listStudents);

    }
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public AddStudent getAddStudent() {
        return addStudent;
    }

    public ListStudents getListStudents() {
        return listStudents;
    }

    public Students[] getStudents() {
        for (int i = 0; i < Server.getAllStudents().size(); i ++){
            students[i] = Server.getAllStudents().get(i);
        }
        return students;
    }
}
