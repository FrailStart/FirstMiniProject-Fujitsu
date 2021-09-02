package com.fujitsu.bootcamp.miniproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registerform")
    public String loadRegsiterForm(Model model){
        
        model.addAttribute("user", new UserModel());
            
            

        return "register";  
    }

    // ,@ModelAttribute("")UserModel userModel
    // , method=RequestMethod.POST
 
   


    @RequestMapping(value="/register")
    public String loadRegister(Model model,@ModelAttribute(name="user")UserModel user){
       Boolean added= userService.insertNewUser(user);
      if(added==false){
       model.addAttribute("user", user);

       return "login";
     }
        
        model.addAttribute("userExist", true);
        return "/register";
        
    }

    


    
    
}
