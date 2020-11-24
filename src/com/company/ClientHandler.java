package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            PackageData pd;
            while ((pd = (PackageData) inputStream.readObject()) != null){
                if (pd.getOperationType().equals("ADD")){
                    Students students = pd.getStudent();
                    Server.allStudents.add(students);
                }
                else if (pd.getOperationType().equals("LIST")){
                    PackageData response = new PackageData();
                    response.setStudents(Server.allStudents);
                    outputStream.writeObject(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
