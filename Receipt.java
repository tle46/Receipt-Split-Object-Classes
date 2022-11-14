import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Receipt {
    public String name;
    public ReceiptCategory receiptCategory;
    public Boolean isComplete;
    public double taxAndFees;
    public double total;
    public ArrayList<Item> itemList;
    public ArrayList<Person> personList;
    public HashMap<Person, Double> billMap;

    //ITEM AND PERSON OBJECT IDENTIFIERS ARE THEIR NAME STRINGS. DUPLICATE NAMES ARE NOT ALLOWED.

    public Receipt(String name) {
        itemList = new ArrayList<Item>();
        personList = new ArrayList<Person>();
        this.name = name;
        receiptCategory = ReceiptCategory.PENDING;
    }

    public Receipt() {
        this("noName");
    }

    public Item getItem(Item item){
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).name.equals(item.name)) {
                return itemList.get(i);
            }
        }
        throw new NoSuchElementException("There is no such item in itemList");
    }

    public double getTotal() {
        double sum = 0;
        for (Item item: itemList) {
            sum += item.price;
        }
        double scale = Math.pow(10, 2);
        return Math.round(sum * scale) / scale;
    }

    public int getSize() {
        return itemList.size();
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Items: ");
        for (Item item: itemList) {
            System.out.println(item.name + " - " + item.price);
        }
        System.out.println("Subtotal ----- " + getTotal());
        System.out.println("Taxes and fees ----- " + taxAndFees);
        System.out.println("Total ----- " + total);
    }

    public void additem(Item item) {
        HashMap<Person, Boolean> newMap = new HashMap<Person, Boolean>();
        for (Person person: personList) {
            newMap.put(person, false);
        }
        item.billMap = newMap;
        boolean isDup = false;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).name.equals(item.name)) {
                isDup = true;
                itemList.get(i).quantity = itemList.get(i).quantity + 1;
                break;
            }
        }
        if (!isDup) {
            itemList.add(item);
        }
    }

    public Item removeItem(Item item) {
        Boolean didRemove = itemList.remove(item);
        if (didRemove) {
            return item;
        }
        throw new NoSuchElementException("No such item in itemList");
    }

    public void addPerson(Person person) {
        personList.add(person);
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).billMap.put(person, false);
        }
        billMap.put(person, 0.0);
    }

    public Person removePerson(Person person) {
        
        Boolean didRemove = personList.remove(person);
        billMap.remove(person);
        recalculateList();
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).billMap.remove(person);
        }
        if (didRemove) {
            return person;
        }
        throw new NoSuchElementException("No such person in personList");
    }

    public void togglePersonCheck(Item item, Person person) {
        if (personList.contains(person) && itemList.contains(item)) {
            getItem(item).billMap.put(person, !getItem(item).billMap.get(person));
        }
    }

    private void recalculateList() {
        for (int i = 0; i < itemList.size(); i++) {
            for (Person person: personList) {
                if (itemList.get(i).billMap.get(person)) {
                    this.billMap.put(person, )
                }
            }
        }
    }
}
