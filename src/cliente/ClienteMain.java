package cliente;

import java.io.*;
import java.net.Socket;

public class ClienteMain {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost", 6001);
            InputStream in = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(in);
            OutputStream out = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(out);

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}
