/**
 Class Market represents the stock market
 Market allows Stock's to be added and removed to it's ArrayList

 @author Sulaiman Bakali-Mueden
 @version 1
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Market implements Serializable {
    private static ArrayList<Stock> stocks = new ArrayList<Stock>();

    public static void add(Stock stock) {
        stocks.add(stock);
    }

    public static void remove(Stock stock) {
        stocks.remove(stock);
    }


    public static Object[] getMarketArray() {
        return stocks.toArray();
    }

    public static String getMarketString() {
        String text = "";
        Iterator<Stock> it = stocks.iterator();

        while (it.hasNext())
            text += it.next();

        return text;
    }

}
