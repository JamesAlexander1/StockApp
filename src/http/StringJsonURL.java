package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * 
 * @author james
 *
 */
public class StringJsonURL implements StringURL{

    String apiURL;
    public StringJsonURL(String url){
        apiURL = url;
    }
    /**
     * 
     */
    @Override
    public String getResponse() {
      
        BufferedReader inputStream = null;
        URL webUrl = null;
        String json = null;
        
        try{
            
            webUrl = new URL(apiURL);
            inputStream = new BufferedReader(new InputStreamReader(webUrl.openStream()));
            json = getStringFromResponse(inputStream);
        
        
        } catch (IOException e) {
            
            e.printStackTrace();
        }finally{
            
           if(inputStream != null){
               
               try {
                   inputStream.close();
                
               } catch (IOException e) {
                
                   e.printStackTrace();
               }
           }
        }
        
        return json;
    }
    
    /**
     * 
     * @param inputStream
     * @return
     */
    private String getStringFromResponse(BufferedReader inputStream){
        
        
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while((line = inputStream.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        return builder.toString();
    }

}
