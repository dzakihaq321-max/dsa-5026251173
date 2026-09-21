package src.lw01.prelab.Unguided;

public abstract class Rental implements Chargeable{
    private String id;
    private int days;

    protected Rental(String id  , int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be greater than zero.");
        }
        this.id = id;
        this.days = days;


    }
    public String getId() {
        return id;
    }
    public int getdays() {
        return days;
    }
    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be greater than zero.");
        }
        return units * calculateCharge();
    }
    public String label() {
        return "Print";
    }
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }

}
