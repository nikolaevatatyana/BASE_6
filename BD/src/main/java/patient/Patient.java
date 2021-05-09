package patient;

import interfaces.TableItem;
import java.util.*;

public class Patient implements TableItem{
        private final int id;
        private final String name;
        private final String state;
        private final int polyclinic_id;
        private final int doctor_id;
        private final int ward_id;

        public Patient(String name, String state, int polyclinic_id, int doctor_id, int ward_id) {
            id = -1;
            this.name = name;
            this.state = state;
            this.polyclinic_id = polyclinic_id;
            this.doctor_id = doctor_id;
            this.ward_id = ward_id;
        }

        public Patient(int id, String name, String state, int polyclinic_id, int doctor_id, int ward_id) {
            this.id = id;
            this.name = name;
            this.state = state;
            this.polyclinic_id = polyclinic_id;
            this.doctor_id = doctor_id;
            this.ward_id = ward_id;
        }

        public int getID() { return id; }
        public String getName() { return name; }
        public String getState() { return state; }
        public int getPolyclinic_id() { return polyclinic_id; }
        public int getDoctor_id() { return doctor_id; }
        public int getWard_id() { return ward_id; }

        @Override
        public Map<String, Object> getValues() {
            Map<String, Object> values = new HashMap<>();
            values.put("id", id);
            values.put("name", name);
            values.put("state", state);
            values.put("polyclinic_id", polyclinic_id);
            values.put("doctor_id", doctor_id);
            values.put("ward_id", ward_id);
            return values;
        }

        @Override
        public String toString() {
            return "Patient{id = " + id
                    + ", name = '" + name
                    + "', state = '" + state
                    + "', polyclinic_id = '" + polyclinic_id
                    + "', doctor_id = " + doctor_id
                    + ", ward_id = " + ward_id + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Patient patient = (Patient) o;
            return id == patient.id
                    && polyclinic_id == patient.polyclinic_id
                    && doctor_id == patient.doctor_id
                    && ward_id == patient.ward_id
                    && Objects.equals(name, patient.name)
                    && Objects.equals(state, patient.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, state, polyclinic_id, doctor_id, ward_id);
        }
    }

