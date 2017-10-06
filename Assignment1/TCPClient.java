package com.tcp.client;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
public class TCPClient {

    public  static long id = 0;

    public static void main(String args[]) {






            Socket socket = new Socket(serverIp, port);
            int i = 0;

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            BufferedReader server = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );


           // int test = server.read();

            while (true) {

                message = "HELLO " + socket.getInetAddress() + " " + socket.getPort() + " " + /*id++*/  System.lineSeparator();

                senttime=System.nanoTime();
                output.writeBytes(message);

                response = server.readLine();
                receivetime=System.nanoTime();
               // latencytable[count]=receivetime-senttime;


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
