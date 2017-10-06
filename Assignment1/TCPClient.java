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
        try {
           FileWriter fout = new FileWriter("filename.txt", true);
            //PrintWriter pw = new PrintWriter(fout);

            String serverIp = args[0];
            int port = Integer.parseInt(args[1]);
            String response, pac;
            String message;
            long [] latencytable ;
            latencytable = new long[300];
            long senttime;
            long receivetime;
            int count=0;

           // CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');


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

                if (response.equals("end")) {
                   // System.out.println("testing");
                    socket.close();
                    System.out.println(Arrays.toString(latencytable));
                    int x=0;
                    long sum =0;
                    while(latencytable[x]!=0 ){
                        sum = sum + latencytable[x];
                        x++;
                    if (x==300)
                        break;
                    }
                    //x--;
                    System.out.println("The Sum Latency is: "+ sum/1000000);
                    System.out.println("The Average Latency is: "+ (sum/x)/1000000);

                    fout.write(String.valueOf((sum/x)/1000000)+"\n");
                      fout.close();
                    //fout.write((int)sum/1000000);
            //        sumofsum+= (sum/x)/1000000;
              //     System.out.println(sumofsum);
                    break;
                }
                System.out.println(response);
                latencytable[count]=receivetime-senttime;
                count++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
