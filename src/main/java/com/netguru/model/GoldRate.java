package com.netguru.model;

import java.io.Serializable;
import java.time.LocalDate;

public class GoldRate implements Serializable {

    private LocalDate data;
    private double cena;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "GoldRate{" +
                "data=" + data +
                ", cena=" + cena +
                '}';
    }
}
