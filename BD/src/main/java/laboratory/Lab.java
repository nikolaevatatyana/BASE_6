package laboratory;

import interfaces.TableItem;
import java.util.*;

public class Lab implements TableItem{
    private final int id;
    private final String name;
    private final int date;
    private final int survey;

    public Lab(String name, int date, int survey) {
        this.id = -1;
        this.name = name;
        this.date = date;
        this.survey = survey;
    }

    public Lab(int id, String name, int date, int survey) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.survey = survey;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public int getDate() { return date; }
    public int getSurvey() { return survey; }

    @Override
    public Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<>();
        values.put("id", id);
        values.put("name", name);
        values.put("a_date", date);
        values.put("survey", survey);
        return values;
    }

    @Override
    public String toString() {
        return "Lab{id = " + id
                + ", name = '" + name
                + "', a_date = " + date
                + ", survey = " + survey + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab lab = (Lab) o;
        return id == lab.id
                && date == lab.date
                && Objects.equals(name, lab.name)
                && Objects.equals(survey, lab.survey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, survey);
    }
}
