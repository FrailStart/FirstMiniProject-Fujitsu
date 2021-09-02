package com.fujitsu.bootcamp.miniproject.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {

    public boolean insertNewUser(UserModel user) {

        String filepath = "C:\\Users\\f.balili\\OneDrive - FUJITSU\\Desktop\\Java\\Mini Project\\miniproject (5)\\miniproject5\\miniproject5\\miniproject\\src\\main\\resources\\users.csv";
        boolean found = false;
        String userList=new String();
        String[] currentUser=null;
 
        try{
            BufferedReader br=new BufferedReader(new FileReader(filepath));
            while((userList=br.readLine())!=null){
                currentUser=userList.split(",");
                if(currentUser[0].equals(String.valueOf(user.getId())) || currentUser[2].equals(user.getUsername())){
                    found = true;
                }
    }
            if(!found){
                try
                (FileWriter f = new FileWriter("C:\\Users\\f.balili\\OneDrive - FUJITSU\\Desktop\\Java\\Mini Project\\miniproject (5)\\miniproject5\\miniproject5\\miniproject\\src\\main\\resources\\users.csv", true); 
                BufferedWriter bWriter = new BufferedWriter(f); 
                
                PrintWriter pWriter = new PrintWriter(bWriter);){
                    
                    pWriter.append(String.valueOf(user.getId()));
                    pWriter.append(",");
                    pWriter.append(user.getName());
                    pWriter.append(",");
                    pWriter.append(user.getUsername());
                    pWriter.append(",");
                    pWriter.append(user.getPassword());
                    pWriter.append("\n");
                    
                    pWriter.close();
                }   catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            br.close();
            // catch (FileNotFoundException e) {
            //     // TODO Auto-generated catch block
            //     System.out.println("File not Found");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Error with reading file");
            }

            return found;

    }

    @GetMapping
    public UserModel getUser(String username, String password) {

        String filepath = "C:\\Users\\f.balili\\OneDrive - FUJITSU\\Desktop\\Java\\Mini Project\\miniproject (5)\\miniproject5\\miniproject5\\miniproject\\src\\main\\resources\\users.csv";

        String userList = new String();
        UserModel user = new UserModel();
        String[] currentUser = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            while ((userList = br.readLine()) != null) {
                currentUser = userList.split(",");
                if (currentUser[2].equals(username) && currentUser[3].equals(password)) {
                    user.setId(Integer.parseInt(currentUser[0]));
                    user.setName(currentUser[1]);
                    user.setUsername(currentUser[2]);
                    user.setPassword(currentUser[3]);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File not Found");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error with reading file");
        }
        return user;
    }

}
