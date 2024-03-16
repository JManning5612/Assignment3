package Assignment3;
import java.util.Date;

public class Citation 
{
    private Date date;
    private int offenceCode;
    private double amount;
    private String status;
    private Registration registration;

    public Citation(Date date, int offenceCode, double amount, String status, Registration registration) {
        this.date = date;
        this.offenceCode = offenceCode;
        this.amount = amount;
        this.status = status;
        this.registration = registration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOffenceCode() {
        return offenceCode;
    }

    public void setOffenceCode(int offenceCode) {
        this.offenceCode = offenceCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
