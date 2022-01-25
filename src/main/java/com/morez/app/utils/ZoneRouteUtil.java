package com.morez.app.utils;

import com.morez.app.config.DataLoader;
import com.morez.app.model.ZoneRoute;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ZoneRouteUtil {

    public ZoneRoute getZoneRoute(String source, String destination) {
        return DataLoader.getRoutes().get(KeyGenerator.generate(source, destination));
    }
}
