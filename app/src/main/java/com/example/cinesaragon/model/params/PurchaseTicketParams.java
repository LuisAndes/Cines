package com.example.cinesaragon.model.params;

import com.example.cinesaragon.model.FullInfoSession;

public class PurchaseTicketParams {

    private final FullInfoSession session;
    private final String day;


    public PurchaseTicketParams(FullInfoSession session, String day) {
        this.session = session;
        this.day = day;
    }

    public FullInfoSession getSession() {
        return session;
    }

    public String getDay() {
        return day;
    }
}
