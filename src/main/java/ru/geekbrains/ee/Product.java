package ru.geekbrains.ee;

class Product {
    int id;
    String title;
    double cost;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    Product(int id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}