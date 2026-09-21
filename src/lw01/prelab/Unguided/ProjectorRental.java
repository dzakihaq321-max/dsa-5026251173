package src.lw01.prelab.Unguided;

public class ProjectorRental extends Rental{

    public ProjectorRental(String id, int days) {
        super(id, days);
    }
    @Override
    public int calculateCharge() {
        int days = getdays();
        int total = 0;

        if (days <= 3) {
            total = days * 60000;
        } else {
            total = (3 * 60000) + ((days - 3) * 45000);
        }

        return total + 20000;
    }
    @Override
    public String label() {
        return "Projector";
}
}
