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
import java.awt.event.*;
import java.sql.Connection;

public class UpdateTaxRecordForm extends JFrame {

    // COLORS
    private static final Color BG_DARK =
            new Color(45,45,45);

    private static final Color FIELD_WHITE =
            Color.WHITE;

    private static final Color TEXT_LABEL =
            new Color(200,200,200);

    private static final Color TEXT_DARK =
            new Color(30,30,30);

    // COMPONENTS
    private JTextField tfSearchId;

    private JTextField tfTaxpayerName;
    private JTextField tfEmail;
    private JTextField tfMobile;
    private JTextField tfAadhar;
    private JTextField tfPan;
    private JTextField tfFinancialYear;
private JTextField tfAddress1;
private JTextField tfAddress2;
    private JTextField tfCity;
    private JTextField tfPinCode;
    private JTextField tfTaxAmount;
    private JTextField tfDueDate;

    private JComboBox<String> cbState;
    private JComboBox<String> cbTaxType;
    private JComboBox<String> cbPaymentStatus;

    private JTextArea taRemarks;

    private TaxRecordDAO taxRecordDAO;

    // CONSTRUCTOR
    public UpdateTaxRecordForm() {

        initDAO();

        setTitle("Update Tax Record");

        setSize(1000,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        add(buildForm(),BorderLayout.CENTER);

        add(buildFooter(),BorderLayout.SOUTH);
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

    // FORM
    private JScrollPane buildForm() {

        JPanel panel = new JPanel();

        panel.setBackground(BG_DARK);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(20,30,20,30)
        );

        JLabel title = new JLabel(
                "UPDATE TAX RECORD"
        );

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
                new Dimension(0,20)
        ));

        // SEARCH SECTION
        tfSearchId = newField();

        JButton btnLoad =
                new JButton("Load Record");

        btnLoad.addActionListener(e -> {

            loadRecord();
        });

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        searchPanel.setBackground(BG_DARK);

        searchPanel.add(
                labeledField(
                        "Enter Record ID",
                        tfSearchId
                )
        );

        searchPanel.add(btnLoad);

        panel.add(searchPanel);

        // FIELDS
        tfTaxpayerName = newField();
        tfEmail = newField();
        tfMobile = newField();
        tfAadhar = newField();
        tfPan = newField();
        tfCity = newField();
        tfPinCode = newField();
        tfTaxAmount = newField();
        tfDueDate = newField();
        tfFinancialYear = newField();
tfAddress1 = newField();
tfAddress2 = newField();

        cbState = new JComboBox<>(new String[] {

                "Bihar",
                "Uttar Pradesh",
                "Delhi",
                "Maharashtra",
                "Other"
        });

        cbTaxType = new JComboBox<>(new String[] {

                "Income Tax",
                "GST",
                "Property Tax",
                "Other"
        });

        cbPaymentStatus =
                new JComboBox<>(new String[] {

                        "Pending",
                        "Paid",
                        "Overdue",
                        "Partially Paid"
                });

        taRemarks = new JTextArea(4,20);

        panel.add(twoColRow(
                labeledField(
                        "Taxpayer Name",
                        tfTaxpayerName
                ),
                labeledField(
                        "Email",
                        tfEmail
                )
        ));

        panel.add(twoColRow(
                labeledField(
                        "Mobile",
                        tfMobile
                ),
                labeledField(
                        "Aadhar",
                        tfAadhar
                )
        ));

        panel.add(twoColRow(
                labeledField(
                        "PAN",
                        tfPan
                ),
                labeledField(
                        "City",
                        tfCity
                )
        ));

        panel.add(twoColRow(
        labeledField(
                "Financial Year",
                tfFinancialYear
        ),
        labeledField(
                "Address Line 1",
                tfAddress1
        )
));

panel.add(
        labeledField(
                "Address Line 2",
                tfAddress2
        )
);

        panel.add(twoColRow(
                labeledField(
                        "Pin Code",
                        tfPinCode
                ),
                labeledField(
                        "Tax Amount",
                        tfTaxAmount
                )
        ));

        panel.add(twoColRow(
                labeledField(
                        "State",
                        cbState
                ),
                labeledField(
                        "Tax Type",
                        cbTaxType
                )
        ));

        panel.add(twoColRow(
                labeledField(
                        "Due Date",
                        tfDueDate
                ),
                labeledField(
                        "Payment Status",
                        cbPaymentStatus
                )
        ));

        JScrollPane areaScroll =
                new JScrollPane(taRemarks);

        panel.add(areaScroll);

        return new JScrollPane(panel);
    }

    // FOOTER
    private JPanel buildFooter() {

        JPanel footer = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        15,
                        10
                )
        );

        footer.setBackground(BG_DARK);

        JButton btnBack =
                new JButton("Back");

        JButton btnClear =
                new JButton("Clear");

        JButton btnUpdate =
                new JButton("Update");

        // BACK
        btnBack.addActionListener(e -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.setVisible(true);

            dispose();
        });

        // CLEAR
        btnClear.addActionListener(e -> {

            clearForm();
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            handleUpdate();
        });

        footer.add(btnBack);
        footer.add(btnClear);
        footer.add(btnUpdate);

        return footer;
    }

    // LOAD RECORD
    private void loadRecord() {

        try {

            int id =
                    Integer.parseInt(
                            tfSearchId
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

            tfTaxpayerName.setText(
                    record.getTaxpayerName()
            );

            tfEmail.setText(
                    record.getEmail()
            );

            tfMobile.setText(
                    record.getMobile()
            );

            tfAadhar.setText(
                    record.getAadhar()
            );

            tfPan.setText(
                    record.getPan()
            );
            
            tfFinancialYear.setText(
        record.getFinancialYear()
);

tfAddress1.setText(
        record.getAddressLine1()
);

tfAddress2.setText(
        record.getAddressLine2()
);

            tfCity.setText(
                    record.getCity()
            );

            tfPinCode.setText(
                    record.getPinCode()
            );

            tfTaxAmount.setText(
                    String.valueOf(
                            record.getTaxAmount()
                    )
            );

            tfDueDate.setText(
                    record.getDueDate()
            );

            cbState.setSelectedItem(
                    record.getState()
            );

            cbTaxType.setSelectedItem(
                    record.getTaxType()
            );

            cbPaymentStatus.setSelectedItem(
                    record.getPaymentStatus()
            );

            taRemarks.setText(
                    record.getRemarks()
            );

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // UPDATE
    private void handleUpdate() {

        try {

            TaxRecord record =
                    new TaxRecord();

            record.setId(
                    Integer.parseInt(
                            tfSearchId
                                    .getText()
                                    .trim()
                    )
            );

            record.setTaxpayerName(
                    tfTaxpayerName
                            .getText()
                            .trim()
            );

            record.setEmail(
                    tfEmail.getText().trim()
            );

            record.setMobile(
                    tfMobile.getText().trim()
            );

            record.setAadhar(
                    tfAadhar.getText().trim()
            );

            record.setPan(
                    tfPan.getText().trim()
            );
            
                        record.setFinancialYear(
        tfFinancialYear.getText().trim()
);

record.setAddressLine1(
        tfAddress1.getText().trim()
);

record.setAddressLine2(
        tfAddress2.getText().trim()
);

            record.setCity(
                    tfCity.getText().trim()
            );
            


            record.setPinCode(
                    tfPinCode.getText().trim()
            );

            record.setTaxAmount(
                    Double.parseDouble(
                            tfTaxAmount
                                    .getText()
                                    .trim()
                    )
            );

            record.setDueDate(
                    tfDueDate.getText().trim()
            );

            record.setState(
                    cbState
                            .getSelectedItem()
                            .toString()
            );

            record.setTaxType(
                    cbTaxType
                            .getSelectedItem()
                            .toString()
            );

            record.setPaymentStatus(
                    cbPaymentStatus
                            .getSelectedItem()
                            .toString()
            );

            record.setRemarks(
                    taRemarks.getText().trim()
            );

            boolean updated =
                    taxRecordDAO
                            .updateTaxRecord(record);

            if(updated) {

                JOptionPane.showMessageDialog(
                        this,
                        "Record Updated Successfully"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Update Failed"
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    // CLEAR
    private void clearForm() {

        tfTaxpayerName.setText("");
        tfEmail.setText("");
        tfMobile.setText("");
        tfAadhar.setText("");
        tfPan.setText("");
        tfFinancialYear.setText("");
tfAddress1.setText("");
tfAddress2.setText("");
        tfCity.setText("");
        tfPinCode.setText("");
        tfTaxAmount.setText("");
        tfDueDate.setText("");
        taRemarks.setText("");
    }

    // HELPERS
    private JTextField newField() {

        JTextField tf =
                new JTextField();

        tf.setPreferredSize(
                new Dimension(250,30)
        );

        return tf;
    }

    private JPanel labeledField(
            String text,
            JComponent field
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(BG_DARK);

        JLabel label =
                new JLabel(text);

        label.setForeground(TEXT_LABEL);

        panel.add(label);

        panel.add(field);

        return panel;
    }

    private JPanel twoColRow(
            JPanel left,
            JPanel right
    ) {

        JPanel row =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                10
                        )
                );

        row.setBackground(BG_DARK);

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );

        row.add(left);
        row.add(right);

        return row;
    }

    // MAIN
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new UpdateTaxRecordForm()
                    .setVisible(true);
        });
    }
}