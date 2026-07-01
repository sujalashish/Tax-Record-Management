/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.tax;

import dao.TaxRecordDAO;
import model.TaxRecord;
import config.DBConnection;
import ui.dashboard.Dashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.Connection;

public class DeleteTaxRecord extends JFrame {

    // COMPONENTS
    private JTextField tfRecordId;

    private JLabel lblName;
    private JLabel lblPan;
    private JLabel lblAmount;
    private JLabel lblStatus;

    private TaxRecordDAO taxRecordDAO;

    // CONSTRUCTOR
    public DeleteTaxRecord() {

        initDAO();

        setTitle("Delete Tax Record");

        setSize(700,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        add(buildMainPanel(),
                BorderLayout.CENTER);

        add(buildFooter(),
                BorderLayout.SOUTH);
    }

    // DAO
    private void initDAO() {

        try {

            Connection conn =
                    DBConnection.getConnection();

            taxRecordDAO =
                    new TaxRecordDAO(conn);

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // MAIN PANEL
    private JPanel buildMainPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(30,30,30,30)
        );

        panel.setBackground(
                new Color(45,45,45)
        );

        JLabel title =
                new JLabel("DELETE TAX RECORD");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        panel.add(title);

        panel.add(Box.createRigidArea(
                new Dimension(0,30)
        ));

        // SEARCH
        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        searchPanel.setBackground(
                new Color(45,45,45)
        );

        JLabel lblId =
                new JLabel("Record ID:");

        lblId.setForeground(Color.WHITE);

        tfRecordId =
                new JTextField(15);

        JButton btnLoad =
                new JButton("Load Record");

        btnLoad.addActionListener(e -> {

            loadRecord();
        });

        searchPanel.add(lblId);
        searchPanel.add(tfRecordId);
        searchPanel.add(btnLoad);

        panel.add(searchPanel);

        panel.add(Box.createRigidArea(
                new Dimension(0,30)
        ));

        // DETAILS
        lblName =
                new JLabel("Name: ");

        lblPan =
                new JLabel("PAN: ");

        lblAmount =
                new JLabel("Amount: ");

        lblStatus =
                new JLabel("Payment Status: ");

        setLabelStyle(lblName);
        setLabelStyle(lblPan);
        setLabelStyle(lblAmount);
        setLabelStyle(lblStatus);

        panel.add(lblName);
        panel.add(Box.createRigidArea(
                new Dimension(0,15)
        ));

        panel.add(lblPan);
        panel.add(Box.createRigidArea(
                new Dimension(0,15)
        ));

        panel.add(lblAmount);
        panel.add(Box.createRigidArea(
                new Dimension(0,15)
        ));

        panel.add(lblStatus);

        return panel;
    }

    // FOOTER
    private JPanel buildFooter() {

        JPanel footer =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                10
                        )
                );

        footer.setBackground(
                new Color(45,45,45)
        );

        JButton btnBack =
                new JButton("Back");

        JButton btnDelete =
                new JButton("Delete");

        // BACK
        btnBack.addActionListener(e -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.setVisible(true);

            dispose();
        });

        // DELETE
        btnDelete.addActionListener(e -> {

            deleteRecord();
        });

        footer.add(btnBack);
        footer.add(btnDelete);

        return footer;
    }

    // LOAD RECORD
    private void loadRecord() {

        try {

            int id =
                    Integer.parseInt(
                            tfRecordId
                                    .getText()
                                    .trim()
                    );

            TaxRecord record =
                    taxRecordDAO
                            .getTaxRecordById(id);

            if(record == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Record Not Found"
                );

                return;
            }

            lblName.setText(
                    "Name: "
                            + record.getTaxpayerName()
            );

            lblPan.setText(
                    "PAN: "
                            + record.getPan()
            );

            lblAmount.setText(
                    "Amount: ₹"
                            + record.getTaxAmount()
            );

            lblStatus.setText(
                    "Payment Status: "
                            + record.getPaymentStatus()
            );

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // DELETE
    private void deleteRecord() {

        try {

            int id =
                    Integer.parseInt(
                            tfRecordId
                                    .getText()
                                    .trim()
                    );

            int confirm =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Are you sure you want to delete this record?",

                            "Confirm Delete",

                            JOptionPane.YES_NO_OPTION
                    );

            if(confirm != JOptionPane.YES_OPTION) {

                return;
            }

            boolean deleted =
                    taxRecordDAO
                            .deleteTaxRecord(id);

            if(deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Record Deleted Successfully"
                );

                clearData();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Delete Failed"
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // CLEAR
    private void clearData() {

        tfRecordId.setText("");

        lblName.setText("Name: ");

        lblPan.setText("PAN: ");

        lblAmount.setText("Amount: ");

        lblStatus.setText("Payment Status: ");
    }

    // LABEL STYLE
    private void setLabelStyle(JLabel label) {

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );
    }

    // MAIN
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new DeleteTaxRecord()
                    .setVisible(true);
        });
    }
}
