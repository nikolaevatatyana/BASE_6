package factories;

import hospital.HospitalDAO;
import interfaces.DAO;
import profiles.ProfDAO;
import laboratory.LabDAO;
import ward.WardDAO;
import doctors.DoctorsDAO;
import patient.PatientDAO;
import polyclinic.PolyclinicDAO;
import hospital.HospitalDAO;
import java.sql.*;

public class DAOFactory {
    public static DAO createDAO(String tableName, Connection connection) throws SQLException {
        DAO dao = null;
        switch (tableName) {
            case "prof":
                dao = new ProfDAO(connection);
                break;
            case "lab":
                dao = new LabDAO(connection);
                break;
            case "ward":
                dao = new WardDAO(connection);
                break;
            case "doctors":
                dao = new DoctorsDAO(connection);
                break;
            case "patient":
                dao = new PatientDAO(connection);
                break;
            case "polyclinic":
                dao = new PolyclinicDAO(connection);
                break;
            case "hospital":
                dao = new HospitalDAO(connection);
                break;
            default:
                break;
        }
        return dao;
    }
}