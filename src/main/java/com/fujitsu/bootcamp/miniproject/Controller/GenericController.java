package com.fujitsu.bootcamp.miniproject.Controller;

import javax.servlet.http.HttpSession;

import com.fujitsu.bootcamp.miniproject.User.UserModel;
import com.fujitsu.bootcamp.miniproject.User.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenericController {

    @Autowired
    private final UserService userService;

    

    public GenericController(UserService userService) {
        this.userService = userService;
        
    }

    @GetMapping(value="/aboutus")
    public String aboutUs(Model model){

        return "aboutus";
    }


    @GetMapping(value = "/")
    public String loadLogin(Model model){
        model.addAttribute("user", new UserModel());
        
        return "login";
    }

    @RequestMapping(path="/login")
    public String login(Model model, HttpSession session,@ModelAttribute(name="user")UserModel user,final RedirectAttributes redirectAttributes){
        
        UserModel newUser=userService.getUser(user.getUsername(), user.getPassword());
        
       if(newUser.getId()!=0){
        redirectAttributes.addFlashAttribute("user",newUser);
      
        return "redirect:/generateexam";


       }System.out.println("login failed");  

        model.addAttribute("user",new UserModel());
        model.addAttribute("invalidCredentials", true);

        return "login";
    }
    
}
