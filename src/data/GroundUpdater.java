package data;

import connecter.SqlConnect;
import data.park.Car;
import data.park.Carport;
import data.info.Info;
import data.user.News;
import data.user.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroundUpdater {
    private SqlConnect sqlConnect;
    private String email;

    public GroundUpdater(String email) {
        sqlConnect = new SqlConnect();
        this.email = email;
    }

    public ArrayList<Carport> updateCarports() {
        ResultSet resultSet;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("SELECT carport_id, carport_owner, carport_state, carport_leftnum, carport_price\n" +
                "FROM Carports");
        ArrayList<Carport> carports = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Carport carport = new Carport();
                carport.setId(resultSet.getInt(1));
                carport.setOwner(resultSet.getString(2));
                carport.setState(resultSet.getString(3));
                carport.setLeftnum(resultSet.getInt(4));
                carport.setPrice(resultSet.getInt(5));
                carports.add(carport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return carports;
    }

    public ArrayList<Car> updateRankCars() {
        ResultSet resultSet = null;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("select " +
                "car_name, car_picture, ifnull(user_nickname, user_email), user_email,car_id  " +
                "from Cars, Users WHERE Cars.car_owner = Users.user_email");
        ArrayList<Car> cars = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setEmail(resultSet.getString(4));
                car.setName(resultSet.getString(1));
                car.setPicture(resultSet.getString(2));
                car.setOwner(resultSet.getString(3));
                car.setId(resultSet.getInt(5));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return cars;
    }

    public ArrayList<Car> updateCars() {
         ResultSet resultSet = null;
         sqlConnect.startDB();
         resultSet = sqlConnect.executeQuery("select " +
                 "car_name, car_picture, car_id " +
                 "from Cars WHERE Cars.car_owner = '" + email + "'");
         ArrayList<Car> cars = new ArrayList<>();
         try {
             while (resultSet.next()) {
                 Car car = new Car();
                 car.setName(resultSet.getString(1));
                 car.setPicture(resultSet.getString(2));
                 car.setId(resultSet.getInt(3));
                 cars.add(car);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         sqlConnect.endStmt();
         sqlConnect.endDB();
         return cars;
    }

    public ArrayList<News> updateNews() {
        ResultSet resultSet = null;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("SELECT news_content, ifnull(user_nickname, user_email), news_time, news_target FROM News JOIN Users WHERE news_owner = user_email AND (find_in_set('"+ email +"', news_target) OR find_in_set('everyone', news_target)) ORDER BY news_time DESC LIMIT 10");
        ArrayList<News> newsArrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
               News news = new News();
               news.setContent(resultSet.getString(1));
               news.setOwner(resultSet.getString(2));
               news.setTime(resultSet.getTime(3));
               news.setDate(resultSet.getDate(3));
               news.setTarget(resultSet.getString(4));
               newsArrayList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return newsArrayList;
    }

    public Info updateInfo() {
        sqlConnect.startDB();
        Info info = sqlConnect.getInfo();
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return info;
    }

    public ArrayList<Order> updateMyOrder() {
        ResultSet resultSet = null;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("SELECT order_to, order_ddl, order_money, order_accepted, car_picture, car_id\n" +
                "FROM Orders, Cars\n" +
                "WHERE order_by = '"+ email +"'\n" +
                "AND car_id = order_car ORDER BY order_accepted");
        ArrayList<Order> arrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setTo(resultSet.getString(1));
                order.setDdl(resultSet.getDate(2));
                order.setMoney(resultSet.getInt(3));
                order.setAccepted(resultSet.getBoolean(4));
                order.setCarPic(resultSet.getString(5));
                order.setOrder_car(resultSet.getInt(6));
                arrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return arrayList;
    }

    public ArrayList<Order> updateTheirOrder() {
        ResultSet resultSet = null;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("SELECT order_by, order_ddl, order_money, car_picture, order_id\n" +
                "FROM Orders, Cars\n" +
                "WHERE order_to = '"+email+"'\n" +
                "AND car_id = order_car AND order_accepted = 0");
        ArrayList<Order> arrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setFrom(resultSet.getString(1));
                order.setDdl(resultSet.getDate(2));
                order.setMoney(resultSet.getInt(3));
                order.setCarPic(resultSet.getString(4));
                order.setId(resultSet.getInt(5));
                arrayList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return arrayList;
    }


}
