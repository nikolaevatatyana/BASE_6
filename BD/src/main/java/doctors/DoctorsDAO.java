package doctors;

import interfaces.DAO;
import interfaces.TableItem;

import java.sql.*;
import java.util.*;

public class DoctorsDAO implements DAO<Doctors>{
    private final Connection connection;

    public DoctorsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Doctors item) throws SQLException {
        String sql = "INSERT INTO DOCTORS (id, name, op, profile) VALUES (S_DOCTORS.nextval, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getOp());
        ps.setString(3, item.getProfile());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Doctors item) throws SQLException {
        String sql = "UPDATE DOCTORS SET name = ?, op = ?, profile = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getOp());
        ps.setString(3, item.getProfile());
        ps.setInt(4, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM DOCTORS WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> doctors = new LinkedList<>();
        String sql = "SELECT * FROM DOCTORS";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int op = rs.getInt("op");
            String profile = rs.getString("profile");
            Doctors c = new Doctors(id, name, op, profile);
            doctors.add(c);
        }
        ps.close();
        rs.close();
        return doctors;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> doctors = new LinkedList<>();
        String sql = "SELECT * FROM DOCTORS WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int op = rs.getInt("op");
            String profile = rs.getString("profile");
            Doctors c = new Doctors(id, name, op, profile);
            doctors.add(c);
        }
        ps.close();
        rs.close();
        return doctors;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_DOCTORS";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_DOCTORS START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Doctors getByID(int id) throws SQLException {
        Doctors doctors = null;
        String sql = "SELECT * FROM DOCTORS WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int op = rs.getInt("op");
            String profile = rs.getString("profile");
            doctors = new Doctors(id, name, op, profile);
        }
        ps.close();
        rs.close();
        return doctors;
    }

    public List<Doctors> getByProfile(String profile) throws SQLException {
        List<Doctors> doctors = new LinkedList<>();
        String sql = "SELECT * FROM DOCTORS WHERE profile = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, profile);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int op = rs.getInt("op");
            doctors.add(new Doctors(id, name, op, profile));
        }
        ps.close();
        rs.close();
        return doctors;
    }
}
