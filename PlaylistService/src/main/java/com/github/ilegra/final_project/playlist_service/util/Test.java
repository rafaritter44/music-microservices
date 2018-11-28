package com.github.ilegra.final_project.playlist_service.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Test {

    public static void main(String args[]) {
        NetworkInterface networkInterface;
        try {
           Enumeration<NetworkInterface> teste = NetworkInterface.getNetworkInterfaces();
           
           while(teste.hasMoreElements()) {
               System.out.println(teste.nextElement().getName());
           }
            networkInterface = NetworkInterface.getByName("eno1");
        } catch(Exception exception) {
        }
       
    }
    }
