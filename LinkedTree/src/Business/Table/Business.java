package Business.Table;

/**
 *  A class used to represent a business from the dataset provided in the statement
 *
 *  The variables are the same that would be found in the tables.paed file. This class also
 *  contains the getters and setters needed to manage the variables.
 */

public class Business {
    private String name;
    private String day;
    private int price;

    public Business(String name, String day, int price) {
        this.name = name;
        this.day = day;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
