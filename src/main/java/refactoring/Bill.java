package refactoring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bill {


    private final String customerName;
    private final String nickname;
    private final String street;
    private final String streetNumber;
    private final int postalCode;
    private final String city;
    private final Date birthday;
    private final String email;
    private final ArrayList<Article> articles;

    /**
     * Erzeugt eine neue Rechnung für einen Kunden.
     *
     * @param name Kundenname
     * @param nickname Kunden-Spitzname
     * @param street Straße der Adresse
     * @param streetNumber Hausnummer der Adresse
     * @param postalCode Postleitzahl
     * @param birthday Geburtstag des Kunden
     * @param email E-Mail-Adresse des Kunden
     * @param city Stadt der Adresse
     */

    public Bill(String name, String nickname, String street, String streetNumber, int postalCode, Date birthday, String email, String city) {
        this.customerName = name;
        this.nickname = nickname;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.birthday = birthday;
        this.email = email;
        this.city = city;
        articles = new ArrayList<>();
    }

    /**
     * Fügt einen Artikel zur Rechnung hinzu.
     *
     * @param a Der hinzuzufügende Artikel
     * @return true, wenn der Artikel erfolgreich hinzugefügt wurde
     */
    public boolean addArticle(Article a) {
        return articles.add(a);
    }

    /**
     * Berechnet den Preis eines einzelnen Artikels in der Rechnung.
     *
     * @param article Der Artikel, dessen Preis berechnet werden soll
     * @return Der berechnete Preis
     */
    public double calculatePrice(Article article) {
        return article.getBike().calculatePrice(article.getPurchaseAmount());
    }

    /**
     * Berechnet den Gesamtpreis aller Artikel in der Rechnung.
     *
     * @return Die Summe der Preise aller Artikel
     */
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Article article : articles) {
            totalPrice += article.getBike().calculatePrice(article.getPurchaseAmount());
        }
        return totalPrice;
    }

    /**
     * Gibt die Details der Rechnung als formatierter String zurück.
     *
     * @return Eine lesbare Rechnungsausgabe
     */
    public String getDetails() {
        double total = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Details for \"").append(customerName).append("\"\n");
        sb.append(street).append(" ").append(streetNumber).append("\n");
        sb.append(postalCode).append(" ").append(city).append("\n");
        sb.append("Geburtstag: ").append(formatter.format(birthday)).append("\n");
        sb.append("Email: ").append(email).append("\n\n");
        sb.append("refactoring.Article: \n");

        for (Article article : articles) {
            double price = calculatePrice(article);
            sb.append("\t")
                .append(article.getBike().getProductName())
                .append("\tx\t")
                .append(article.getPurchaseAmount())
                .append("\t=\t")
                .append(price)
                .append("\n");
            total += price;
        }

        sb.append("\nTotal price:\t").append(calculateTotalPrice()).append("\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getCustomerName() {
        return customerName;
    }
    public String getNickname() {
        return nickname;
    }
    public SimpleDateFormat getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.format(birthday);
        return sdf;
    }
    public String getEmail() {
        return email;
    }
    public String getStreet() {
        return street;
    }
    public String getStreetNumber() {
        return streetNumber;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public String getCity() {
        return city;
    }
    public ArrayList<Article> getArticles() {
        return articles;
    }
}
