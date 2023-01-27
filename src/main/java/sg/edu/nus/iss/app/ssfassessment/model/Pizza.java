package sg.edu.nus.iss.app.ssfassessment.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza implements Serializable {
    // private static final long serialVersionUID = 1L;
    @NotNull(message = "Please enter 1 type of Pizza")
    private String pizzaType;

    @NotNull(message = "Please specify size for the selected Pizza")
    private String pizzaSize;

    @NotNull(message = "Please specify quantity for the selected Pizza")
    @Min(value = 1, message = "Minimum order quantity of 1")
    @Max(value = 10, message = "Maximum order quantity of 10")
    private Integer pizzaQty;

    private Integer rush = 0;

    private Double totalCost = 0.0d;

    private String id;

    @NotNull(message = "Name is mandatory.")
    @Size(min = 3, message = "Name must be at least 3 characters.")
    private String name;

    @NotNull(message = "Address is mandatory.")
    private String address;

    @Size(min = 8, message = "Phone number must be at least 8 digits.")
    private String phone;

    private String comments;

    public Pizza(String pizza, String size, Integer quantity) {
        this.id = this.generateId(8);
        this.pizzaType = pizza;
        this.pizzaSize = size;
        this.pizzaQty = quantity;
    }

    public Pizza() {
        this.id = this.generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numChars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numChars);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRush() {
        return rush;
    }

    public void setRush(Integer rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public Integer getPizzaQty() {
        return pizzaQty;
    }

    public void setPizzaQty(Integer pizzaQty) {
        this.pizzaQty = pizzaQty;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("orderId", this.getId())
                .add("name", this.getName())
                .add("address", this.getAddress())
                .add("phone", this.getPhone())
                .add("rush", this.getRush())
                .add("comments", this.getComments())
                .add("pizza", this.getPizzaType())
                .add("size", this.getPizzaSize())
                .add("quantity", this.getPizzaQty())
                .build();

    }

    public static Pizza create(String json) throws IOException {
        Pizza p = new Pizza();
        if(json != null)
            try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
                JsonReader r = Json.createReader(is);
                JsonObject o = r.readObject();
                p.setName(o.getString("name"));
                p.setAddress(o.getString("address"));
                p.setName(o.getString("phone"));
                p.setName(o.getString("rush"));
                p.setName(o.getString("comments"));
                p.setName(o.getString("pizza"));
                p.setName(o.getString("size"));
                p.setName(o.getString("quantity"));

            }
        return p;
    }
}
