import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection (Socket aClientSocket) {
        try {
            // inicializa variáveis
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out =new DataOutputStream( clientSocket.getOutputStream());

            this.start(); //executa o método run numa thread separada

        } catch(IOException e) {
            System.out.println("Connection:"+e.getMessage());
        }
    }


    public void run(){
        try {

            ObjectInputStream ois = new ObjectInputStream(in);
            Person ret =  (Person) ois.readObject(); //lê os dados do cliente
            out.writeUTF((String) ret.getName()); //envia a mensagem (resposta) ao cliente

        } catch(EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch(IOException e) {
            System.out.println("IO:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try {
                clientSocket.close();
            }catch (IOException e){
                /*close failed*/
            }
        }
    }
}