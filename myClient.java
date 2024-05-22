
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mysql.cj.result.Field;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class myClient 
{

   public static String fetchIndex()
   {
       String url = "http://localhost:9000/";
       try 
       {
    HttpResponse<String> res = Unirest.get("http://localhost:9000/one").asString();
           
       if(res.getStatus() == 200)
       {
           String ans = res.getBody();
           return ans;
       }
       } 
       catch (Exception e) 
       {
       e.printStackTrace();
       return e.toString();
       }
       
      return "error";
   }
   
    public static String userlogin(String useremail, String password)
   {
     
       try 
       {
           String url = "http://localhost:9000/userlogin";
   
           HttpResponse<String> res = Unirest.get(url)
                   .queryString("useremail", useremail)
                   .queryString("password", password)
                   .asString();
           
       if(res.getStatus() == 200)
       {
           String ans = res.getBody();
           System.out.println("-->"+ans);
           return ans;
       }
       } 
       catch (Exception e) 
       {
       e.printStackTrace();
       return e.toString();
       }
       
      return "error";
   }
      public static String usersignup(String email,String pass,String mobile,String address, File ph)
    {
        try {
              String url = "http://localhost:9000/usersignup";
               HttpResponse<String> res = Unirest.post(url)
                       .queryString("email", email)
                       .queryString("pass", pass)
                       .queryString("mobile", mobile)
                       .queryString("address", address)
                       .field("f1", ph)
                       .asString();
               
               if(res.getStatus()==200)
               {
                   return res.getBody();
               }
                       
        } 
        catch (Exception e) {
            return e.toString();
        }
       return "error";
   }
 public static String verifyEmail(String Email)
    {
        String url = "http://localhost:9000/verifyEmail";
        try {
    
      HttpResponse<String> res = Unirest.get(url)
         .queryString("email", Email)
         .asString();
      
      if(res.getStatus()==200) 
      {
          return res.getBody();
      }
      } 
       catch (Exception e) {
          return e.toString();
        }
     return null;
    }
     public static String fetchcat()
     {
          String url = "http://localhost:9000/fetchcat";
          try{
           HttpResponse<String> res = Unirest.get(url).asString();
             if(res.getStatus()==200) 
      {
          return res.getBody();
      }
             else
             {
                 return " server error";
      } 
          }
      catch (Exception e) {
          return e.toString();
        }
     }
     public static String AdminLogin(String name, String pass)
   {
     
       try 
       {
           String url = "http://localhost:9000/AdminLogin";
   
           HttpResponse<String> res = Unirest.get(url)
                   .queryString("name", name)
                   .queryString("pass", pass)
                   .asString();
           
       if(res.getStatus() == 200)
       {
           String ans = res.getBody();
           System.out.println("-->"+ans);
           return ans;
       }
       else
       {
           return "server error";
       }
       }
       catch(Exception e)
       {
           return e.toString();
       }
   }
       public static String addcat(String category, File ph)
    {
          String url = "http://localhost:9000/addcat";
          try {
            
       
          HttpResponse<String> res = Unirest.post(url)
                  .queryString("category", category)
                  .field("f1", ph)
                  .asString();
          
          if(res.getStatus()==200)
          {
              return res.getBody();
          }
          else
          {
              return "Server Error";
          }
           } catch (Exception e)
           {
               return e.toString();
            }
   }
    public static String fetchCategories()
    {
        String url = "http://localhost:9000/fetchCategories";
        try{
        HttpResponse<String>res=Unirest.get(url).asString();
        if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else{
            return "SERVER Error";
        }
    }
        catch (Exception e)
        {
            return e.toString();
        }
}
    public static String addmovie(String category, String movie, String director, String cast, String utube_id,File ph1,File movie1)
    {String url = "http://localhost:9000/addmovie";
        try{
            HttpResponse<String> res=Unirest.post(url)
                    .queryString("category", category)
                    .queryString("movie", movie)
                    .queryString("director", director)
                    .queryString("cast", cast)
                    .queryString("utube_id", utube_id)
                    .field("f2",ph1)
                    .field("movie1", movie1)
                    .asString();
             if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else{
            return "SERVER Error";
        }
        }
        catch(Exception e)
        {
            return e.toString();
        }
    }
       public static String Fetch_movies(String category)
       {
           String url = "http://localhost:9000/Fetch_movies";
       try {
           HttpResponse<String>res=Unirest.get(url)
                   .queryString("category", category)
                   .asString();
            if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else{
            return "SERVER Error";
 } 
       }
            catch (UnirestException e) 
            {
       return e.toString();
             }
 }
        public static String movie_detail(String id)
    {
        String url = "http://localhost:9000/movie_detail";
        try{
            HttpResponse<String>res=Unirest.get(url)
                             .queryString("id", id)
                              .asString();
                
        if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else{
            return "SERVER Error";
        }
    }
        catch (Exception e)
        {
            return e.toString();
        }
}
}

