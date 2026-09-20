package src.lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int total = 0;

        if (pages <= 10) {
            total = pages * 1500;
        } else {
            total = (10 * 1500) + ((pages - 10) * 1000);
        }

        return total + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
