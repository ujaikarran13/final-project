package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Book> booksToBuy = new ArrayList<>();
    public void add(Book bookToAdd) {
        booksToBuy.add(bookToAdd);
    }
    public double getTotalPrice() {
        double total = 0.0;
        for(Book book : booksToBuy) {
            total += book.getPrice();
        }
        return total;
    }
}
