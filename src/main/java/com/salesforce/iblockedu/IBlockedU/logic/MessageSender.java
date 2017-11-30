package com.salesforce.iblockedu.IBlockedU.logic;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class MessageSender {

    public static void sendMessage(String message, String phoneNumber) {
        try {
            URL url = new URL("http://www.txtlocal.com/sendsmspost.php");
             String query = String.format("uname=griglerk@gmail.com&pword=A123456789a&message=%s&from=IBlockU&selectednums=%s&info=1&test=0", message, phoneNumber);

            //make connection
            URLConnection urlc = url.openConnection();

            //use post mode
            urlc.setDoOutput(true);
            urlc.setAllowUserInteraction(false);

            //send query
            PrintStream ps = new PrintStream(urlc.getOutputStream());
            ps.print(query);
            ps.close();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            while ((l=br.readLine())!=null) {
                System.out.println(l);
            }
            br.close();
         } catch (Exception e) {

        }
    }
}
