/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class TaxRecord {

    private int    id;
    private String taxpayerName;
    private String email;
    private String mobile;
    private String aadhar;
    private String pan;
    private String financialYear;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pinCode;
    private String taxType;
    private double taxableIncome;
    private double taxAmount;
    private String dueDate;
    private String paymentStatus;
    private String remarks;

    // ── Constructors ──────────────────────────────────────────────────────────

    public TaxRecord() {}

    public TaxRecord(int id, String taxpayerName, String email, String mobile,
                     String aadhar, String pan, String financialYear,
                     String addressLine1, String addressLine2, String city,
                     String state, String pinCode, String taxType,
                     double taxableIncome, double taxAmount, String dueDate,
                     String paymentStatus, String remarks) {
        this.id             = id;
        this.taxpayerName   = taxpayerName;
        this.email          = email;
        this.mobile         = mobile;
        this.aadhar         = aadhar;
        this.pan            = pan;
        this.financialYear  = financialYear;
        this.addressLine1   = addressLine1;
        this.addressLine2   = addressLine2;
        this.city           = city;
        this.state          = state;
        this.pinCode        = pinCode;
        this.taxType        = taxType;
        this.taxableIncome  = taxableIncome;
        this.taxAmount      = taxAmount;
        this.dueDate        = dueDate;
        this.paymentStatus  = paymentStatus;
        this.remarks        = remarks;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTaxpayerName() { return taxpayerName; }
    public void setTaxpayerName(String taxpayerName) { this.taxpayerName = taxpayerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAadhar() { return aadhar; }
    public void setAadhar(String aadhar) { this.aadhar = aadhar; }

    public String getPan() { return pan; }
    public void setPan(String pan) { this.pan = pan; }

    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }

    public String getAddressLine1() { return addressLine1; }
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    public String getAddressLine2() { return addressLine2; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getTaxType() { return taxType; }
    public void setTaxType(String taxType) { this.taxType = taxType; }

    public double getTaxableIncome() { return taxableIncome; }
    public void setTaxableIncome(double taxableIncome) { this.taxableIncome = taxableIncome; }

    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    @Override
    public String toString() {
        return "TaxRecord{id=" + id + ", name=" + taxpayerName +
               ", pan=" + pan + ", taxType=" + taxType +
               ", taxAmount=" + taxAmount + ", status=" + paymentStatus + "}";
    }
}
