package com.github.eventure.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private String title;
    private String typeEvent;
    private String category;
    private List<Image> images;
    private Date date;
    // não sei qual é melhor para armazenar data se é a class Date ou uma string 
    private LocalTime startHours;
    private LocalTime endHours;
    // Ideia para uso seria LocalTime hora = LocalTime.of(14,35); para pegar somente hora e minutos
    // mesma ideia da data não sei qual é melhor por causa do espaço em memoria ocupado
    private Address address;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Evento(id=%d, name=%s)", id, name);
    }

}

