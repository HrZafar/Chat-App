package chatclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class ChatClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner in=new Scanner(System.in);
        String name="",pass="";
        while(true){
            if(name.compareToIgnoreCase("Client")!=0){
                System.out.print("Enter Username or write quit to exit: ");
                name=in.nextLine();
                if(name.compareToIgnoreCase("quit")==0){
                    break;
                }
                if(name.compareToIgnoreCase("Client")!=0){
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
            System.out.println("\t\t\tApp ready.");
            System.out.println("\t\t\tStart chat.");
            InetAddress RemoteIPAddress=InetAddress.getLocalHost();
            InetSocketAddress ServerSockAddress=new InetSocketAddress(RemoteIPAddress,4444);
        
            Socket clientSock=new Socket();
            clientSock.connect(ServerSockAddress);
        
            DataOutputStream output;
            output=new DataOutputStream (clientSock.getOutputStream());
        
            DataInputStream input;
            input=new DataInputStream(clientSock.getInputStream());
            System.out.println();
            for(;;){
                String send,receive;
                System.out.print("\t\tYou : ");
                send=in.nextLine();
                output.writeUTF(send);
                if(send.compareToIgnoreCase("bye")==0){
                    break;
                }
                receive=input.readUTF();
                System.out.println("Server : "+receive);
                if(receive.compareToIgnoreCase("bye")==0){
                    break;
                }
            }
        
            input.close();
            output.close();
            clientSock.close();
        }
    }
}