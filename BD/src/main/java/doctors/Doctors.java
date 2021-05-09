package doctors;

import interfaces.TableItem;

import java.util.*;

public class Doctors implements TableItem {
    private final int id;
    private final String name;
    private final int op;
    private final String profile;


    public Doctors(String name, int op, String profile) {
        this.id = -1;
        this.name = name;
        this.op = op;
        this.profile = profile;
    }

    public Doctors(int id, String name, int op, String profile) {
        this.id = id;
        this.name = name;
        this.op = op;
        this.profile = profile;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public int getOp() { return op; }
    public String getProfile() { return profile; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        values.put("op", op);
        values.put("profile", profile);
        return values;
    }

    @Override
    public String toString() {
        return "Doctors{id = " + id
                + ", name = '" + name
                + "', op = " + op
                + ", profile = " + profile + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctors doctors = (Doctors) o;
        return id == doctors.id
                && op == doctors.op
                && Objects.equals(name, doctors.name)
                && Objects.equals(profile, doctors.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, op, profile);
    }
}
