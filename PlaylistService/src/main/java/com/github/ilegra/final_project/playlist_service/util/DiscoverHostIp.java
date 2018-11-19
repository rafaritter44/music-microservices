package com.github.ilegra.final_project.playlist_service.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;



public class DiscoverHostIp {
   public static String getMachineIp() {
       NetworkInterface networkInterface;
       try {
           networkInterface = NetworkInterface.getByName("eno1");
       } catch(Exception exception) {
           exception.printStackTrace();
           return "localhost";
       }
       Enumeration<InetAddress> inetAdresses = networkInterface.getInetAddresses();
       String hostAdress = "";
       while (inetAdresses.hasMoreElements()) {
           String inetAdress = inetAdresses.nextElement().getHostAddress();
           if(inetAdress.length() <=15)
               hostAdress = inetAdress;
           }
       return hostAdress;
   }
}