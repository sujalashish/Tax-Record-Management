package ui.tax;

import dao.TaxRecordDAO;
import model.TaxRecord;
import config.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import ui.dashboard.Dashboard;

public class AddTaxRecordForm extends JFrame {

    // COLORS
    private static final Color BG_DARK =
            new Color(45, 45, 45);

    private static final Color FIELD_WHITE =
            Color.WHITE;

    private static final Color TEXT_LABEL =
            new Color(200, 200, 200);

    private static final Color TEXT_DARK =
            new Color(30, 30, 30);

    private static final Color SECTION_TEXT =
            Color.WHITE;

    private static final Color LINE_COLOR =
            new Color(120, 120, 120);

    private static final Color BTN_SAVE =
            new Color(0, 150, 136);

    private static final Color BTN_CLEAR =
            new Color(80, 80, 80);

    // FIELDS
    private JTextField tfTaxpayerName;
    private JTextField tfEmail;
    private JTextField tfMobile;
    private JTextField tfAadhar;
    private JTextField tfPan;
    private JTextField tfAddressLine1;
    private JTextField tfAddressLine2;
    private JTextField tfCity;
    private JTextField tfPinCode;

    private JComboBox<String> cbState;
    private JComboBox<String> cbTaxType;
    private JComboBox<String> cbPaymentStatus;

    private JTextField tfTaxableIncome;
    private JTextField tfTaxAmount;
    private JTextField tfDueDate;
    private JTextField tfFinancialYear;

    private JTextArea taRemarks;

    private JLabel lblStatus;

    private TaxRecordDAO taxRecordDAO;

    // CONSTRUCTOR
    public AddTaxRecordForm() {

        initDAO();

        setTitle("Add Tax Record");

        setSize(1200, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        add(buildScrollableForm(),
                BorderLayout.CENTER);

        add(buildFooter(),
                BorderLayout.SOUTH);
    }

    // INIT DAO
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

    // BUILD SCROLLABLE FORM
    private JScrollPane buildScrollableForm() {

        JPanel content = new JPanel();

        content.setBackground(BG_DARK);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        // TITLE
        JLabel pageTitle =
                new JLabel("ADD TAX RECORD");

        pageTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        pageTitle.setForeground(Color.WHITE);

        pageTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        content.add(pageTitle);

        content.add(vgap(20));

        // SECTION
        content.add(
                sectionHeader(
                        "TAXPAYER INFORMATION"
                )
        );

        content.add(vgap(10));

        tfTaxpayerName = newField();

        tfEmail = newField();

        content.add(
                twoColRow(
                        labeledField(
                                "Taxpayer Full Name",
                                tfTaxpayerName
                        ),
                        labeledField(
                                "Email Address",
                                tfEmail
                        )
                )
        );

        content.add(vgap(10));

        // MOBILE
        tfMobile = newField();

        tfMobile.addKeyListener(
                new KeyAdapter() {

                    public void keyTyped(
                            KeyEvent e
                    ) {

                        char c =
                                e.getKeyChar();

                        if(!Character.isDigit(c)
                                || tfMobile.getText().length() >= 10) {

                            e.consume();
                        }
                    }
                }
        );

        // AADHAR
        tfAadhar = newField();

        tfAadhar.addKeyListener(
                new KeyAdapter() {

                    public void keyTyped(
                            KeyEvent e
                    ) {

                        char c =
                                e.getKeyChar();

                        if(!Character.isDigit(c)
                                || tfAadhar.getText().length() >= 12) {

                            e.consume();
                        }
                    }
                }
        );

        content.add(
                twoColRow(
                        labeledField(
                                "Mobile Number",
                                tfMobile
                        ),
                        labeledField(
                                "Aadhar Number",
                                tfAadhar
                        )
                )
        );

        content.add(vgap(10));

        // PAN
        tfPan = newField();

        tfPan.addKeyListener(
                new KeyAdapter() {

                    public void keyReleased(
                            KeyEvent e
                    ) {

                        tfPan.setText(
                                tfPan.getText()
                                        .toUpperCase()
                        );
                    }
                }
        );

        // FINANCIAL YEAR
        tfFinancialYear =
                newField();

        tfFinancialYear.setText(
                "2025-26"
        );

        content.add(
                twoColRow(
                        labeledField(
                                "PAN Card Number",
                                tfPan
                        ),
                        labeledField(
                                "Financial Year",
                                tfFinancialYear
                        )
                )
        );

        content.add(vgap(20));

        // ADDRESS SECTION
        content.add(
                sectionHeader("ADDRESS")
        );

        content.add(vgap(10));

        tfAddressLine1 = newField();

        content.add(
                fullWidthRow(
                        labeledField(
                                "Address Line 1",
                                tfAddressLine1
                        )
                )
        );

        content.add(vgap(10));

        tfAddressLine2 = newField();

        content.add(
                fullWidthRow(
                        labeledField(
                                "Address Line 2",
                                tfAddressLine2
                        )
                )
        );

        content.add(vgap(10));

        tfCity = newField();

        cbState =
                newComboBox(
                        new String[]{
                                "Andhra Pradesh",
                                "Bihar",
                                "Delhi",
                                "Gujarat",
                                "Jharkhand",
                                "Karnataka",
                                "Maharashtra",
                                "Tamil Nadu",
                                "Uttar Pradesh",
                                "West Bengal"
                        }
                );

        tfPinCode = newField();

        tfPinCode.addKeyListener(
                new KeyAdapter() {

                    public void keyTyped(
                            KeyEvent e
                    ) {

                        char c =
                                e.getKeyChar();

                        if(!Character.isDigit(c)
                                || tfPinCode.getText().length() >= 6) {

                            e.consume();
                        }
                    }
                }
        );

        content.add(
                threeColRow(
                        labeledField(
                                "City",
                                tfCity
                        ),
                        labeledField(
                                "State",
                                cbState
                        ),
                        labeledField(
                                "Pin Code",
                                tfPinCode
                        )
                )
        );

        content.add(vgap(20));

        // TAX DETAILS
        content.add(
                sectionHeader("TAX DETAILS")
        );

        content.add(vgap(10));

        cbTaxType =
                newComboBox(
                        new String[]{
                                "Income Tax",
                                "GST",
                                "Property Tax",
                                "Corporate Tax"
                        }
                );

        tfTaxableIncome =
                newField();

        tfTaxableIncome.addKeyListener(
                new KeyAdapter() {

                    public void keyTyped(
                            KeyEvent e
                    ) {

                        char c =
                                e.getKeyChar();

                        if(!Character.isDigit(c)
                                && c != '.') {

                            e.consume();
                        }
                    }
                }
        );

        content.add(
                twoColRow(
                        labeledField(
                                "Tax Type",
                                cbTaxType
                        ),
                        labeledField(
                                "Taxable Income",
                                tfTaxableIncome
                        )
                )
        );

        content.add(vgap(10));

        tfTaxAmount = newField();

        tfTaxAmount.addKeyListener(
                new KeyAdapter() {

                    public void keyTyped(
                            KeyEvent e
                    ) {

                        char c =
                                e.getKeyChar();

                        if(!Character.isDigit(c)
                                && c != '.') {

                            e.consume();
                        }
                    }
                }
        );

        tfDueDate = newField();

        content.add(
                twoColRow(
                        labeledField(
                                "Tax Amount",
                                tfTaxAmount
                        ),
                        labeledField(
                                "Due Date (DD/MM/YYYY)",
                                tfDueDate
                        )
                )
        );

        content.add(vgap(10));

        cbPaymentStatus =
                newComboBox(
                        new String[]{
                                "Pending",
                                "Paid",
                                "Overdue"
                        }
                );

        JPanel spacer =
                new JPanel();

        spacer.setBackground(BG_DARK);

        content.add(
                twoColRow(
                        labeledField(
                                "Payment Status",
                                cbPaymentStatus
                        ),
                        spacer
                )
        );

        content.add(vgap(20));

        // REMARKS
        content.add(
                sectionHeader("REMARKS")
        );

        content.add(vgap(10));

        taRemarks =
                new JTextArea(4, 20);

        taRemarks.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        JScrollPane remarksScroll =
                new JScrollPane(
                        taRemarks
                );

        remarksScroll.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90
                )
        );

        content.add(remarksScroll);

        content.add(vgap(10));

        lblStatus =
                new JLabel(" ");

        lblStatus.setForeground(Color.WHITE);

        content.add(lblStatus);

        JScrollPane scrollPane =
                new JScrollPane(content);

        scrollPane.getViewport()
                .setBackground(BG_DARK);

        return scrollPane;
    }

    // FOOTER
    private JPanel buildFooter() {

        JPanel footer =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                12
                        )
                );

        footer.setBackground(
                new Color(38,38,38)
        );

        JButton btnBack =
                new JButton("Back");

        btnBack.addActionListener(
                e -> {

                    Dashboard dashboard =
                            new Dashboard();

                    dashboard.setVisible(true);

                    dispose();
                }
        );

        JButton btnClear =
                new JButton("Clear");

        btnClear.addActionListener(
                e -> clearForm()
        );

        JButton btnSave =
                new JButton("Save Record");

        btnSave.addActionListener(
                e -> handleSave()
        );

        footer.add(btnBack);

        footer.add(btnClear);

        footer.add(btnSave);

        return footer;
    }

    // SAVE
    private void handleSave() {

        if(!validateInputs()) {

            return;
        }

        try {

            TaxRecord record =
                    new TaxRecord();

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
                    tfPan.getText()
                            .trim()
                            .toUpperCase()
            );

            record.setFinancialYear(
                    tfFinancialYear
                            .getText()
                            .trim()
            );

            record.setAddressLine1(
                    tfAddressLine1
                            .getText()
                            .trim()
            );

            record.setAddressLine2(
                    tfAddressLine2
                            .getText()
                            .trim()
            );

            record.setCity(
                    tfCity.getText().trim()
            );

            record.setState(
                    (String)
                            cbState.getSelectedItem()
            );

            record.setPinCode(
                    tfPinCode.getText().trim()
            );

            record.setTaxType(
                    (String)
                            cbTaxType.getSelectedItem()
            );

            // IMPORTANT FIX
            record.setTaxableIncome(
                    Double.parseDouble(
                            tfTaxableIncome
                                    .getText()
                                    .trim()
                    )
            );

            // IMPORTANT FIX
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

            record.setPaymentStatus(
                    (String)
                            cbPaymentStatus
                                    .getSelectedItem()
            );

            record.setRemarks(
                    taRemarks.getText().trim()
            );

            boolean saved =
                    taxRecordDAO
                            .addTaxRecord(record);

            if(saved) {

                JOptionPane.showMessageDialog(
                        this,
                        "Record Saved Successfully!"
                );

                int option =
                        JOptionPane
                                .showConfirmDialog(
                                        this,
                                        "Add Another Record?",
                                        "Success",
                                        JOptionPane.YES_NO_OPTION
                                );

                if(option ==
                        JOptionPane.YES_OPTION) {

                    clearForm();

                } else {

                    Dashboard dashboard =
                            new Dashboard();

                    dashboard.setVisible(true);

                    dispose();
                }

            } else {

                showError(
                        "Failed to save record."
                );
            }

        } catch(Exception e) {

            e.printStackTrace();

            showError(
                    "Error: "
                            + e.getMessage()
            );
        }
    }

    // VALIDATION
    private boolean validateInputs() {

        String mobile =
                tfMobile.getText().trim();

        if(!mobile.matches("\\d{10}")) {

            showError(
                    "Mobile must be 10 digits."
            );

            return false;
        }

        String aadhar =
                tfAadhar.getText().trim();

        if(!aadhar.matches("\\d{12}")) {

            showError(
                    "Aadhar must be 12 digits."
            );

            return false;
        }

        String pan =
                tfPan.getText()
                        .trim()
                        .toUpperCase();

        if(!pan.matches(
                "[A-Z]{5}[0-9]{4}[A-Z]"
        )) {

            showError(
                    "Invalid PAN format."
            );

            return false;
        }

        String pin =
                tfPinCode.getText().trim();

        if(!pin.matches("\\d{6}")) {

            showError(
                    "Pin Code must be 6 digits."
            );

            return false;
        }

        String date =
                tfDueDate.getText().trim();

        if(!date.matches(
                "\\d{2}/\\d{2}/\\d{4}"
        )) {

            showError(
                    "Date format: DD/MM/YYYY"
            );

            return false;
        }

        return true;
    }

    // ERROR
    private void showError(String msg) {

        lblStatus.setForeground(Color.RED);

        lblStatus.setText(
                "✘ " + msg
        );
    }

    // CLEAR
    private void clearForm() {

        tfTaxpayerName.setText("");

        tfEmail.setText("");

        tfMobile.setText("");

        tfAadhar.setText("");

        tfPan.setText("");

        tfFinancialYear.setText(
                "2025-26"
        );

        tfAddressLine1.setText("");

        tfAddressLine2.setText("");

        tfCity.setText("");

        tfPinCode.setText("");

        tfTaxableIncome.setText("");

        tfTaxAmount.setText("");

        tfDueDate.setText("");

        taRemarks.setText("");

        lblStatus.setText(" ");
    }

    // HELPERS
    private JPanel sectionHeader(
            String title
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

        JLabel lbl =
                new JLabel(title);

        lbl.setForeground(Color.WHITE);

        lbl.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        panel.add(lbl);

        JSeparator sep =
                new JSeparator();

        panel.add(sep);

        return panel;
    }

    private JPanel labeledField(
            String labelText,
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

        JLabel lbl =
                new JLabel(labelText);

        lbl.setForeground(TEXT_LABEL);

        panel.add(lbl);

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
                                0
                        )
                );

        row.setBackground(BG_DARK);

        row.add(left);

        row.add(right);

        return row;
    }

    private JPanel threeColRow(
            JPanel a,
            JPanel b,
            JPanel c
    ) {

        JPanel row =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        row.setBackground(BG_DARK);

        row.add(a);

        row.add(b);

        row.add(c);

        return row;
    }

    private JPanel fullWidthRow(
            JPanel field
    ) {

        JPanel row =
                new JPanel(
                        new GridLayout(1,1)
                );

        row.setBackground(BG_DARK);

        row.add(field);

        return row;
    }

    private JTextField newField() {

        JTextField tf =
                new JTextField();

        tf.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tf.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        180,
                                        180,
                                        180
                                )
                        ),
                        new EmptyBorder(
                                4,
                                8,
                                4,
                                8
                        )
                )
        );

        return tf;
    }

    private JComboBox<String> newComboBox(
            String[] items
    ) {

        return new JComboBox<>(items);
    }

    private Component vgap(int height) {

        return Box.createRigidArea(
                new Dimension(0, height)
        );
    }
}