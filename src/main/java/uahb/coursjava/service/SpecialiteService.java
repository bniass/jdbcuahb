package uahb.coursjava.service;

import uahb.coursjava.DatabaseHelper;
import uahb.coursjava.model.Service;
import uahb.coursjava.model.Specialite;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteService implements ISpecialite {
    @Override
    public List<Specialite> findAll() {
        List<Specialite> specialites = new ArrayList<>();
        try {
            DatabaseHelper db = new DatabaseHelper();
            ResultSet rs = db.mySelect("specialite");
            while (rs.next()){
                Specialite s = new Specialite(rs.getInt(1), rs.getString(2));
                specialites.add(s);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialites;
    }

    @Override
    public Specialite findByLibelle(String libelle) {
        Specialite specialite = null;
        try {
            DatabaseHelper db = new DatabaseHelper();
            String sql = "SELECT * FROM specialite WHERE libelle = ?";
            Object[] params = {libelle};
            db.myPreparedStatement(sql, params);
            ResultSet rs = db.myExecuteQuery();
            if (rs.next()){
                specialite = new Specialite(rs.getInt(1), rs.getString(2));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialite;
    }

    @Override
    public Specialite save(Specialite specialite) {
        try {
            DatabaseHelper db = new DatabaseHelper();
            String sql = "INSERT INTO specialite VALUES(null, ?, ?)";
            Object[] params = {specialite.getLibelle(), specialite.getServive().getId()};
            db.myPreparedStatement(sql, params);
            db.myExecuteUpdate();
            ResultSet rs = db.getPstmt().getGeneratedKeys();
            if (rs.next()){
                specialite.setId(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialite;
    }
}
