package refactoring;

public class Brompton extends Bike {

    public Brompton(String productName, double price, int maxSpeed, int rearGearCount, int frontGearCount) {
        super(productName, price, maxSpeed, rearGearCount, frontGearCount);
    }

    @Override
    public double calculatePrice(int amount){
        double pricePerUnit=getPrice();
        int discountAmount = Math.max(0, amount-1);

        double total=pricePerUnit+ (discountAmount*pricePerUnit/2);

        return applyBulkDiscount(total);
    }
}
