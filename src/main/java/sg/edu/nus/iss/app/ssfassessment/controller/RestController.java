package sg.edu.nus.iss.app.ssfassessment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.app.ssfassessment.model.Pizza;
import sg.edu.nus.iss.app.ssfassessment.service.PizzaService;

//@RestController
@RequestMapping(path="/order/orderid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {
    @Autowired
    private PizzaService pSvc;
    @GetMapping(path = "{id}")
    public ResponseEntity<String> getBoardGame(@PathVariable String id)
            throws IOException {
        Pizza p = pSvc.findById(id);
        if (p == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(p.toJSON().toString());
    }
}
