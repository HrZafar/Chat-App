package chatserver;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ChatServer {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner in=new Scanner(System.in);
        String name="",pass="";
        while(true){
            if(name.compareToIgnoreCase("Server")!=0){
                System.out.print("Enter Username or write quit to exit: ");
                name=in.nextLine();
                if(name.compareToIgnoreCase("quit")==0){
                    break;
                }
                if(name.compareToIgnoreCase("Server")!=0){
                    System.out.println("Username not recognized!");
                    continue;
                }
            }
            if(pass.compareToIgnoreCase("abcd123")!=0){
                System.out.print("Enter password: ");
                pass=in.nextLine();
                if(pass.compareToIgnoreCase("quit")==0){
                    break;
                }
                if(pass.compareToIgnoreCase("abcd123")!=0){
                    System.out.println("Incorrect password! ");
                    continue;
                }
                else
                    break;
            }
        }
        if(pass.compareToIgnoreCase("quit")==0||name.compareToIgnoreCase("quit")==0){
            System.out.println("\t\t\tApp exit");
        }   
        else{
            InetAddress LocalAddress=InetAddress.getLocalHost();
            InetSocketAddress serverSockAddress=new InetSocketAddress(LocalAddress,4444);
            int concurrent_client=1;
        
            ServerSocket serverSocket=new ServerSocket();
            serverSocket.bind(serverSockAddress,concurrent_client);
            System.out.println("\t\t\tApp ready.");
            System.out.println("waiting for request at "+serverSocket.getLocalSocketAddress());
        
            Socket clientSocket=null;
            clientSocket=serverSocket.accept();
            System.out.println("Request Received From Client : "+clientSocket.getRemoteSocketAddress());
        
            DataInputStream input;
            input=new DataInputStream(clientSocket.getInputStream());
        
            DataOutputStream output;
            output=new DataOutputStream(clientSocket.getOutputStream());
            System.out.println();
            for(;;){
                String send,receive;
                receive=input.readUTF();
                System.out.println("client : "+receive);
                if(receive.compareToIgnoreCase("bye")==0){
                    break;
                }
                System.out.print("\t\tYou : ");
                send=in.nextLine();
                output.writeUTF(send);
                if(send.compareToIgnoreCase("bye")==0){
                    break;
                }
            }
  
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
}    