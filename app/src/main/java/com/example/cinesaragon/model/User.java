package com.example.cinesaragon.model;

public class User {

    final String name;
    final String email;
    final String address;
    final String card;
    final String paypal;
    final String phone;

    private User(String email, String name, String address, String card, String paypal, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.card = card;
        this.paypal = paypal;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCard() {
        return card;
    }

    public String getPaypal() {
        return paypal;
    }

    public String getPhone() {
        return phone;
    }

    public static class Builder {

        String name;
        String email;
        String address;
        String card;
        String paypal;
        String phone;

        public Builder(String email) {
            this.email = email;
        }

        public Builder(User user) {
            this.name = user.name;
            this.email = user.email;
            this.address = user.address;
            this.card = user.card;
            this.paypal = user.paypal;
            this.phone = user.phone;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCard(String card) {
            this.card = card;
            return this;
        }

        public Builder setPaypal(String paypal) {
            this.paypal = paypal;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(email, name, address, card, paypal, phone);
        }
    }
}
