

import com.vmm.JHTTPServer;
import java.awt.Image;
import java.io.IOException;
import java.util.Properties;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class my_server extends JHTTPServer
{
    
    
    public my_server(int port) throws IOException 
    {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) 
    {
        Response res = null;
        
   // if(uri.equals("/"))
  //  {
      //  String ans = Math.random()+"";
       
       // res = new Response(HTTP_OK, "text/plain", ans);
        
       
   // }
  //  else if(uri.equals("/one"))
  //  {
       // String ans = Math.random()+"";
       
        //res = new Response(HTTP_OK, "text/plain", ans);
        
        
  //  }
    
     if(uri.equals("/userlogin"))
    {
        
        String useremail = parms.getProperty("useremail");
        String password = parms.getProperty("password");
        System.out.println("----->"+useremail+"---->"+password);
        try {
            
       
        
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+useremail+"' and password = '"+password+"'");
        if(rs.next())
        {
            res = new Response(HTTP_OK, "text/plain", "success");
        }
        else
        {
             res = new Response(HTTP_OK, "text/plain", "fail");
        }
         }
        catch (Exception e) 
         {
           e.printStackTrace();
         }
    }
    
    else if(uri.equals("/usersignup"))
    {
        String email = parms.getProperty("email");
        String password = parms.getProperty("pass");
        String mobile = parms.getProperty("mobile");
        String address = parms.getProperty("address");
        String abspath = "src/uploads/";
        String name = saveFileOnServerWithRandomName(files, parms, "f1", abspath);
        try {
        
        ResultSet rs = DBLoader.executeQuery("select * from users");
      
            ResultSet rs1 = DBLoader.executeQuery("select * from users where mobile='"+mobile+"'");
            if(rs1.next())
            {
                 res = new Response(HTTP_OK, "text/plain", "mobile");
            }
            else
            {
                  rs1.moveToInsertRow();
            rs1.updateString("useremail", email);
            rs1.updateString("password", password);
            rs1.updateString("mobile", mobile);
            rs1.updateString("address", address);
            rs1.updateString("photo", "src/uploads/"+name);
            rs1.insertRow();
            
            res = new Response(HTTP_OK, "text/plain", "success"); 
            }
         
        
      } catch (Exception e)
      {
      e.printStackTrace();
      }
    }
    
    else if(uri.equals("/verifyEmail"))
    {
        String email = parms.getProperty("email");
        
        try 
        {
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+email+"'");
        if(rs.next())
        {
                res = new Response(HTTP_OK, "text/plain", "exist");
        }
        else
        {
             String otp =  (int) (1000+(9999-1000)*Math.random())+" ";
            sendemail obj = new sendemail(email,"Hello"  ,"Your Otp is: "+otp);
         
         
            res = new Response(HTTP_OK, "text/plain", otp);
             
        }
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
        
         
            
        
    }
    else if (uri.equals("/fetchcat"))
    {
        String ans ="";
        ResultSet rs;
            try {
                rs = DBLoader.executeQuery("select * from category");
            
        while(rs.next())
        {
            String name=rs.getString("name");
            String photo=rs.getString("photo");
            String row =name+"$"+photo;
            ans=ans+row+";;";
             res = new Response(HTTP_OK, "text/plain", ans);
        }
            }
            catch(Exception e)
            {
              e.printStackTrace();
    }
    }
    
    else if(uri.equals("/AdminLogin"))
    {
        
        String name = parms.getProperty("name");
        String pass = parms.getProperty("pass");
        System.out.println("name:"+name+" password:"+pass);
  try {
 
        ResultSet rs = DBLoader.executeQuery("select * from admin where username='"+name+"' and password = '"+pass+"'");
        if(rs.next())
        {
            res = new Response(HTTP_OK, "text/plain", "success");
        }
        else
        {
             res = new Response(HTTP_OK, "text/plain", "fail");
        }
  }
        catch (Exception e) 
         {
             e.printStackTrace();
         }
    }
     else if(uri.equals("/addcat"))
    {
        String category  = parms.getProperty("category");
        
        String name = saveFileOnServerWithRandomName(files, parms, "f1", "src/uploads/");
       
        try {
            
        
        ResultSet rs = DBLoader.executeQuery("select * from category where name='"+category+"'");
        if(rs.next())
        {
              res = new Response(HTTP_OK, "text/plain", "exist");
        }
        else
        {
            rs.moveToInsertRow();
            rs.updateString("name", category);
            rs.updateString("photo", "src/uploads/"+name);
            rs.insertRow();
            
              res = new Response(HTTP_OK, "text/plain", "success");
        }
        } 
        catch (Exception e) {
        }
    }
     else if(uri.equals("/fetchCategories"))
     {        String ans =" ";
            try {
                ResultSet rs=DBLoader.executeQuery("select name from category");
                while(rs.next())
                {
                    String name=rs.getString("name");
                    String row =name;
                    ans =ans+row+";;";
                    System.out.println(ans);
                }
                   res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception ex) {
                res = new Response(HTTP_OK, "text/plain", "failed");

            }
         
     }
     else if(uri.equals("/addmovie"))
     {
        
          String category=parms.getProperty("category");
          String movie=parms.getProperty("movie");
          String director=parms.getProperty("director");
          String cast=parms.getProperty("cast");
          String utube_id =parms.getProperty("utube_id");
          String name=saveFileOnServerWithRandomName(files,parms,"f2","src/uploads/");
          String movie_link =saveFileOnServerWithRandomName(files,parms,"movie1","src/uploads/movies/");
              try {
            
        
        ResultSet rs = DBLoader.executeQuery("select * from movies ");
       
            rs.moveToInsertRow();
            rs.updateString("movie_name", movie);
            rs.updateString("director", director);
            rs.updateString("cast", cast);
            rs.updateString("movie_link","src/uploads/movies/"+movie_link);
            rs.updateString("trailer", utube_id);
            rs.updateString("photo","src/uploads/"+ name );
            rs.updateString("category", category);
            
          rs.insertRow();
            
              res = new Response(HTTP_OK, "text/plain", "success");
        }
        
        catch (Exception e) {
                res = new Response(HTTP_OK, "text/plain", e.toString()); 
        }
     }
     else if(uri.equals("/Fetch_movies"))
     {       String ans =" ";
         String category=parms.getProperty("category");
            try {
                ResultSet rs = DBLoader.executeQuery("select * from movies where category ='" +category+"' ");
                while(rs.next())
                {
                    int id=rs.getInt("id");
                  String movie_name=  rs.getString("movie_name");
                  String photo= rs.getString("photo");
                  String row =id+"$"+movie_name+"$"+photo;
                     ans=ans+row+";;";
                     System.out.println(ans);
                      res = new Response(HTTP_OK, "text/plain", ans);
        }
    
            } catch (Exception ex)
            {
               res = new Response(HTTP_OK, "text/plain", ex.toString()); 
            }
                 
     }
     else if(uri.equals("/movie_detail"))
     {      
         String id =parms.getProperty("id");
            try {
                ResultSet rs = DBLoader.executeQuery("select * from movies where id ='" +id+"' ");
                while(rs.next())
                { 
                  
                  String movie_name=  rs.getString("movie_name");
                  String cast= rs.getString("cast");
                   String youtube_id= rs.getString("trailer");
                  String photo= rs.getString("photo");
                  String movie_link=rs.getString("movie_link");
            String ans  =movie_name+"$"+cast+"$"+youtube_id+"$"+photo+"$"+movie_link;
                  
                     
                     System.out.println(ans);
                      res = new Response(HTTP_OK, "text/plain", ans);
                  }
            }
            catch (Exception ex) {
                res = new Response(HTTP_OK, "text/plain", "failed");
     }
     }
     
    return res;
    }

   
}

   

//    public static void main(String[] args) {
//        
//        try 
//        {
//             myServer obj = new myServer(9000);
//             Thread.sleep(1000000000);
//        } 
//        catch (Exception e) 
//        {
//         e.printStackTrace();
//        }
//        
//       
//        
//    }

    


