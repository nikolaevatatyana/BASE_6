package frames;

import laboratory.LabDAO;
import laboratory.Lab;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class LabFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();
    private final JLabel surveyLabel = new JLabel("SURVEY:");
    private final JTextField surveyTextField = new JTextField();
    private final JLabel dateLabel = new JLabel("DATE:");
    private final JTextField dateTextField = new JTextField();

    public LabFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 330);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(String.valueOf(((Lab) ti).getName()));
            surveyTextField.setText(String.valueOf(((Lab) ti).getSurvey()));
            dateTextField.setText(String.valueOf(((Lab) ti).getDate()));
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 1");
            surveyTextField.setText("= 3");                   //what???
            dateTextField.setText("> 3.0");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(80, 170, 190, 30);
        surveyLabel.setBounds(10, 210, 260, 30);
        surveyTextField.setBounds(80, 210, 190, 30);
        dateLabel.setBounds(10, 250, 260, 30);
        dateTextField.setBounds(120, 250, 150, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(surveyLabel);
        container.add(surveyTextField);
        container.add(dateLabel);
        container.add(dateTextField);
    }

    @Override
    protected void create() {
        try {
            LabDAO dao = (LabDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int survey = Integer.parseInt(surveyTextField.getText());
            int date = Integer.parseInt(dateTextField.getText());
            Lab dc = new Lab(name, survey, date);
            dao.add(dc);
            tf.setVisible(true);
            dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this, "Could not create item!",
                    "Error!", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    protected void edit() {
        try {
            LabDAO dao = (LabDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int survey = Integer.parseInt(surveyTextField.getText());
            int date = Integer.parseInt(dateTextField.getText());
            Lab dc = new Lab(((Lab)ti).getID(), name, survey, date);
            dao.update(dc);
            tf.setVisible(true);
            dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this, "Could not edit item!",
                    "Error!", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    protected void find() {
        try {
            LabDAO dao = (LabDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null, s2 = null, s3 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            if (!surveyTextField.getText().equals("")) {
                s2 = "survey " + surveyTextField.getText();
            }
            if (!dateTextField.getText().equals("")) {
                s3 = "date " + dateTextField.getText();
            }
            appendConditionPart(condition, s1);
            appendConditionPart(condition, s2);
            appendConditionPart(condition, s3);
            List<TableItem> foundItems = dao.getByParameters(condition.toString());
            tf.setVisible(true);
            tf.updateItems(foundItems);
            dispose();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(
                    this, "Could not find items!",
                    "Error!", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
