package com.example.cinesaragon.model;

import java.util.List;
import java.util.Map;

public class Cinema {

    final String name;
    final String address;
    final String phone;
    final List<String> pictures;
    final Map<String, List<Session>> sessions;


    private Cinema(String name, String address, String phone, List<String> pictures, Map<String, List<Session>> sessions) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pictures = pictures;
        this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, List<Session>> getSessions() {
        return sessions;
    }
}
