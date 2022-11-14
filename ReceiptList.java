import java.util.*;

public class ReceiptList {
    public ReceiptCategory category;
    public ArrayList<Receipt> receiptList;

    public ReceiptList() {
        receiptList = new ArrayList<Receipt>();
        category = ReceiptCategory.PENDING;
    }

    public ReceiptList(ReceiptCategory category) {
        this();
        this.category = category;
    }

    public ReceiptList(ArrayList<Receipt> receiptList) {
        this.receiptList = receiptList;
        this.category = ReceiptCategory.PENDING;
    }

    public ReceiptList(ReceiptCategory category, ArrayList<Receipt> receiptList) {
        this(receiptList);
        this.category = category;
    }

    public void addReceipt(Receipt receipt) {
        receiptList.add(receipt);
    }

    public Receipt removeReceipt(String name) {
        for (Receipt receipt: receiptList) {
            if (receipt.name.equals(name)) {
                receiptList.remove(receipt);
                return receipt;
            }
        }
        throw new NoSuchElementException("Receipt name does not exist in ReceiptList");
    }

    public int getSize() {
        return receiptList.size();
    }
}
