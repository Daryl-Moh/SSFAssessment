package sg.edu.nus.iss.app.ssfassessment.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.ssfassessment.model.Pizza;

@Service
public class PizzaService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    // Not sure why it is not setting into the database

    public void saveToDb (final Pizza pza) {
        System.out.println("pza " + pza.toJSON().toString());
        redisTemplate.opsForValue().set(pza.getId(), pza.toJSON().toString());
       
    }

    public Double cost (String pType, String pSize, Integer pQty) {
        Double totalPrice=0.0d;
        Integer pizzaPrice=0;
        Double multipler=0.0d;

        switch(pType) {
            case "bella":
                pizzaPrice=30;
            case "margherita":
                pizzaPrice=22;
            case "marinara":
                pizzaPrice=30;
            case "spianatacalabrese":
                pizzaPrice=30;
            case "trioformaggio":
                pizzaPrice=25;
        }

        switch(pSize) {
            case "sm":
                multipler=1.0d;
            case "md":
                multipler=1.2d;
            case "lg":
                multipler=1.5d;
        }

        totalPrice = (pizzaPrice*multipler*pQty);

        System.out.println("pizza type: " + pType);
        System.out.println("pizza size: " + pSize);
        System.out.println("pizza qty: " + pQty);
        System.out.println("pizza total price: " + totalPrice);

        return totalPrice;

    }

    public Pizza findById(final String id) throws IOException {
        String pStr = (String) redisTemplate.opsForValue().get(id);
        Pizza p = Pizza.create(pStr);
        return p;
    }
    
}
