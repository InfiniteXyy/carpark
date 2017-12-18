package data;

import connecter.SqlConnect;
import data.park.Car;
import data.park.Carport;
import data.info.Info;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroundUpdater {
    private SqlConnect sqlConnect;

    public GroundUpdater() {
        sqlConnect = new SqlConnect();
    }

    public ArrayList<Carport> updateCarports() {
        ResultSet resultSet;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("select " +
                "carport_id, carport_owner, carport_state, carport_date, carport_price " +
                "from Carports");
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

    public ArrayList<Car> updateCars() {
        ResultSet resultSet = null;
        sqlConnect.startDB();
        resultSet = sqlConnect.executeQuery("select " +
                "car_name, car_picture, car_owner " +
                "from Cars");
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

    public Info updateInfo() {
        sqlConnect.startDB();
        Info info = sqlConnect.getInfo();
        sqlConnect.endStmt();
        sqlConnect.endDB();
        return info;
    }
}
