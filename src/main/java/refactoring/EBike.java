package refactoring;

public class EBike extends Bike {
    private final int batteryCapacity;

    public EBike(String productName, double price, int maxSpeed, int rearGearCount, int frontGearCount, int batteryCapacity) {
        super(productName, price, maxSpeed, rearGearCount, frontGearCount);
        if (batteryCapacity<0) {
            throw new IllegalArgumentException("Battery Capacity should be greater than 0");
        }
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity(){ return batteryCapacity; }



    @Override
    public double calculatePrice(int amount){
        double total=getPrice()*amount;

        return applyBulkDiscount(total);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Battery: %dWh]", batteryCapacity);
    }
}

