package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread{
    Socket socket;
    ArrayList <String> user;
    Biglietti Biglietti;
    public MioThread(Socket socket, ArrayList <String> user, Biglietti Biglietti){
        this.socket = socket;
        this.user=user;
        this.Biglietti=Biglietti;

    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            boolean presente = false;
            String fraseRic="";
            String username ="";
            String quantita="";
            String tipo="";
            int num=0;

            do {
                presente = false;
                username = in.readLine();
                
                if(user.contains(username)){
                    out.writeBytes("n" + "\n");
                    presente = true;
                }
                else{
                    user.add(username);
                    out.writeBytes("s" +"\n");
                }
                
            } while (presente);

            do {
                
                fraseRic = in.readLine();
                if(fraseRic.equals("QUIT")){
                    user.remove(username);
                    System.out.println("Client disconnesso");
                }
                else if(fraseRic.equals("N")){
                    out.writeBytes("Quantita biglietti gold:"+Biglietti.getBigliettiOro() + "\n");
                    out.writeBytes("Quantita biglietti pit:"+Biglietti.getBigliettiArg() + "\n");
                    out.writeBytes("Quantita biglietti parterre:"+Biglietti.getBigliettiBase() + "\n");
                    out.writeBytes("" + "\n");
                    }
                
                else if(fraseRic.equals("BUY")){
                    tipo = in.readLine();
                    quantita = in.readLine();
                    num =Integer.parseInt(quantita);
                    if(tipo.equals("gold")){
                        int calc = Biglietti.getBigliettiOro() - num;
                        Biglietti.setBigliettiOro(calc);
                        if(Biglietti.getBigliettiOro()<0){
                            out.writeBytes("KO" + "\n");
                            int somm = Biglietti.getBigliettiOro() + num;
                            Biglietti.setBigliettiOro(somm);
                        }
                        else{
                            out.writeBytes("OK" + "\n");
                        }
                    }else if(tipo.equals("pit")){
                        int calc = Biglietti.getBigliettiArg() - num;
                        Biglietti.setBigliettiArg(calc);
                        if(Biglietti.getBigliettiArg()<0){
                            out.writeBytes("KO" + "\n");
                            int somm = Biglietti.getBigliettiArg() + num;
                            Biglietti.setBigliettiArg(somm);
                        }
                        else{
                            out.writeBytes("OK" + "\n");
                        }
                    }else if(tipo.equals("parterre")){
                        int calc = Biglietti.getBigliettiBase() - num;
                        Biglietti.setBigliettiBase(calc);
                        if(Biglietti.getBigliettiBase()<0){
                            out.writeBytes("KO" + "\n");
                            int somm = Biglietti.getBigliettiBase() + num;
                            Biglietti.setBigliettiBase(somm);
                        }
                        else{
                            out.writeBytes("OK" + "\n");
                        }
                    }
                    else{
                        out.writeBytes("!" + "\n");
                    }
                    
                }
                
            } while (!fraseRic.equals("!"));

        }catch(Exception e) {
        }
    }
}
