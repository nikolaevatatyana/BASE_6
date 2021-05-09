package laboratory;

import interfaces.DAO;
import interfaces.TableItem;

import java.sql.*;
import java.util.*;

public class LabDAO implements DAO<Lab>{
    private final Connection connection;

    public LabDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Lab item) throws SQLException {
        String sql = "INSERT INTO LAB (id, name, a_date, survey) VALUES (S_LAB.nextval, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getDate());
        ps.setInt(3, item.getSurvey());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Lab item) throws SQLException {
        String sql = "UPDATE LAB SET name = ?, a_date = ?, survey = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getDate());
        ps.setInt(3, item.getSurvey());
        ps.setInt(4, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM LAB WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> lab = new LinkedList<>();
        String sql = "SELECT * FROM LAB";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int date = rs.getInt("a_date");
            int survey = rs.getInt("survey");
            Lab c = new Lab(id, name, date, survey);
            lab.add(c);
        }
        ps.close();
        rs.close();
        return lab;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> lab = new LinkedList<>();
        String sql = "SELECT * FROM LAB WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int date = rs.getInt("a_date");
            int survey = rs.getInt("survey");
            Lab c = new Lab(id, name, date, survey);
            lab.add(c);
        }
        ps.close();
        rs.close();
        return lab;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_LAB";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_LAB START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Lab getByID(int id) throws SQLException {
        Lab lab = null;
        String sql = "SELECT * FROM LAB WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int date = rs.getInt("a_date");
            int survey = rs.getInt("survey");
            lab = new Lab(id, name, date, survey);
        }
        ps.close();
        rs.close();
        return lab;
    }
}
