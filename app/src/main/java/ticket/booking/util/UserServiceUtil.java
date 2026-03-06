package ticket.booking.util;

import java.security.MessageDigest;

public class UserServiceUtil {

    public static String hashPassword(String password){

        try{

            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] bytes=md.digest(password.getBytes());

            StringBuilder sb=new StringBuilder();

            for(byte b:bytes){
                sb.append(Integer.toHexString(0xff & b));
            }

            return sb.toString();

        }catch(Exception e){
            return password;
        }
    }
}