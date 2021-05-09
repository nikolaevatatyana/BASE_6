package frames;

import polyclinic.PolyclinicDAO;
import polyclinic.Polyclinic;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class PolyclinicFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();
    private final JLabel hospitalIDLabel = new JLabel("HOSPITAL ID:");
    private final JTextField hospitalIDTextField = new JTextField();
    private final JLabel doctorLabel = new JLabel("DOCTOR:");
    private final JTextField doctorTextField = new JTextField();
    private final JLabel cabinetLabel = new JLabel("CABINET:");
    private final JTextField cabinetTextField = new JTextField();
    private final JLabel n_cabinetLabel = new JLabel("N. CABINET:");
    private final JTextField n_cabinetTextField = new JTextField();

    public PolyclinicFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 420);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(String.valueOf(((Polyclinic) ti).getName()));
            hospitalIDTextField.setText(String.valueOf(((Polyclinic) ti).getHospital_id()));
            doctorTextField.setText(String.valueOf(((Polyclinic) ti).getDoctor()));
            cabinetTextField.setText(String.valueOf(((Polyclinic) ti).getCabinet()));
            n_cabinetTextField.setText(String.valueOf(((Polyclinic) ti).getN_cabinet()));
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 2");
            hospitalIDTextField.setText("= 2");
            doctorTextField.setText("<= 200.0");
            cabinetTextField.setText("> 3");
            n_cabinetTextField.setText("< 10");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(70, 170, 200, 30);
        hospitalIDLabel.setBounds(10, 210, 260, 30);
        hospitalIDTextField.setBounds(70, 210, 200, 30);
        doctorLabel.setBounds(10, 250, 260, 30);
        doctorTextField.setBounds(70, 250, 200, 30);
        cabinetLabel.setBounds(10, 290, 260, 30);
        cabinetTextField.setBounds(100, 290, 170, 30);
        n_cabinetLabel.setBounds(10, 330, 260, 30);
       n_cabinetTextField.setBounds(70, 330, 200, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(hospitalIDLabel);
        container.add(hospitalIDTextField);
        container.add(doctorLabel);
        container.add(doctorTextField);
        container.add(cabinetLabel);
        container.add(cabinetTextField);
        container.add(n_cabinetLabel);
        container.add(n_cabinetTextField);
    }

    @Override
    protected void create() {
        try {
            PolyclinicDAO dao = (PolyclinicDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int hospital_id = Integer.parseInt(hospitalIDTextField.getText());
            int doctor = Integer.parseInt(doctorTextField.getText());
            int cabinet = Integer.parseInt(cabinetTextField.getText());
            int n_cabinet = Integer.parseInt(n_cabinetTextField.getText());
            Polyclinic d = new Polyclinic(name, hospital_id, doctor, cabinet, n_cabinet);
            dao.add(d);
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
            PolyclinicDAO dao = (PolyclinicDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int hospital_id = Integer.parseInt(hospitalIDTextField.getText());
            int doctor = Integer.parseInt(doctorTextField.getText());
            int cabinet = Integer.parseInt(cabinetTextField.getText());
            int n_cabinet = Integer.parseInt(n_cabinetTextField.getText());
            Polyclinic d = new Polyclinic(((Polyclinic)ti).getID(), name, hospital_id, doctor, cabinet, n_cabinet);
            dao.update(d);
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
            PolyclinicDAO dao = (PolyclinicDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            if (!hospitalIDTextField.getText().equals("")) {
                s2 = "hospital " + hospitalIDTextField.getText();
            }
            if (!doctorTextField.getText().equals("")) {
                s3 = "doctor " + doctorTextField.getText();
            }
            if (!cabinetTextField.getText().equals("")) {
                s4 = "cabinet " + cabinetTextField.getText();
            }
            if (!n_cabinetTextField.getText().equals("")) {
                s5 = "n_cabinet " + n_cabinetTextField.getText();
            }
            appendConditionPart(condition, s1);
            appendConditionPart(condition, s2);
            appendConditionPart(condition, s3);
            appendConditionPart(condition, s4);
            appendConditionPart(condition, s5);
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
