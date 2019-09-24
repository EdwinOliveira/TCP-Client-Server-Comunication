import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPCliente {
    public static void main (String args[]) {
        Socket s = null;
        try{
            int serverPort = 7896; //porto do servidor
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());

            /*
            * Invoke Object
            * Write to Server
            * */
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(new Person("Joao", 2010));

            /*
            * Resposta Servidor
            * */
            String data = in.readUTF(); //  Bloqueia à espera da resposta do servidor

            System.out.println("Received: " + data);
        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){
            System.out.println("IO:" + e.getMessage());
        }catch (IOException e){
            System.out.println("IO:"+e.getMessage());
        } finally {
            if(s!=null)
                try {
                    s.close();
                }catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
        }
    }
}
