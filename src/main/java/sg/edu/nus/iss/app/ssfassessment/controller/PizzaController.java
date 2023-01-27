package sg.edu.nus.iss.app.ssfassessment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.ssfassessment.model.Pizza;
import sg.edu.nus.iss.app.ssfassessment.service.PizzaService;


@Controller
@RequestMapping(path="/pizza")
public class PizzaController {

    // Supposed to do some validation for the landing page inputs 
    // but I cannot seem to modify the thymeleaf portion to do validation
    @Autowired
    PizzaService pizzaService;

    @PostMapping
        public String calculateOrder(@RequestParam(required=true) String pizza,
                            @RequestParam(required=true) String size,
                            @RequestParam(required=true) Integer quantity,
                            Model model) throws IOException {
            Pizza p = new Pizza(pizza, size, quantity); 
            p.setTotalCost(this.pizzaService.cost(pizza, size, quantity));   
            model.addAttribute("order", p);
            pizzaService.saveToDb(p);
            return "delivery";
        }

    


    
}
