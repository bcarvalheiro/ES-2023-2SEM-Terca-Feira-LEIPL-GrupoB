package org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar;

import org.es.leipl.tercafeira.grupob.pojos.Bloco;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarEvent {

    private static final Color DEFAULT_COLOR = Color.PINK;

    private Bloco bloco;
    private Color color;

    public CalendarEvent(Bloco bloco) {
        this(bloco, DEFAULT_COLOR);
    }

    public CalendarEvent(Bloco bloco, Color color) {
        this.bloco = bloco;
        this.color = color;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public String toString() {
        return bloco.toString();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (!bloco.toString().equals(that.bloco.toString())) return false;
        return true;

    }

    @Override
    public int hashCode() {
        return bloco.hashCode();
    }
}
