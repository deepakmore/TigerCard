package com.morez.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ZoneRoute {
    private Zone source;
    private Zone destination;
    private Fare fare;
    private Capping capping;
}
