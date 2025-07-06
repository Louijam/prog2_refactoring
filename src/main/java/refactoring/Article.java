package refactoring;

public class  Article {

    private Bike bike;
    private int purchaseAmount;


    public Article(Bike bike, int purchaseAmount) {
        if (bike == null || purchaseAmount <= 0) {
            throw new IllegalArgumentException("Invalid article data");
        }
        this.bike = bike;
        this.purchaseAmount = purchaseAmount;
    }

    public Bike getBike() {return bike;}

    public int getPurchaseAmount() {return purchaseAmount;}

    @Override
    public String toString() {
        return String.format("%s x%d (%.2f EUR)",
            bike.getProductName(),
            purchaseAmount,
            bike.calculatePrice(purchaseAmount));
    }
}
