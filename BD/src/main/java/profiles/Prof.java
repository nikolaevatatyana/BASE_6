package profiles;

import interfaces.TableItem;
import java.util.*;

public class Prof implements TableItem {
    private final int id;
    private final String name;

    public Prof(String name) {
        this.id = -1;
        this.name = name;
    }

    public Prof(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() { return id; }

    public String getName() { return name; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        return values;
    }

    @Override
    public String toString() {
        return "Prof{id = " + id + ", name = '" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prof prof = (Prof) o;
        return id == prof.id && Objects.equals(name, prof.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
