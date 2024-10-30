package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        ArrayList arrUser = new ArrayList<String>();
        Biglietti Biglietti = new Biglietti();
        System.out.println("Server partito");
        while(true){
            Socket socket = serverSocket.accept();
            MioThread s = new MioThread(socket,arrUser,Biglietti);
            s.start();
        }
    }
}