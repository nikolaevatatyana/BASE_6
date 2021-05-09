package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class QueryFrame extends JFrame implements ActionListener {
    private final Container container = getContentPane();
    private final JLabel titleLabel = new JLabel("INFORMATION SYSTEM OF MEDICAL ORGANIZATIONS");
    private final JLabel queryNameLabel = new JLabel();
    private final JList<String> resultsJList = new JList<>();
    private final JScrollPane scrollPane = new JScrollPane(resultsJList);
    private final JButton backButton = new JButton("Back");
    private final MainQueriesFrame mqf;
    private final QueryExecutor qe;

    public QueryFrame(MainQueriesFrame mqf, Connection connection) {
        qe = new QueryExecutor(mqf, this, connection);
        this.mqf = mqf;
        container.setLayout(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setLocationAndSize();
        addActionEvent();
        addComponentsToContainer();
        setTitle("DIS :: Query Frame");
        setBounds(10,10,300,420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    private void setLocationAndSize() {
        titleLabel.setBounds(10, 10, 260, 30);
        queryNameLabel.setBounds(10, 50, 260, 30);
        scrollPane.setBounds(10, 90, 260, 220);
        backButton.setBounds(10, 330, 260, 30);
    }

    private void addActionEvent() {
        backButton.addActionListener(this);
    }

    private void addComponentsToContainer() {
        container.add(titleLabel);
        container.add(queryNameLabel);
        container.add(scrollPane);
        container.add(backButton);
    }

    public void executeQuery(String queryName, String parameter) {
        setVisible(true);
        queryNameLabel.setText("QUERY: " + queryName);
        try {
            String[] results = qe.execute(queryName, parameter);
            resultsJList.setListData(results);
        } catch (Exception e) {
            resultsJList.setListData(new String[0]);
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this, "Could not execute query!",
                    "Error!", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            mqf.setVisible(true);
        }
    }
}
