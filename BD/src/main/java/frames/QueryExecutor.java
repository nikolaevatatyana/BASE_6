package frames;

import doctors.*;
import hospital.*;
import laboratory.*;
import patient.*;
import polyclinic.*;
import profiles.*;
import ward.*;
import frames.*;
import java.sql.*;
import java.util.*;

public class QueryExecutor {
    private final MainQueriesFrame mqf;
    private final QueryFrame qf;
    private final Connection connection;

    public QueryExecutor(MainQueriesFrame mqf, QueryFrame qf, Connection connection) {
        this.mqf = mqf;
        this.qf = qf;
        this.connection = connection;
    }

    public String[] execute(String queryName, String parameter) throws SQLException {
        switch (queryName) {
            case "Doctors of a specific profile":
                return doctorsOfSpecificProfile(parameter);
          /*  case "Info about drug":
                return infoAboutDrug(parameter);
            case "Cost of ready drug / its components":
                return costOfDrugAndComponents(parameter);*/
            default:
                return null;
        }
    }

    private String[] doctorsOfSpecificProfile(String profile) throws SQLException {
        if (profile == null) {
            QueryParameterFrame qpf = new QueryParameterFrame(mqf, qf, "Doctors of a specific profile");
            return new String[0];
        }
        DoctorsDAO doctorsDAO = new DoctorsDAO(connection);
        List<Doctors> doctors = doctorsDAO.getByProfile(profile);
        List<String> result = new LinkedList<>();
        for (Doctors doctor : doctors) {
            result.add(doctor.getName());
        }
        return result.toArray(new String[0]);
    }
/*
    private String[] infoAboutDrug(String drugName) throws SQLException {
        if (drugName == null) {
            QueryParameterFrame qpf = new QueryParameterFrame(mqf, qf, "Info about drug");
            return new String[0];
        } else {
            TechnologyDAO tDAO = new TechnologyDAO(connection);
            Technology technology = tDAO.getByDrugName(drugName);
            DrugDAO dDAO = new DrugDAO(connection);
            Drug drug = dDAO.getByTechnologyID(technology.getID());
            DrugTypeDAO dtDAO = new DrugTypeDAO(connection);
            DrugType dt = dtDAO.getByID(drug.getTypeID());
            DrugComponentDAO dcDAO = new DrugComponentDAO(connection);
            Set<Integer> componentsIDs = dcDAO.getComponentsForDrug(drug.getID()).keySet();
            ComponentDAO cDAO = new ComponentDAO(connection);
            List<String> result = new LinkedList<>();
            result.add("NAME: " + drugName);
            result.add("TYPE: " + dt.getName());
            result.add("TECHNOLOGY: " + technology.getDescription());
            result.add("PRICE: $" + drug.getPrice());
            result.add("AMOUNT: " + drug.getAmount());
            result.add("CRIT. NORMA: " + drug.getCritNorma());
            result.add("COMPONENTS:");
            int count = 1;
            for (int cID : componentsIDs) {
                Component component = cDAO.getByID(cID);
                result.add(count + ". " + component.getName());
                count++;
            }
            return result.toArray(new String[0]);
        }
    }

    private String[] costOfDrugAndComponents(String drugName) throws SQLException {
        if (drugName == null) {
            QueryParameterFrame qpf = new QueryParameterFrame(mqf, qf, "Cost of ready drug / its components");
            return new String[0];
        } else {
            TechnologyDAO tDAO = new TechnologyDAO(connection);
            Technology technology = tDAO.getByDrugName(drugName);
            DrugDAO dDAO = new DrugDAO(connection);
            Drug drug = dDAO.getByTechnologyID(technology.getID());
            DrugComponentDAO dcDAO = new DrugComponentDAO(connection);
            Map<Integer, Float> componentsAndGrams = dcDAO.getComponentsForDrug(drug.getID());
            Set<Integer> componentsIDs = componentsAndGrams.keySet();
            ComponentDAO cDAO = new ComponentDAO(connection);
            List<String> result = new LinkedList<>();
            result.add("NAME: " + drugName);
            result.add("PRICE: $" + drug.getPrice());
            result.add("MADE OF COMPONENTS:");
            int count = 1;
            float sum = 0;
            for (int cID : componentsIDs) {
                Component component = cDAO.getByID(cID);
                float costPerGram = component.getCostPerGram();
                float grams = componentsAndGrams.get(cID);
                result.add(count + ". " + component.getName()
                        + ": $" + costPerGram + "/gram, "
                        + grams + " grams"
                );
                sum += costPerGram * grams;
                count++;
            }
            result.add("COST OF COMPONENTS SEPARATELY: $" + sum);
            return result.toArray(new String[0]);
        }
    }*/
}
