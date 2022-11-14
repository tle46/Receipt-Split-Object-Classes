import java.util.ArrayList;

public class Person {
    private String name;
    public ArrayList<Receipt> receiptList;
    public double total;

    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        if (this.name.equals(((Person)other).name)) {
            return true;
        }
        return false;
    }
}
