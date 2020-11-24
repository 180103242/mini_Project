package com.company;

import com.mysql.cj.xdevapi.SqlUpdateResult;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddStudent extends JPanel {
    private MainFrame parent;

    private JLabel NameLabel;
    private JLabel SurnameLabel;
    private JLabel AgeLabel;
    private JTextField NameTextField;
    private JTextField SurnameTextField;
    private JTextField AgeTextField;
    private JButton add;
    private JButton back;

    public AddStudent(MainFrame parent){
        this.parent = parent;
        setSize(500,500);
        setLayout(null);

        NameLabel = new JLabel("Name: ");
        NameLabel.setSize(100, 30);
        NameLabel.setLocation(100, 100);
        add(NameLabel);

        NameTextField = new JTextField();
        NameTextField.setSize(155, 30);
        NameTextField.setLocation(210, 100);
        add(NameTextField);

        SurnameLabel = new JLabel("Surname: ");
        SurnameLabel.setSize(100, 30);
        SurnameLabel.setLocation(100, 150);
        add(SurnameLabel);

        SurnameTextField = new JTextField();
        SurnameTextField.setSize(155, 30);
        SurnameTextField.setLocation(210, 150);
        add(SurnameTextField);

        AgeLabel = new JLabel("Age: ");
        AgeLabel.setSize(100, 30);
        AgeLabel.setLocation(100, 200);
        add(AgeLabel);

        AgeTextField = new JTextField();
        AgeTextField.setSize(155, 30);
        AgeTextField.setLocation(210, 200);
        add(AgeTextField);

        add = new JButton("Add");
        add.setSize(100, 30);
        add.setLocation(100, 270);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = NameTextField.getText();
                String surname = SurnameTextField.getText();
                Integer age = Integer.valueOf(AgeTextField.getText());
                if (!name.equals("") && !surname.equals("") && !age.equals("")) {
                    Students students = new Students(null, name, surname, age);
                    PackageData packageData = new PackageData("ADD", students);
                    try {
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("project1.data"));
                        outputStream.writeObject(packageData);
                        outputStream.close();
                        NameTextField.setText("");
                        SurnameTextField.setText("");
                        AgeTextField.setText("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(add);

        back = new JButton("Back");
        back.setSize(100, 30);
        back.setLocation(280, 270);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getAddStudent().setVisible(false);
                parent.getMainMenu().setVisible(true);
            }
        });
    }
}
