/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.tax;

import dao.TaxRecordDAO;
import model.TaxRecord;
import config.DBConnection;

import javax.swing.*;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.List;

public class ExportData {

    public static void exportToCSV() {

        try {

            Connection conn =
                    DBConnection.getConnection();

            TaxRecordDAO dao =
                    new TaxRecordDAO(conn);

            List<TaxRecord> list =
                    dao.getAllTaxRecords();

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setDialogTitle(
                    "Save CSV File"
            );

            int result =
                    chooser.showSaveDialog(null);

            if(result != JFileChooser.APPROVE_OPTION) {

                return;
            }

            String path =
                    chooser.getSelectedFile()
                            .getAbsolutePath();

            if(!path.endsWith(".csv")) {

                path += ".csv";
            }

            FileWriter writer =
                    new FileWriter(path);

            // HEADER
            writer.append(
                    "ID,Name,PAN,Mobile,Tax Type,Tax Amount,Payment Status,Due Date\n"
            );

            // DATA
            for(TaxRecord r : list) {

                writer.append(
                        r.getId() + ","
                        + r.getTaxpayerName() + ","
                        + r.getPan() + ","
                        + r.getMobile() + ","
                        + r.getTaxType() + ","
                        + r.getTaxAmount() + ","
                        + r.getPaymentStatus() + ","
                        + r.getDueDate()
                        + "\n"
                );
            }

            writer.flush();

            writer.close();

            JOptionPane.showMessageDialog(
                    null,
                    "CSV Exported Successfully!"
            );

        } catch(Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Export Failed!"
            );
        }
    }
}
