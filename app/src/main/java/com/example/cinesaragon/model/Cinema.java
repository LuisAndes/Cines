package com.example.cinesaragon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinema {

    final String name;
    final String address;
    final String phone;
    final List<String> images;
    final Map<String, List<Session>> sessions;

    private Cinema(){
        this.name = "";
        this.address = "";
        this.phone = "";
        this.images = new ArrayList<>();
        this.sessions = new HashMap<>();
    }

    private Cinema(String name, String address, String phone, List<String> images, Map<String, List<Session>> sessions) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.images = images;
        this.sessions = sessions;
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

    public Map<String, List<Session>> getSessions() {
        return sessions;
    }
}
