import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

public class WebScraper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== INTERACTIVE WEB SCRAPER =====");
            System.out.print("Enter website URL (or 'exit'): ");
            String url = sc.nextLine().trim();
            if (url.equalsIgnoreCase("exit")) break;
            if (!url.startsWith("http")) url = "https://" + url;

            try {
                Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();

                System.out.println("\n1. Page title\n2. Headings\n3. Links\n4. Paragraphs\n5. All of the above");
                System.out.print("What do you want to scrape? ");
                String ch = sc.nextLine().trim();

                if (!ch.matches("[1-5]")) {
                    System.out.println("Invalid option.");
                    continue;
                }
                if (ch.equals("1") || ch.equals("5")) showTitle(doc);
                if (ch.equals("2") || ch.equals("5")) showHeadings(doc);
                if (ch.equals("3") || ch.equals("5")) showLinks(doc);
                if (ch.equals("4") || ch.equals("5")) showParagraphs(doc);
            } catch (Exception e) {
                System.out.println("Could not fetch the page: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    static void showTitle(Document doc) {
        System.out.println("\n--- TITLE ---");
        System.out.println(doc.title().isEmpty() ? "(no title)" : doc.title());
    }

    static void showHeadings(Document doc) {
        System.out.println("\n--- HEADINGS (first 10) ---");
        Elements hs = doc.select("h1, h2, h3");
        if (hs.isEmpty()) System.out.println("(none found)");
        int n = 0;
        for (Element h : hs) {
            if (n++ == 10) break;
            System.out.println("* " + h.text());
        }
    }

    static void showLinks(Document doc) {
        System.out.println("\n--- LINKS (first 10) ---");
        Elements links = doc.select("a[href]");
        if (links.isEmpty()) System.out.println("(none found)");
        int n = 0;
        for (Element a : links) {
            if (n == 10) break;
            n++;
            String text = a.text().isEmpty() ? "(no text)" : a.text();
            System.out.println(n + ". " + text + " -> " + a.absUrl("href"));
        }
    }

    static void showParagraphs(Document doc) {
        System.out.println("\n--- PARAGRAPHS (first 5) ---");
        Elements ps = doc.select("p");
        if (ps.isEmpty()) System.out.println("(none found)");
        int n = 0;
        for (Element p : ps) {
            if (n++ == 5) break;
            String t = p.text();
            System.out.println("- " + (t.length() > 150 ? t.substring(0, 150) + "..." : t));
        }
    }
}
