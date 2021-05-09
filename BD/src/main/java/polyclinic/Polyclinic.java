package polyclinic;

import interfaces.TableItem;
import patient.Patient;

import java.util.*;

public class Polyclinic implements TableItem{
    private final int id;
    private final String name;
    private final int hospital_id;
    private final int doctor;
    private final int cabinet;
    private final int n_cabinet;

    public Polyclinic(String name, int hospital_id, int doctor, int cabinet, int n_cabinet) {
        id = -1;
        this.name = name;
        this.hospital_id = hospital_id;
        this.doctor = doctor;
        this.cabinet = cabinet;
        this.n_cabinet = n_cabinet;
    }

    public Polyclinic(int id, String name, int hospital_id, int doctor, int cabinet, int n_cabinet) {
        this.id = id;
        this.name = name;
        this.hospital_id = hospital_id;
        this.doctor = doctor;
        this.cabinet = cabinet;
        this.n_cabinet = n_cabinet;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public int getHospital_id() { return hospital_id; }
    public int getDoctor() { return doctor; }
    public int getCabinet() { return cabinet; }
    public int getN_cabinet() { return n_cabinet; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        values.put("hospital_id", hospital_id);
        values.put("doctor", doctor);
        values.put("cabinet", cabinet);
        values.put("n_cabinet", n_cabinet);
        return values;
    }

    @Override
    public String toString() {
        return "Polyclinic{id = " + id
                + ", name = '" + name
                + "', hospital_id = '" + hospital_id
                + "', doctor = '" + doctor
                + "', cabinet = " + cabinet
                + ", n_cabinet = " + n_cabinet + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polyclinic polyclinic = (Polyclinic) o;
        return id == polyclinic.id
                && hospital_id == polyclinic.hospital_id
                && doctor == polyclinic.doctor
                && cabinet == polyclinic.cabinet
                && n_cabinet == polyclinic.n_cabinet
                && Objects.equals(name, polyclinic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hospital_id, doctor, cabinet, n_cabinet);
    }
}
