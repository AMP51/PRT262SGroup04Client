package com.example.crimewatchclient;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Thread thread1 = null;
    Thread thread2= null;
    Socket socket;
    String serverIP="127.0.0.1";
    int serverPort;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
          //  Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //return insets;
        thread1 = new Thread(new thread1());
        thread1.start();

        }


    public void connectServer() //Allows the client side of the application to connect to the server
    {
        try {
            System.out.println("Attempting connection");
            socket = new Socket(serverIP, serverPort);
            getStreams();
            System.out.println("Connected Succesfully");
            closeConnection();
            System.out.println("Closed Connection Succesfully");
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }

    public void getStreams() //Creates the input and output streams
    {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream((socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() //Closes the streams and disconnects the client from the server
        {
try{
            out.flush();
out.close();
in.close();
socket.close();
}
catch (IOException e) {
e.printStackTrace();
}
        }

    class thread1 implements Runnable{
        @Override
        public void run() {
            connectServer();
        }

    }
    }
