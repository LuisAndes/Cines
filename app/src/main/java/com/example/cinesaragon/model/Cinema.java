package com.example.cinesaragon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Cinema {

    final String name;
    final String address;
    final String phone;
    final List<String> images;

    private Cinema(){
        this.name = "";
        this.address = "";
        this.phone = "";
        this.images = new ArrayList<>();
    }

    private Cinema(String name, String address, String phone, List<String> images) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getImages() {
        return images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema)) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(name, cinema.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cinema{");
        sb.append("name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
