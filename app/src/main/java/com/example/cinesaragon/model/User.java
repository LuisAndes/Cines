package com.example.cinesaragon.model;

public class User {

    final String name;
    final String lastName;
    final String email;
    final String address;
    final String card;
    final String paypal;
    final String phone;

    private User() {
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.address = "";
        this.card = "";
        this.paypal = "";
        this.phone = "";
    }

    private User(String email, String name, String lastName, String address, String card, String paypal, String phone) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
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

    public String getId() {
        return email.replaceAll("\\.", "_");
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {

        String name;
        String email;
        String lastName;
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

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
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
            return new User(email, name, lastName, address, card, paypal, phone);
        }
    }


}
