package server;

import exceptions.PortNumberOutOfBounds;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.ServerSocket;

public class DicServer {
    private static int port = 12345; // default value
    private static String address =null;
    public static void main (String args[]){
        ServerSocket listening = null;
        validatePortNumber(args);
        validateAddress(args);
    }
    private static void validatePortNumber(String[] args){
        try{
            port = Integer.parseInt(args[0]);
            if(port > 65535 || port <=1042){
                System.out.println("Port Number: " + port);
                throw new PortNumberOutOfBounds();
            }
            if(args.length !=2)throw new ArrayIndexOutOfBoundsException();
        } catch (PortNumberOutOfBounds e){
            System.out.println("Please enter the port number between 1024 and 65535");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("Please enter only number, not something else.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Are you sure that the port number is correctly entered?");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void validateAddress(String[] args){
        try{
            address = args[1];
            File dicFile = new File(address);
            if(!dicFile.exists()) throw new FileNotFoundException();
            if(args.length!=2) throw new ArrayIndexOutOfBoundsException();
        } catch (FileNotFoundException e){
            System.out.println("File not found in the path. Check the path first.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please make sure feed the command line with two arguments.");
            e.printStackTrace();
        }
    }

    private static void defaultHostHandle(){

    }
}


