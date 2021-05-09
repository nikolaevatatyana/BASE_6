package frames;

import ward.WardDAO;
import ward.Ward;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class WardFrame extends ItemFrame{
    private final JLabel bedLabel = new JLabel("BED:");
    private final JTextField bedTextField = new JTextField();

    public WardFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 260);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            bedTextField.setText(String.valueOf(((Ward) ti).getBed()));
        } else if (type == ItemFrameType.FIND) {
            bedTextField.setText("= 1");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        bedLabel.setBounds(10, 170, 260, 30);
        bedTextField.setBounds(90, 170, 180, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(bedLabel);
        container.add(bedTextField);
    }

    @Override
    protected void create() {
        try {
            WardDAO dao = (WardDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            int orderID = Integer.parseInt(bedTextField.getText());
            Ward o = new Ward(orderID);
            dao.add(o);
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
            WardDAO dao = (WardDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            int orderID = Integer.parseInt(bedTextField.getText());
            Ward o = new Ward(((Ward)ti).getID(), orderID);
            dao.update(o);
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
            WardDAO dao = (WardDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null;
            if (!bedTextField.getText().equals("")) {
                s1 = "bed " + bedTextField.getText();
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
