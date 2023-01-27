package sg.edu.nus.iss.app.ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import sg.edu.nus.iss.app.ssfassessment.model.Pizza;

@Controller
@RequestMapping(path="/pizza/order")
    public class DeliveryController {


        // // Not sure why my page wont show. Keep getting the following error
        // // ERROR 3568 --- [nio-8080-exec-5] o.a.c.c.C.[.[.[/].[dispatcherServlet]    
        // : Servlet.service() for servlet [dispatcherServlet] in context with path  
        // [] threw exception [Request processing failed: org.thymeleaf.exceptions.TemplateInputException: 
        // An error happened during template parsing 
        // (template: "class path resource [templates/delivery.html]")] with root cause

        @PostMapping
            public String orderConfirmation(@Valid Pizza pizza, BindingResult bindResult, Model model) {
                if(bindResult.hasErrors()){
                    return "delivery";
                }
                return "order";
            }
}
