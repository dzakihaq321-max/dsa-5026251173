package src.lw01.prelab.Unguided;

public class LaptopRental extends Rental {

    public LaptopRental(String id, int days) {
        super(id, days);
    
}
@Override
    public int calculateCharge() {
        return getdays() * 40000;
}
@Override
    public String label() {
        return "Laptop";
}
}