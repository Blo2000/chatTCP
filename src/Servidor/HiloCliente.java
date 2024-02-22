package Servidor;

import java.io.*;
import java.net.Socket;

public class HiloCliente extends Thread{
    private Socket cliente;

    public HiloCliente(Socket cliente) {
        this.cliente = cliente;

    }

    @Override
    public void run() {
        try {
            InputStream in = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(out);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
