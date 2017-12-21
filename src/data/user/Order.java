package data.user;

import java.sql.Date;

public class Order {
    private String originator;
    private String order_car;
    private Date ddl;
    private int money;
    private boolean isAccepted;
    private String carPic;

    public String getCarPic() {
        return carPic;
    }

    public void setCarPic(String carPic) {
        this.carPic = carPic;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }


    public String getOrder_car() {
        return order_car;
    }

    public void setOrder_car(String order_car) {
        this.order_car = order_car;
    }

    public Date getDdl() {
        return ddl;
    }

    public Order() {
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String renderAccepted() {
        if (isAccepted) return "已被接受";
        else return "待处理";
    }
}
