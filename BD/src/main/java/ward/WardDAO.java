package ward;

import interfaces.DAO;
import interfaces.TableItem;
import profiles.Prof;

import java.sql.*;
import java.util.*;

public class WardDAO implements DAO<Ward> {
    private final Connection connection;

    public WardDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Ward item) throws SQLException {
        int bed = item.getBed();
        String sql = "INSERT INTO WARD (id, bed) VALUES (S_WARD.nextval, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, bed);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Ward item) throws SQLException {
        String sql = "UPDATE WARD SET bed = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, item.getBed());
        ps.setInt(2, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM WARD WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> ward = new LinkedList<>();
        String sql = "SELECT * FROM WARD";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int bed = rs.getInt("bed");
            Ward w = new Ward(id, bed);
            ward.add(w);
        }
        ps.close();
        rs.close();
        return ward;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> ward = new LinkedList<>();
        String sql = "SELECT * FROM WARD WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int bed = rs.getInt("bed");
            Ward w = new Ward(id, bed);
            ward.add(w);
        }
        ps.close();
        rs.close();
        return ward;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_WARD";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_WARD START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Ward getByID(int id) throws SQLException {
        Ward w = null;
        String sql = "SELECT * FROM WARD WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int dtID = rs.getInt("id");
            int bed = rs.getInt("bed");
            w = new Ward(dtID, bed);
        }
        ps.close();
        rs.close();
        return w;
    }
}

