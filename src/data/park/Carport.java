package data.park;

import java.io.Serializable;
import java.util.Date;

public class Carport implements Serializable{
    private int id;
    private String owner;
    private String state;
    private Date date;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Carport() {

    }

    public Carport(int id, String owner, String state, Date date, int price) {

        this.id = id;
        this.owner = owner;
        this.state = state;
        this.date = date;
        this.price = price;
    }
}
