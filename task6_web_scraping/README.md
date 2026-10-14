# Task 6: Interactive Web Scraping

A Java console program that fetches a website and shows the title, headings, links or paragraphs in a readable format, using the Jsoup library.

## Setup
1. Download the Jsoup jar from https://jsoup.org/download
2. Place it in this folder and rename it to `jsoup.jar`

## How to run
Windows:
```
javac -cp jsoup.jar WebScraper.java
java -cp ".;jsoup.jar" WebScraper
```
Mac/Linux:
```
javac -cp jsoup.jar WebScraper.java
java -cp ".:jsoup.jar" WebScraper
```

## Test websites
- https://example.com
- https://quotes.toscrape.com
- https://books.toscrape.com
- An invalid URL (shows an error message instead of crashing)

Note: scrape only sites that allow it. The sites above are made for practice.
