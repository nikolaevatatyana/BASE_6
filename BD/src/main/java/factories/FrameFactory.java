package factories;
import frames.*;
import interfaces.TableItem;
import java.sql.Connection;

public class FrameFactory {
    public static ItemFrame getItemFrame(String tableName, ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        switch (tableName) {
            case "PROF":
                return new ProfFrame(type, ti, tf, connection);
            case "LAB":
                return new LabFrame(type, ti, tf, connection);
            case "DOCTORS":
                return new DoctorsFrame(type, ti, tf, connection);
            case "HOSPITAL":
                return new HospitalFrame(type, ti, tf, connection);
            case "PATIENT":
                return new PatientFrame(type, ti, tf, connection);
            case "POLYCLINIC":
                return new PolyclinicFrame(type, ti, tf, connection);
            case "WARD":
                return new WardFrame(type, ti, tf, connection);
            default:
                return null;
        }
    }
}