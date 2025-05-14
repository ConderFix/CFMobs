package ru.quizie.cfmobs.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Mob {
    private String name;
    private double min, max;
}
