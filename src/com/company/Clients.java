package com.company;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Clients {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        try {
            Socket socket = new Socket("127.0.0.1", 1999);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                PackageData packageData1;
                ObjectInputStream inputStream1 = new ObjectInputStream(new FileInputStream("project1.data"));
                packageData1 = (PackageData)inputStream1.readObject();
                outputStream.writeObject(packageData1);
                inputStream1.close();
                outputStream.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            /*while ((packageData = (PackageData)inputStream.readObject()) != null){
                if (packageData.getOperationType().equals("ADD")){
                    Students students = packageData.getStudent();
                    manager.addStudent(students);
                }
                else if (packageData.getOperationType().equals("LIST")){

                }
            }*/
        }catch (Exception e){

        }
    }
}
