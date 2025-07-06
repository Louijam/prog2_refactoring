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

    public boolean addArticle(Article a) {
        return articles.add(a);
    }

    public double calculatePrice(Article article) {
        return article.getBike().calculatePrice(article.getPurchaseAmount());
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Article article : articles) {
            totalPrice += article.getBike().calculatePrice(article.getPurchaseAmount());
        }
        return totalPrice;
    }

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
