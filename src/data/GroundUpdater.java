package data;

import connecter.SqlConnect;
import data.park.Car;
import data.park.Carport;
import data.info.Info;
import data.user.News;

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
    public GroundUpdater() {
        sqlConnect = new SqlConnect();
        email = "";
    }

    public ArrayList<Carport> updateCarports() {
        ResultSet resultSet;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("select carport_id, ifnull(user_nickname, user_email), carport_state, carport_date, carport_price from Carports, Users WHERE carport_owner = user_email");
        ArrayList<Carport> carports = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Carport carport = new Carport();
                carport.setId(resultSet.getInt(1));
                carport.setOwner(resultSet.getString(2));
                carport.setState(resultSet.getString(3));
                carport.setDate(resultSet.getDate(4));
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
                "car_name, car_picture, ifnull(user_nickname, user_email)  " +
                "from Cars, Users WHERE Cars.car_owner = Users.user_email");
        ArrayList<Car> cars = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setName(resultSet.getString(1));
                car.setPicture(resultSet.getString(2));
                car.setOwner(resultSet.getString(3));
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
                 "car_name, car_picture " +
                 "from Cars WHERE Cars.car_owner = '" + email + "'");
         ArrayList<Car> cars = new ArrayList<>();
         try {
             while (resultSet.next()) {
                 Car car = new Car();
                 car.setName(resultSet.getString(1));
                 car.setPicture(resultSet.getString(2));
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
        resultSet = sqlConnect.executeQuery("SELECT news_content, ifnull(user_nickname, user_email), news_time FROM News JOIN Users WHERE news_owner = user_email ORDER BY news_time DESC LIMIT 10 ");
        ArrayList<News> newsArrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
               News news = new News();
               news.setContent(resultSet.getString(1));
               news.setOwner(resultSet.getString(2));
               news.setTime(resultSet.getTime(3));
               news.setDate(resultSet.getDate(3));
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
}
