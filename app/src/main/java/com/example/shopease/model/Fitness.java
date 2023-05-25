package com.example.shopease.model;

public class Fitness {

    String fitness_id, fitness_imageUrl, fitness_name, fitness_price, fitness_rating,fitness_discription,fitness_stock;

   public Fitness(){

   }

    public Fitness(String fitness_id, String fitness_imageUrl, String fitness_name , String fitness_price ,String fitness_rating ,String fitness_discription, String fitness_stock ){
        this.fitness_id = fitness_id;
        this.fitness_imageUrl = fitness_imageUrl;
        this.fitness_name = fitness_name;
        this.fitness_price = fitness_price;
        this.fitness_rating = fitness_rating;
        this.fitness_discription = fitness_discription;
        this.fitness_stock = fitness_stock;

    }

    public String getFitness_stock() {
        return fitness_stock;
    }

    public void setFitness_stock(String fitness_stock) {
        this.fitness_stock = fitness_stock;
    }

    public String getFitness_rating() {
        return fitness_rating;
    }

    public void setFitness_rating(String fitness_rating) {
        this.fitness_rating = fitness_rating;
    }

    public String getFitness_discription() {
        return fitness_discription;
    }

    public void setFitness_discription(String fitness_discription) {
        this.fitness_discription = fitness_discription;
    }

    public String getFitness_id() {
        return fitness_id;
    }

    public void setFitness_id(String fitness_id) {
        this.fitness_id = fitness_id;
    }

    public String getFitness_imageUrl() {
        return fitness_imageUrl;
    }

    public void setFitness_imageUrl(String fitness_imageUrl) {
        this.fitness_imageUrl = fitness_imageUrl;
    }

    public String getFitness_name() {
        return fitness_name;
    }

    public void setFitness_name(String fitness_name) {
        this.fitness_name = fitness_name;
    }

    public String getFitness_price() {
        return fitness_price;
    }

    public void setFitness_price(String fitness_price) {
        this.fitness_price = fitness_price;
    }

}
