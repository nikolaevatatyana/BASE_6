package ward;

import interfaces.TableItem;
import java.util.*;

public class Ward implements TableItem {
    private final int id;
    private final int bed;

    public Ward(int bed) {
        this.id = -1;
        this.bed = bed;
    }

    public Ward(int id, int bed) {
        this.id = id;
        this.bed = bed;
    }

    public int getID() { return id; }

    public int getBed() { return bed; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("bed", bed);
        return values;
    }

    @Override
    public String toString() {
        return "Bed{id = " + id + ", bed = '" + bed + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return id == ward.id && Objects.equals(bed, ward.bed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bed);
    }
}
