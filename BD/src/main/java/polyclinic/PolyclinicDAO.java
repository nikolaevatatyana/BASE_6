package polyclinic;

import interfaces.DAO;
import interfaces.TableItem;
import patient.Patient;

import java.sql.*;
import java.util.*;

public class PolyclinicDAO implements DAO<Polyclinic>{
    private final Connection connection;

    public PolyclinicDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Polyclinic item) throws SQLException {
        String sql = "INSERT INTO POLYCLINIC (id, name , hospital_id, doctor, cabinet, n_cabinet) VALUES (S_POLYCLINIC.nextval, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getHospital_id());
        ps.setInt(3, item.getDoctor());
        ps.setInt(4, item.getCabinet());
        ps.setInt(5, item.getN_cabinet());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Polyclinic item) throws SQLException {
        String sql = "UPDATE POLYCLINIC SET name = ?, hospital_id = ?, doctor = ?, cabinet = ?, n_cabinet = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getHospital_id());
        ps.setInt(3, item.getDoctor());
        ps.setInt(4, item.getCabinet());
        ps.setInt(5, item.getN_cabinet());
        ps.setInt(6, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM POLYCLINIC WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> polyclinic = new LinkedList<>();
        String sql = "SELECT * FROM POLYCLINIC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int hospital_id = rs.getInt("hospital_id");
            int doctor = rs.getInt("doctor");
            int cabinet = rs.getInt("cabinet");
            int n_cabinet = rs.getInt("n_cabinet");
            Polyclinic o = new Polyclinic(id, name, hospital_id, doctor, cabinet, n_cabinet);
            polyclinic.add(o);
        }
        ps.close();
        rs.close();
        return polyclinic;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> polyclinic = new LinkedList<>();
        String sql = "SELECT * FROM POLYCLINIC WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int hospital_id = rs.getInt("hospital_id");
            int doctor = rs.getInt("doctor");
            int cabinet = rs.getInt("cabinet");
            int n_cabinet = rs.getInt("n_cabinet");
            Polyclinic o = new Polyclinic(id, name, hospital_id, doctor, cabinet, n_cabinet);
            polyclinic.add(o);
        }
        ps.close();
        rs.close();
        return polyclinic;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_POLYCLINIC";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_POLYCLINIC START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Polyclinic findByID(int id) throws SQLException {
        Polyclinic polyclinic = null;
        String sql = "SELECT * FROM POLYCLINIC WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int PolyclinicID = rs.getInt("id");    // id???
            String name = rs.getString("name");
            int hospital_id = rs.getInt("hospital_id");
            int doctor = rs.getInt("doctor");
            int cabinet = rs.getInt("cabinet");
            int n_cabinet = rs.getInt("n_cabinet");
            polyclinic = new Polyclinic(id, name, hospital_id, doctor, cabinet, n_cabinet);
        }
        ps.close();
        rs.close();
        return polyclinic;
    }
}

