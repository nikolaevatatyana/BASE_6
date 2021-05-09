package hospital;

import interfaces.TableItem;
import patient.Patient;

import java.util.*;

public class Hospital implements TableItem{
    private final int id;
    private final String name;
    private final String prof;
    private final int corpus;
    private final int ward;
    private final int f_ward;

    public Hospital(String name, String prof, int corpus, int ward, int f_ward) {
        id = -1;
        this.name = name;
        this.prof = prof;
        this.corpus = corpus;
        this.ward = ward;
        this.f_ward = f_ward;
    }

    public Hospital(int id, String name, String prof, int corpus, int ward, int f_ward) {
        this.id = id;
        this.name = name;
        this.prof = prof;
        this.corpus = corpus;
        this.ward = ward;
        this.f_ward = f_ward;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public String getProf() { return prof; }
    public int getCorpus() { return corpus; }
    public int getWard() { return ward; }
    public int getF_ward() { return f_ward; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        values.put("state", prof);
        values.put("polyclinic_id", corpus);
        values.put("doctor_id", ward);
        values.put("ward_id", f_ward);
        return values;
    }

    @Override
    public String toString() {
        return "Hospital{id = " + id
                + ", name = '" + name
                + "', prof = '" + prof
                + "', corpus = '" + corpus
                + "', ward = " + ward
                + ", f_ward = " + f_ward + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return id == hospital.id
                && corpus == hospital.corpus
                && ward== hospital.ward
                && f_ward== hospital.f_ward
                && Objects.equals(name, hospital.name)
                && Objects.equals(prof, hospital.prof);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, prof, corpus, ward, f_ward);
    }
}
