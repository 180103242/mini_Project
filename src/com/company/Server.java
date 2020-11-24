package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Server {
    private static Connection connection;
    public static ArrayList<Students> allStudents = new ArrayList<>();

    public static void main(String[] args) {
        connect();
        try {
            ServerSocket serverSocket = new ServerSocket(1999);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
                addStudent(allStudents);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //manager.addStudent(allStudents);
    }

    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useUnicode=true&serverTimezone=UTC","root", "");
            System.out.println("Connected!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addStudent(ArrayList<Students> students){
        /*try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO student(id, name, surname, age) values(NULL,?,?,?)");
            st.setString(1, students.getName());
            st.setString(2, students.getSurname());
            st.setInt(3, students.getAge());
            st.executeUpdate();
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/

        try{
            for (int i = 0; i < students.size(); i++) {
                PreparedStatement st = connection.prepareStatement("INSERT INTO student(id, name, surname, age) values(NULL,?,?,?)");
                st.setString(1, students.get(i).getName());
                st.setString(2, students.get(i).getSurname());
                st.setInt(3, students.get(i).getAge());
                st.executeUpdate();
                st.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Students> getAllStudents(){
        ArrayList<Students> students = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM student");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                students.add(new Students(id, name, surname, age));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
}
