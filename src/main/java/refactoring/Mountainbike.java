package refactoring;

public class Mountainbike extends Bike {

    public Mountainbike(String productName, double price, int maxSpeed, int rearGearCount, int frontGearCount) {
        super(productName, price, maxSpeed, rearGearCount, frontGearCount);
    }

    @Override
    public double calculatePrice(int amount){
        double total=0;

        if (amount > 2){
            total+=amount * getPrice() * 0.9;
        } else {
            total+=amount * getPrice();
        }

       return applyBulkDiscount(total);
    }
}

