package refactoring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class BillTest {

    private Bill sampleBill(){
        return new Bill(
            "Max Mustermann",
            "Maxi",
            "Musterstraße",
            "123a",
            32339,
            new Date(),
            "Max.Mustermann@gmail.com",
            "Espelkamp"



        );
    }


    @Test
    public void testAddArticle() {
        Bill bill=sampleBill();
        Article article=new Article(new EBike("Turbo 5000",2000,25,5, 1, 3000),1);


        boolean added=bill.addArticle(article);

        assertTrue(added);
        assertEquals(1, bill.getArticles().size());
    }

    @Test
    public void getDetailsWithDiscount() {
        Bill bill=sampleBill();
        Article article=new Article(new EBike("Turbo 5000",2000,25,5,1, 3000),1);

        bill.addArticle(article);

        bill.addArticle(new Article(new Mountainbike("Bergrider",2000,25,5, 1), 4));

        String details=bill.getDetails();

        assertTrue(details.contains("Max Mustermann"));

        assertTrue(details.contains("Turbo 5000"));
        assertTrue(details.contains("Bergrider"));
        assertTrue(details.contains("Total price"));
    }

    @Test
    public void testGetDetailsWithNoArticles() {
        Bill bill = sampleBill();

        String details = bill.getDetails();

        assertTrue(details.contains("Total price:\t0.0"));
    }

    @Test
    public void testAddNullArticle() {
        Bill bill = sampleBill();

        boolean added = bill.addArticle(null);

        assertTrue(added); // oder false, je nachdem, wie du es später behandelst
        assertEquals(1, bill.getArticles().size());
        assertNull(bill.getArticles().get(0)); // Kontrolle: null wurde wirklich gespeichert
    }

    @Test
    public void testDiscountAtExactly1000() {
        Bill bill = sampleBill();

        // 1 Mountainbike mit Preis 1000 (kein Mengenrabatt → Basisfall)
        bill.addArticle(new Article(new Mountainbike("Climber",1000,25,5,1), 1));

        String details = bill.getDetails();


        // 1000.0 * 0.8 = 800.0
        assertTrue(details.contains("=	800.0"));
        assertTrue(details.contains("Total price:\t800.0"));
    }

    @Test
    public void testBromptonWithQuantityDiscount() {
        Bill bill = sampleBill();

        // Preis: 500, Menge: 3
        // Rabatt: ab 2. Fahrrad nur halber Preis für die zusätzlichen →
        // (1x 500) + (2x 250) = 1000
        bill.addArticle(new Article(new Brompton("Brompton Classic", 500, 25, 5, 1), 3));

        String details = bill.getDetails();

        System.out.println(details);
        // Rabatt wegen Preisgrenze → 1000 * 0.8 = 800.0
        assertTrue(details.contains("Total price:\t800.0"));
    }

    @Test
    public void testMountainbikeBulkDiscount() {
        Bill bill = sampleBill();

        // 4x Mountainbike à 1000 → Mengenrabatt greift (10%)
        // (4-1) * 1000 /2 = 1500
        bill.addArticle(new Article(new Mountainbike("Rocky", 1000, 25, 5, 1), 4));

        String details = bill.getDetails();


        //4*1000*0,9*0,8 (0,8 weil price>=1000)
        assertTrue(details.contains("=	2880.0"));
    }

    @Test
    public void testMixedArticlesTotalPrice() {
        Bill bill = sampleBill();

        bill.addArticle(new Article(new Mountainbike("Rocky", 1000,25, 5, 1), 3));  // 2700
        bill.addArticle(new Article(new EBike("E-Rider",1200,25,5, 1, 2000), 1)); // 1200

        // Gesamt = 3900 → Rabatt auf beide?
        // beide über 1000 → 2700 * 0.8 = 2160, 1200 * 0.8 = 960 → Gesamt = 3120

        String details = bill.getDetails();

        assertTrue(details.contains("=	2160.0"));
        assertTrue(details.contains("=	960.0"));
        assertTrue(details.contains("Total price:\t3120.0"));
    }

}
