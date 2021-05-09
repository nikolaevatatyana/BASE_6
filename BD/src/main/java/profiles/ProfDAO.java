package profiles;

import interfaces.DAO;
import interfaces.TableItem;

import java.sql.*;
import java.util.*;

public class ProfDAO implements DAO<Prof> {
    private final Connection connection;

    public ProfDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Prof item) throws SQLException {
        String name = item.getName();
        String sql = "INSERT INTO PROF (id, name) VALUES (S_PROF.nextval, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void update(Prof item) throws SQLException {
        String sql = "UPDATE PROF SET name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, item.getName());
        ps.setInt(2, item.getID());
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PROF WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    @Override
    public List<TableItem> getAll() throws SQLException {
        List<TableItem> profs = new LinkedList<>();
        String sql = "SELECT * FROM PROF";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Prof dt = new Prof(id, name);
            profs.add(dt);
        }
        ps.close();
        rs.close();
        return profs;
    }

    @Override
    public List<TableItem> getByParameters(String condition) throws SQLException {
        List<TableItem> prof = new LinkedList<>();
        String sql = "SELECT * FROM PROF WHERE 1 = 1 AND " + condition;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Prof pr = new Prof(id, name);
            prof.add(pr);
        }
        ps.close();
        rs.close();
        return prof;
    }

    @Override
    public void resetSequence() throws SQLException {
        String sql1 = "DROP SEQUENCE S_PROF";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.executeUpdate();
        String sql2 = "CREATE SEQUENCE S_PROF START WITH 1 INCREMENT BY 1 NOMAXVALUE";
        ps = connection.prepareStatement(sql2);
        ps.executeUpdate();
        connection.commit();
        ps.close();
    }

    public Prof getByID(int id) throws SQLException {
        Prof pr = null;
        String sql = "SELECT * FROM PROF WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int dtID = rs.getInt("id");
            String name = rs.getString("name");
            pr = new Prof(dtID, name);
        }
        ps.close();
        rs.close();
        return pr;
    }
}
