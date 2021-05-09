package frames;

import profiles.ProfDAO;
import profiles.Prof;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;


import javax.swing.*;
import java.sql.*;
import java.util.List;

public class ProfFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();

    public ProfFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 260);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(((Prof) ti).getName());
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 'type_1'");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(70, 170, 200, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
    }

    @Override
    protected void create() {
        try {
            ProfDAO dao = (ProfDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            Prof dt = new Prof(name);
            dao.add(dt);
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
            ProfDAO dao = (ProfDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            Prof newDT = new Prof(((Prof)ti).getID(), name);
            dao.update(newDT);
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
            ProfDAO dao = (ProfDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            appendConditionPart(condition, s1);
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
