package refactoring;

public abstract class Bike {
    private final String productName;
    private final double price;
    private final int maxSpeed;
    private final int rearGear;
    private final int frontGear;
    private static final double discountRate=0.8;
    private static final int discountThreshold=1000;

    protected Bike(String productName, double price, int maxSpeed, int rearGear, int frontGear) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name must not be null or blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed must not be negative");
        }
        if (rearGear <= 0 || frontGear <= 0) {
            throw new IllegalArgumentException("Gear counts must be greater than 0");
        }


        this.productName = productName;
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.rearGear = rearGear;
        this.frontGear = frontGear;


    }

    public String getProductName() {return productName;}
    public double getPrice() {return price;}
    public int getmaxSpeed() {return maxSpeed;}
    public int getRearGear() {return rearGear;}
    public int getFrontGear() {return frontGear;}

    public int getGearsCount(){
        return rearGear * frontGear;
    }

    protected double applyBulkDiscount(double totalPrice) {
        if (totalPrice >= discountThreshold) {
            return totalPrice * discountRate;// 20 % Rabatt
        }
        return totalPrice;
    }

    public abstract double calculatePrice ( int amount);

    @Override
    public String toString() {
        return String.format("%s: %.2f EUR, %d Gears, Max Speed: %d km/h",
            productName, price, maxSpeed, getGearsCount());
    }

}
