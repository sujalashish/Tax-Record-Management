package dao;

import model.TaxRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxRecordDAO {

    private Connection connection;

    // CONSTRUCTOR
    public TaxRecordDAO(Connection connection) {

        this.connection = connection;
    }

    // ADD RECORD
    public boolean addTaxRecord(TaxRecord record) {

        boolean inserted = false;

        try {

            String sql =
                    "INSERT INTO tax_records ("
                    + "taxpayer_name,"
                    + "email,"
                    + "mobile,"
                    + "aadhar,"
                    + "pan,"
                    + "financial_year,"
                    + "address_line1,"
                    + "address_line2,"
                    + "city,"
                    + "state,"
                    + "pin_code,"
                    + "tax_type,"
                    + "taxable_income,"
                    + "tax_amount,"
                    + "due_date,"
                    + "payment_status,"
                    + "remarks"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1,
                    record.getTaxpayerName());

            ps.setString(2,
                    record.getEmail());

            ps.setString(3,
                    record.getMobile());

            ps.setString(4,
                    record.getAadhar());

            ps.setString(5,
                    record.getPan());

            ps.setString(6,
                    record.getFinancialYear());

            ps.setString(7,
                    record.getAddressLine1());

            ps.setString(8,
                    record.getAddressLine2());

            ps.setString(9,
                    record.getCity());

            ps.setString(10,
                    record.getState());

            ps.setString(11,
                    record.getPinCode());

            ps.setString(12,
                    record.getTaxType());

            ps.setDouble(13,
                    record.getTaxableIncome());

            ps.setDouble(14,
                    record.getTaxAmount());

            ps.setString(15,
                    record.getDueDate());

            ps.setString(16,
                    record.getPaymentStatus());

            ps.setString(17,
                    record.getRemarks());

            int rows =
                    ps.executeUpdate();

            inserted = rows > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return inserted;
    }

    // GET ALL RECORDS
    public List<TaxRecord> getAllTaxRecords() {

        List<TaxRecord> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM tax_records";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                TaxRecord r =
                        new TaxRecord();

                r.setId(
                        rs.getInt("id"));

                r.setTaxpayerName(
                        rs.getString("taxpayer_name"));

                r.setEmail(
                        rs.getString("email"));

                r.setMobile(
                        rs.getString("mobile"));

                r.setAadhar(
                        rs.getString("aadhar"));

                r.setPan(
                        rs.getString("pan"));

                r.setFinancialYear(
                        rs.getString("financial_year"));

                r.setAddressLine1(
                        rs.getString("address_line1"));

                r.setAddressLine2(
                        rs.getString("address_line2"));

                r.setCity(
                        rs.getString("city"));

                r.setState(
                        rs.getString("state"));

                r.setPinCode(
                        rs.getString("pin_code"));

                r.setTaxType(
                        rs.getString("tax_type"));

                r.setTaxableIncome(
                        rs.getDouble("taxable_income"));

                r.setTaxAmount(
                        rs.getDouble("tax_amount"));

                r.setDueDate(
                        rs.getString("due_date"));

                r.setPaymentStatus(
                        rs.getString("payment_status"));

                r.setRemarks(
                        rs.getString("remarks"));

                list.add(r);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // GET RECORD BY ID
    public TaxRecord getTaxRecordById(int id) {

        TaxRecord record = null;

        try {

            String sql =
                    "SELECT * FROM tax_records WHERE id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                record = new TaxRecord();

                record.setId(
                        rs.getInt("id"));

                record.setTaxpayerName(
                        rs.getString("taxpayer_name"));

                record.setEmail(
                        rs.getString("email"));

                record.setMobile(
                        rs.getString("mobile"));

                record.setAadhar(
                        rs.getString("aadhar"));

                record.setPan(
                        rs.getString("pan"));

                record.setFinancialYear(
                        rs.getString("financial_year"));

                record.setAddressLine1(
                        rs.getString("address_line1"));

                record.setAddressLine2(
                        rs.getString("address_line2"));

                record.setCity(
                        rs.getString("city"));

                record.setState(
                        rs.getString("state"));

                record.setPinCode(
                        rs.getString("pin_code"));

                record.setTaxType(
                        rs.getString("tax_type"));

                record.setTaxableIncome(
                        rs.getDouble("taxable_income"));

                record.setTaxAmount(
                        rs.getDouble("tax_amount"));

                record.setDueDate(
                        rs.getString("due_date"));

                record.setPaymentStatus(
                        rs.getString("payment_status"));

                record.setRemarks(
                        rs.getString("remarks"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return record;
    }

    // UPDATE RECORD
    public boolean updateTaxRecord(
            TaxRecord record
    ) {

        boolean updated = false;

        try {

            String sql =
                    "UPDATE tax_records SET "
                    + "taxpayer_name=?, "
                    + "email=?, "
                    + "mobile=?, "
                    + "aadhar=?, "
                    + "pan=?, "
                    + "financial_year=?, "
                    + "address_line1=?, "
                    + "address_line2=?, "
                    + "city=?, "
                    + "state=?, "
                    + "pin_code=?, "
                    + "tax_type=?, "
                    + "taxable_income=?, "
                    + "tax_amount=?, "
                    + "due_date=?, "
                    + "payment_status=?, "
                    + "remarks=? "
                    + "WHERE id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1,
                    record.getTaxpayerName());

            ps.setString(2,
                    record.getEmail());

            ps.setString(3,
                    record.getMobile());

            ps.setString(4,
                    record.getAadhar());

            ps.setString(5,
                    record.getPan());

            ps.setString(6,
                    record.getFinancialYear());

            ps.setString(7,
                    record.getAddressLine1());

            ps.setString(8,
                    record.getAddressLine2());

            ps.setString(9,
                    record.getCity());

            ps.setString(10,
                    record.getState());

            ps.setString(11,
                    record.getPinCode());

            ps.setString(12,
                    record.getTaxType());

            ps.setDouble(13,
                    record.getTaxableIncome());

            ps.setDouble(14,
                    record.getTaxAmount());

            ps.setString(15,
                    record.getDueDate());

            ps.setString(16,
                    record.getPaymentStatus());

            ps.setString(17,
                    record.getRemarks());

            ps.setInt(18,
                    record.getId());

            int rows =
                    ps.executeUpdate();

            updated = rows > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return updated;
    }

    // DELETE RECORD
    public boolean deleteTaxRecord(int id) {

        boolean deleted = false;

        try {

            String sql =
                    "DELETE FROM tax_records WHERE id=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, id);

            int rows =
                    ps.executeUpdate();

            deleted = rows > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return deleted;
    }

    // TOTAL RECORDS
    public int getTotalRecordCount() {

        int total = 0;

        try {

            String sql =
                    "SELECT COUNT(*) FROM tax_records";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // TOTAL TAX
    public double getTotalTaxCollected() {

        double total = 0;

        try {

            String sql =
                    "SELECT SUM(tax_amount) FROM tax_records";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total = rs.getDouble(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // PENDING PAYMENTS
    public int getPendingPaymentCount() {

        int total = 0;

        try {

            String sql =
                    "SELECT COUNT(*) FROM tax_records "
                    + "WHERE payment_status='Pending'";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // UNPAID RECORDS
    public int getUnpaidRecordCount() {

        int total = 0;

        try {

            String sql =
                    "SELECT COUNT(*) FROM tax_records "
                    + "WHERE payment_status='Overdue'";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total = rs.getInt(1);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }

    // SEARCH BY PAN
    public List<TaxRecord> getTaxRecordsByPan(
            String pan
    ) {

        List<TaxRecord> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM tax_records "
                    + "WHERE pan LIKE ?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1,
                    "%" + pan + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                TaxRecord r =
                        new TaxRecord();

                r.setId(
                        rs.getInt("id"));

                r.setTaxpayerName(
                        rs.getString("taxpayer_name"));

                r.setPan(
                        rs.getString("pan"));

                r.setMobile(
                        rs.getString("mobile"));

                r.setTaxType(
                        rs.getString("tax_type"));

                r.setTaxAmount(
                        rs.getDouble("tax_amount"));

                r.setPaymentStatus(
                        rs.getString("payment_status"));

                r.setDueDate(
                        rs.getString("due_date"));

                list.add(r);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // FILTER BY STATUS
    public List<TaxRecord> getTaxRecordsByStatus(
            String status
    ) {

        List<TaxRecord> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM tax_records "
                    + "WHERE payment_status=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, status);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                TaxRecord r =
                        new TaxRecord();

                r.setId(
                        rs.getInt("id"));

                r.setTaxpayerName(
                        rs.getString("taxpayer_name"));

                r.setPan(
                        rs.getString("pan"));

                r.setMobile(
                        rs.getString("mobile"));

                r.setTaxType(
                        rs.getString("tax_type"));

                r.setTaxAmount(
                        rs.getDouble("tax_amount"));

                r.setPaymentStatus(
                        rs.getString("payment_status"));

                r.setDueDate(
                        rs.getString("due_date"));

                list.add(r);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}