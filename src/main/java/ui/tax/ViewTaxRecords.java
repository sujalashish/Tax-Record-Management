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
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ViewTaxRecords extends JFrame {

    // COLORS
    private static final Color BG_DARK = new Color(45,45,45);
    private static final Color PANEL_BG = new Color(60,60,60);
    private static final Color TEXT_WHITE = Color.WHITE;

    // COMPONENTS
    private JTable table;
    private DefaultTableModel model;

    private JTextField tfSearchPan;
    private JComboBox<String> cbStatus;

    private TaxRecordDAO taxRecordDAO;

    // CONSTRUCTOR
    public ViewTaxRecords() {

        initDAO();

        setTitle("View Tax Records");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
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

    // TOP PANEL
    private JPanel buildTopPanel() {

        JPanel top = new JPanel();
        top.setBackground(BG_DARK);
        top.setLayout(new FlowLayout(FlowLayout.LEFT,15,15));

        JLabel title = new JLabel("VIEW TAX RECORDS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        tfSearchPan = new JTextField(15);

        JButton btnSearch =
                new JButton("Search PAN");

        cbStatus = new JComboBox<>(new String[] {

                "All",
                "Pending",
                "Paid",
                "Overdue",
                "Partially Paid"
        });

        JButton btnFilter =
                new JButton("Filter");

        JButton btnRefresh =
                new JButton("Refresh");

        // SEARCH ACTION
        btnSearch.addActionListener(e -> {

            searchByPan();
        });

        // FILTER ACTION
        btnFilter.addActionListener(e -> {

            filterByStatus();
        });

        // REFRESH ACTION
        btnRefresh.addActionListener(e -> {

            loadAllRecords();
        });

        top.add(title);
        top.add(Box.createHorizontalStrut(30));
        top.add(tfSearchPan);
        top.add(btnSearch);
        top.add(cbStatus);
        top.add(btnFilter);
        top.add(btnRefresh);

        return top;
    }

    // TABLE PANEL
    private JScrollPane buildTablePanel() {

        String[] columns = {

                "ID",
                "Name",
                "PAN",
                "Mobile",
                "Tax Type",
                "Tax Amount",
                "Payment Status",
                "Due Date"
        };

        model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        table.setRowHeight(28);

        table.setFont(new Font("Segoe UI",
                Font.PLAIN,13));

        table.getTableHeader().setFont(
                new Font("Segoe UI",
                        Font.BOLD,13)
        );

        JScrollPane scroll =
                new JScrollPane(table);

        loadAllRecords();

        return scroll;
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

        btnBack.setPreferredSize(
                new Dimension(100,35)
        );

        btnBack.addActionListener(e -> {

            Dashboard dashboard =
                    new Dashboard();

            dashboard.setVisible(true);

            dispose();
        });

        footer.add(btnBack);

        return footer;
    }

    // LOAD ALL RECORDS
    private void loadAllRecords() {

        model.setRowCount(0);

        List<TaxRecord> list =
                taxRecordDAO.getAllTaxRecords();

        for(TaxRecord r : list) {

            model.addRow(new Object[] {

                    r.getId(),
                    r.getTaxpayerName(),
                    r.getPan(),
                    r.getMobile(),
                    r.getTaxType(),
                    r.getTaxAmount(),
                    r.getPaymentStatus(),
                    r.getDueDate()
            });
        }
    }

    // SEARCH BY PAN
    private void searchByPan() {

        String pan =
                tfSearchPan.getText()
                        .trim()
                        .toUpperCase();

        model.setRowCount(0);

        List<TaxRecord> list =
                taxRecordDAO.getTaxRecordsByPan(pan);
        for(TaxRecord r : list) {

            model.addRow(new Object[] {

                    r.getId(),
                    r.getTaxpayerName(),
                    r.getPan(),
                    r.getMobile(),
                    r.getTaxType(),
                    r.getTaxAmount(),
                    r.getPaymentStatus(),
                    r.getDueDate()
            });
        }
    }
    // FILTER STATUS
    private void filterByStatus() {

        String status =
                cbStatus.getSelectedItem()
                        .toString();

        if(status.equals("All")) {

            loadAllRecords();
            return;
        }
        model.setRowCount(0);

        List<TaxRecord> list =
                taxRecordDAO.getTaxRecordsByStatus(status);

        for(TaxRecord r : list) {

            model.addRow(new Object[] {

                    r.getId(),
                    r.getTaxpayerName(),
                    r.getPan(),
                    r.getMobile(),
                    r.getTaxType(),
                    r.getTaxAmount(),
                    r.getPaymentStatus(),
                    r.getDueDate()
            });
        }
    }

    // MAIN
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ViewTaxRecords()
                    .setVisible(true);
        });
    }
}