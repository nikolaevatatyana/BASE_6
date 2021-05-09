package frames;

import hospital.HospitalDAO;
import hospital.Hospital;
import factories.DAOFactory;
import frames.TableFrame;
import interfaces.TableItem;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class HospitalFrame extends ItemFrame{
    private final JLabel nameLabel = new JLabel("NAME:");
    private final JTextField nameTextField = new JTextField();
    private final JLabel profLabel = new JLabel("PROF:");
    private final JTextField profTextField = new JTextField();
    private final JLabel corpusLabel = new JLabel("CORPUS:");
    private final JTextField corpusTextField = new JTextField();
    private final JLabel wardLabel = new JLabel("WARD:");
    private final JTextField wardTextField = new JTextField();
    private final JLabel f_wardLabel = new JLabel("F. WARD:");
    private final JTextField f_wardTextField = new JTextField();

    public HospitalFrame(ItemFrameType type, TableItem ti, TableFrame tf, Connection connection) {
        super(type, ti, tf, connection);
        initComponents();
        setBounds(10, 10, 300, 420);
    }

    @Override
    protected void setTextOnTextFields() {
        if (type == ItemFrameType.EDIT) {
            nameTextField.setText(String.valueOf(((Hospital) ti).getName()));
            profTextField.setText(String.valueOf(((Hospital) ti).getProf()));
            corpusTextField.setText(String.valueOf(((Hospital) ti).getCorpus()));
            wardTextField.setText(String.valueOf(((Hospital) ti).getWard()));
            f_wardTextField.setText(String.valueOf(((Hospital) ti).getF_ward()));
        } else if (type == ItemFrameType.FIND) {
            nameTextField.setText("= 2");
            profTextField.setText("= 2");
            corpusTextField.setText("<= 200.0");
            wardTextField.setText("> 3");
            f_wardTextField.setText("< 10");
        }
    }

    @Override
    protected void setLocationAndSizeForCustom() {
        nameLabel.setBounds(10, 170, 260, 30);
        nameTextField.setBounds(70, 170, 200, 30);
        profLabel.setBounds(10, 210, 260, 30);
        profTextField.setBounds(70, 210, 200, 30);
        corpusLabel.setBounds(10, 250, 260, 30);
        corpusTextField.setBounds(70, 250, 200, 30);
        wardLabel.setBounds(10, 290, 260, 30);
        wardTextField.setBounds(100, 290, 170, 30);
        f_wardLabel.setBounds(10, 330, 260, 30);
        f_wardTextField.setBounds(70, 330, 200, 30);
    }

    @Override
    protected void addCustomComponentsToContainer() {
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(profLabel);
        container.add(profTextField);
        container.add(corpusLabel);
        container.add(corpusTextField);
        container.add(wardLabel);
        container.add(wardTextField);
        container.add(f_wardLabel);
        container.add(f_wardTextField);
    }

    @Override
    protected void create() {
        try {
            HospitalDAO dao = (HospitalDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            String prof = nameTextField.getText();
            int corpus = Integer.parseInt(corpusTextField.getText());
            int ward = Integer.parseInt(wardTextField.getText());
            int f_ward = Integer.parseInt(f_wardTextField.getText());
            Hospital d = new Hospital(name, prof, corpus, ward, f_ward);
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
            HospitalDAO dao = (HospitalDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            String name = nameTextField.getText();
            String prof = nameTextField.getText();
            int corpus = Integer.parseInt(corpusTextField.getText());
            int ward = Integer.parseInt(wardTextField.getText());
            int f_ward = Integer.parseInt(f_wardTextField.getText());
            Hospital d = new Hospital(((Hospital)ti).getID(), name, prof, corpus, ward, f_ward);
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
            HospitalDAO dao = (HospitalDAO) DAOFactory.createDAO(tf.getTableName(), connection);
            StringBuilder condition = new StringBuilder();
            String s1 = null, s2 = null, s3 = null, s4 = null, s5 = null;
            if (!nameTextField.getText().equals("")) {
                s1 = "name " + nameTextField.getText();
            }
            if (!profTextField.getText().equals("")) {
                s2 = "prof " + profTextField.getText();
            }
            if (!corpusTextField.getText().equals("")) {
                s3 = "corpus " + corpusTextField.getText();
            }
            if (!wardTextField.getText().equals("")) {
                s4 = "ward " + wardTextField.getText();
            }
            if (!f_wardTextField.getText().equals("")) {
                s5 = "f_ward " + f_wardTextField.getText();
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
