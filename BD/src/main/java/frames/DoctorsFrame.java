package frames;

import doctors.DoctorsDAO;
import doctors.Doctors;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;


import javax.swing.*;
import java.sql.*;
import java.util.List;

public class DoctorsFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();
    private final JLabel opLabel = new JLabel("OP:");
    private final JTextField opTextField = new JTextField();
    private final JLabel profileLabel = new JLabel("Profile:");
    private final JTextField profileTextField = new JTextField();

    public DoctorsFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 330);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(String.valueOf(((Doctors) ti).getName()));
            opTextField.setText(String.valueOf(((Doctors) ti).getOp()));
            profileTextField.setText(String.valueOf(((Doctors) ti).getProfile()));
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 1");
            opTextField.setText("= 3");                   //what???
            profileTextField.setText("> 3.0");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(80, 170, 190, 30);
        opLabel.setBounds(10, 210, 260, 30);
        opTextField.setBounds(80, 210, 190, 30);
        profileLabel.setBounds(10, 250, 260, 30);
        profileTextField.setBounds(120, 250, 150, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(opLabel);
        container.add(opTextField);
        container.add(profileLabel);
        container.add(profileTextField);
    }

    @Override
    protected void create() {
        try {
            DoctorsDAO dao = (DoctorsDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int op = Integer.parseInt(opTextField.getText());
            String profile = profileTextField.getText();
            Doctors dc = new Doctors(name, op, profile);
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
            DoctorsDAO dao = (DoctorsDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            int op = Integer.parseInt(opTextField.getText());
            String profile = profileTextField.getText();
            Doctors dc = new Doctors(((Doctors)ti).getID(), name, op, profile);
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
            DoctorsDAO dao = (DoctorsDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null, s2 = null, s3 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            if (!opTextField.getText().equals("")) {
                s2 = "op " + opTextField.getText();
            }
            if (!profileTextField.getText().equals("")) {
                s3 = "profile " + profileTextField.getText();
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
