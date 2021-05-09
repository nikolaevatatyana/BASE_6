package patient;

import interfaces.DAO;
import interfaces.TableItem;

import java.sql.*;
import java.util.*;


public class PatientDAO implements DAO<Patient>{
    private final Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Patient item) throws SQLException {
        String sql = "INSERT INTO PATIENT (id, name , state, polyclinic_id, doctor_id, ward_id) VALUES (S_PATIENT.nextval, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setString(2, item.getState());
        ps.setInt(3, item.getPolyclinic_id());
        ps.setInt(4, item.getDoctor_id());
        ps.setInt(5, item.getWard_id());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Patient item) throws SQLException {
        String sql = "UPDATE PATIENT SET name = ?, state = ?, polyclinic_id = ?, doctor_id = ?, ward_id = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setString(2, item.getState());
        ps.setInt(3, item.getPolyclinic_id());
        ps.setInt(4, item.getDoctor_id());
        ps.setInt(5, item.getWard_id());
        ps.setInt(6, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PATIENT WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> patient = new LinkedList<>();
        String sql = "SELECT * FROM PATIENT";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String state = rs.getString("state");
            int polyclinic_id = rs.getInt("polyclinic_id");
            int doctor_id = rs.getInt("doctor_id");
            int ward_id = rs.getInt("ward_id");
            Patient o = new Patient(id, name, state, polyclinic_id, doctor_id, ward_id);
            patient.add(o);
        }
        ps.close();
        rs.close();
        return patient;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> patient = new LinkedList<>();
        String sql = "SELECT * FROM PATIENT WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String state = rs.getString("state");
            int polyclinic_id = rs.getInt("polyclinic_id");
            int doctor_id = rs.getInt("doctor_id");
            int ward_id = rs.getInt("ward_id");
            Patient o = new Patient(id, name, state, polyclinic_id, doctor_id, ward_id);
            patient.add(o);
        }
        ps.close();
        rs.close();
        return patient;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_PATIENT";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_PATIENT START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Patient findByID(int id) throws SQLException {
        Patient patient = null;
        String sql = "SELECT * FROM PATIENT WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int PatientID = rs.getInt("id");    // id???
            String name = rs.getString("name");
            String state = rs.getString("state");
            int polyclinic_id = rs.getInt("polyclinic_id");
            int doctor_id = rs.getInt("doctor_id");
            int ward_id = rs.getInt("ward_id");
            patient = new Patient(id, name, state, polyclinic_id, doctor_id, ward_id);
        }
        ps.close();
        rs.close();
        return patient;
    }
}
