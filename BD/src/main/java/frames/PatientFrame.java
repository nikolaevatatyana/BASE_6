package frames;


import patient.PatientDAO;
import patient.Patient;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;


import javax.swing.*;
import java.sql.*;
import java.util.List;

public class PatientFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();
    private final JLabel stateLabel = new JLabel("STATE:");
    private final JTextField stateTextField = new JTextField();
    private final JLabel polyclinicIDLabel = new JLabel("POLYCLINIC ID:");
    private final JTextField polyclinicIDTextField = new JTextField();
    private final JLabel doctorIDLabel = new JLabel("DOCTOR ID:");
    private final JTextField doctorIDTextField = new JTextField();
    private final JLabel wardIDLabel = new JLabel("WARD ID:");
    private final JTextField wardIDTextField = new JTextField();

    public PatientFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 420);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(String.valueOf(((Patient) ti).getName()));
            stateTextField.setText(String.valueOf(((Patient) ti).getState()));
            polyclinicIDTextField.setText(String.valueOf(((Patient) ti).getPolyclinic_id()));
            doctorIDTextField.setText(String.valueOf(((Patient) ti).getDoctor_id()));
            wardIDTextField.setText(String.valueOf(((Patient) ti).getWard_id()));
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 2");
            stateTextField.setText("= 2");
           polyclinicIDTextField.setText("<= 200.0");
            doctorIDTextField.setText("> 3");
            wardIDTextField.setText("< 10");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(70, 170, 200, 30);
        stateLabel.setBounds(10, 210, 260, 30);
        stateTextField.setBounds(70, 210, 200, 30);
        polyclinicIDLabel.setBounds(10, 250, 260, 30);
        polyclinicIDTextField.setBounds(70, 250, 200, 30);
        doctorIDLabel.setBounds(10, 290, 260, 30);
        doctorIDTextField.setBounds(100, 290, 170, 30);
        wardIDLabel.setBounds(10, 330, 260, 30);
        wardIDTextField.setBounds(70, 330, 200, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(stateLabel);
        container.add(stateTextField);
        container.add(polyclinicIDLabel);
        container.add(polyclinicIDTextField);
        container.add(doctorIDLabel);
        container.add(doctorIDTextField);
        container.add(wardIDLabel);
        container.add(wardIDTextField);
    }

    @Override
    protected void create() {
        try {
            PatientDAO dao = (PatientDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            String state = nameTextField.getText();
            int polyclinicID = Integer.parseInt(polyclinicIDTextField.getText());
            int doctorID = Integer.parseInt(doctorIDTextField.getText());
            int wardID = Integer.parseInt(wardIDTextField.getText());
            Patient d = new Patient(name, state, polyclinicID, doctorID, wardID);
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
            PatientDAO dao = (PatientDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            String state = nameTextField.getText();
            int polyclinicID = Integer.parseInt(polyclinicIDTextField.getText());
            int doctorID = Integer.parseInt(doctorIDTextField.getText());
            int wardID = Integer.parseInt(wardIDTextField.getText());
            Patient d = new Patient(((Patient)ti).getID(), name, state, polyclinicID, doctorID, wardID);
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
            PatientDAO dao = (PatientDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            if (!stateTextField.getText().equals("")) {
                s2 = "state " + stateTextField.getText();
            }
            if (!polyclinicIDTextField.getText().equals("")) {
                s3 = "polyclinicID " + polyclinicIDTextField.getText();
            }
            if (!doctorIDTextField.getText().equals("")) {
                s4 = "doctorID " + doctorIDTextField.getText();
            }
            if (!wardIDTextField.getText().equals("")) {
                s5 = "wardID " + wardIDTextField.getText();
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
