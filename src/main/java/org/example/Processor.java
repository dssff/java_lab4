package org.example;

import java.util.Objects;

/**
 * Клас, що представляє процесор телефону (об'єкт для агрегації).
 */
public class Processor {
    private String model;
    private int cores;
    private double frequency;

    /**
     * Конструктор для ініціалізації процесора.
     */
    public Processor(String model, int cores, double frequency) {
        setModel(model);
        setCores(cores);
        setFrequency(frequency);
    }

    /**
     * Конструктор копіювання для процесора.
     */
    public Processor(Processor other) {
        if (other != null) {
            this.model = other.model;
            this.cores = other.cores;
            this.frequency = other.frequency;
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель процесора не може бути порожньою");
        }
        this.model = model;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        if (cores <= 0) {
            throw new IllegalArgumentException("Кількість ядер має бути більшою за 0");
        }
        this.cores = cores;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Частота має бути більшою за 0");
        }
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Processor processor = (Processor) o;
        return cores == processor.cores &&
                Double.compare(processor.frequency, frequency) == 0 &&
                Objects.equals(model, processor.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, cores, frequency);
    }

    @Override
    public String toString() {
        return model + " (" + cores + " cores, " + frequency + " GHz)";
    }
}
