package chipta;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class Scarping  {
    public static void scarping(String fromWhere, String toWhere) throws Exception {

        //connecting to railway website and inserting data
        Connection.Response response = Jsoup
                .connect("https://chipta.railway.uz/ru/home")
                .data("search-trains__input--from", fromWhere, "search-trains__input--to", toWhere)
                .method(Connection.Method.POST)
                .execute();

        //Get cookies
        Map<String, String> railWayCookies = response.cookies();

        Document document = Jsoup.connect("https://chipta.railway.uz/ru/pages/trains-page")
                .cookies(railWayCookies)
                .get();
    }
}