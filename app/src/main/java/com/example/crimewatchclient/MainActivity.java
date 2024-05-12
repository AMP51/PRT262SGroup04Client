package com.example.crimewatchclient;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Thread thread1 = null;
    Thread thread2= null;
    Socket socket;
    String serverIP;
    int serverPort;
    private PrintWriter out;
    private BufferedReader in;

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
            socket = new Socket(serverIP, serverPort);
            getStreams();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getStreams() //Creates the input and output streams
    {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
