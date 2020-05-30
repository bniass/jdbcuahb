package uahb.coursjava.service;

import uahb.coursjava.DatabaseHelper;
import uahb.coursjava.model.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceService implements IService {
    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();
        try {
            DatabaseHelper db = new DatabaseHelper();
            ResultSet rs = db.mySelect("service");

            while (rs.next()){
                Service s = new Service(rs.getInt(1), rs.getString(2));
                services.add(s);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }
}
