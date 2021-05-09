package hospital;

import interfaces.DAO;
import interfaces.TableItem;
import patient.Patient;

import java.sql.*;
import java.util.*;

public class HospitalDAO implements DAO<Hospital>{
    private final Connection connection;

    public HospitalDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Hospital item) throws SQLException {
        String sql = "INSERT INTO HOSPITAL (id, name , prof, corpus, ward, f_ward) VALUES (S_HOSPITAL.nextval, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setString(2, item.getProf());
        ps.setInt(3, item.getCorpus());
        ps.setInt(4, item.getWard());
        ps.setInt(5, item.getF_ward());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Hospital item) throws SQLException {
        String sql = "UPDATE HOSPITAL SET name = ?, prof = ?, corpus = ?, ward = ?, f_ward = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setString(2, item.getProf());
        ps.setInt(3, item.getCorpus());
        ps.setInt(4, item.getWard());
        ps.setInt(5, item.getF_ward());
        ps.setInt(6, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM HOSPITAL WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> hospital = new LinkedList<>();
        String sql = "SELECT * FROM HOSPITAL";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String prof = rs.getString("prof");
            int corpus = rs.getInt("corpus");
            int ward = rs.getInt("ward");
            int f_ward = rs.getInt("f_ward");
            Hospital o = new Hospital(id, name, prof, corpus, ward, f_ward);
            hospital.add(o);
        }
        ps.close();
        rs.close();
        return hospital;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> hospital = new LinkedList<>();
        String sql = "SELECT * FROM HOSPITAL WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String prof = rs.getString("prof");
            int corpus = rs.getInt("corpus");
            int ward = rs.getInt("ward");
            int f_ward = rs.getInt("f_ward");
            Hospital o = new Hospital(id, name, prof, corpus, ward, f_ward);
            hospital.add(o);
        }
        ps.close();
        rs.close();
        return hospital;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_HOSPITAL";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_HOSPITAL START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Hospital findByID(int id) throws SQLException {
        Hospital hospital = null;
        String sql = "SELECT * FROM HOSPITAL WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int HospitalID = rs.getInt("id");    // id???
            String name = rs.getString("name");
            String prof = rs.getString("prof");
            int corpus = rs.getInt("corpus");
            int ward = rs.getInt("ward");
            int f_ward = rs.getInt("f_ward");
            hospital = new Hospital(id, name, prof, corpus, ward, f_ward);
        }
        ps.close();
        rs.close();
        return hospital;
    }
}
