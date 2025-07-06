package refactoring;

public class Mountainbike extends Bike {

    public Mountainbike(String productName, double price, int maxSpeed, int rearGearCount, int frontGearCount) {
        super(productName, price, maxSpeed, rearGearCount, frontGearCount);
    }

    /**
     * Berechnet den Gesamtpreis für eine bestimmte Menge der Bikes.
     *
     * @param amount Die Anzahl der Bikes, die gekauft werden sollen. Muss größer 0 sein.
     * @return Der Gesamtpreis nach Berücksichtigung von Rabatten.
     *
     */
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

