package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Retrieve JSON from HTTP call.
 * @author James Alexander
 *
 */
public class StringJsonURL implements StringURL{

    
    private String apiURL;                  //URL for AlphaVantage API.
    
    
    /**
     * Constructor.
     * @param url   -   URL for HTTP request.
     */
    public StringJsonURL(String url){
        
        apiURL = url;
    }
    
    
    /**
     * Get JSON String from HTTP call.
     */
    @Override
    public String getResponse() {
      
        BufferedReader inputStream = null;
        URL webUrl = null;
        String json = null;
        
        try{                                //Query URL for HTTP response.
            
            webUrl = new URL(apiURL);
            inputStream = new BufferedReader(new InputStreamReader(webUrl.openStream()));
            json = getStringFromResponse(inputStream);
        
        
        } catch (IOException e) {           
            
            e.printStackTrace();
            
            
        }finally{                           //Close HTTP Connections.
            
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
     * Read HTTP response body as a single String object.
     * @param inputStream   - InputStream object to read from.
     * @return String of HTTP response body.
     */
    private String getStringFromResponse(BufferedReader inputStream){
        
        
        StringBuilder builder = new StringBuilder();
        String line;
        
        try {                               //read all lines from HTTP response body.
            while((line = inputStream.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {           //Catch some exceptions (add loging!)
            
            e.printStackTrace();
        }
        
        return builder.toString();          //return as single String object.
    }

}
