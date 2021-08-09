package com.sample.postgress.HttpHandler;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpsClient{
    
//   public static void main(String[] args)
//   {
//        new HttpsClient().callapi("96171362075");
//   }
    
   public HashMap<?,?> callapi(String mobile){
	   
	   HashMap response = new HashMap<>();
	   final String access_key = "cbda89f8ec94ae9ba4a038c125f5abd3";
	   String https_url = "http://apilayer.net/api/validate?access_key=" + access_key + "&number="+ mobile;

	   URL url;
	   
      try {

         url = new URL(https_url);
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
            
         //dumpl all cert info
         //print_https_cert(con);
            
         //dump all the content
         //print_content(con);
         BufferedReader br = null;
         if (con.getResponseCode() == 200) {
             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
             String strCurrentLine;
                 while ((strCurrentLine = br.readLine()) != null) {
                        System.out.println(strCurrentLine);
                        response =  new ObjectMapper().readValue(strCurrentLine, HashMap.class);
                 }
         } else {
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
             String strCurrentLine;
                 while ((strCurrentLine = br.readLine()) != null) {
                        System.out.println(strCurrentLine);
                 }
         }
         
         con.disconnect();
            
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
	return response;

   }
    
   private void print_https_cert(HttpsURLConnection con){
     
    if(con!=null){
            
      try {
                
    System.out.println("Response Code : " + con.getResponseCode());
    System.out.println("Cipher Suite : " + con.getCipherSuite());
    System.out.println("\n");
                
    Certificate[] certs = con.getServerCertificates();
    for(Certificate cert : certs){
       System.out.println("Cert Type : " + cert.getType());
       System.out.println("Cert Hash Code : " + cert.hashCode());
       System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
       System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
       System.out.println("\n");
    }
                
    } catch (SSLPeerUnverifiedException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    }

     }
    
   }
    
   private void print_content(HttpsURLConnection con){
    if(con!=null){
            
    try {
        
       System.out.println("****** Content of the URL ********");			
       BufferedReader br = 
        new BufferedReader(
            new InputStreamReader(con.getInputStream()));
                
       String input;
                
       while ((input = br.readLine()) != null){
          System.out.println(input);
       }
       br.close();
                
    } catch (IOException e) {
       e.printStackTrace();
    }
            
       }
        
   }
    
}