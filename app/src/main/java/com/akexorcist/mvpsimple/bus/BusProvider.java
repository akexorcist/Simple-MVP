package com.akexorcist.mvpsimple.bus;

import com.squareup.otto.Bus;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class BusProvider {
    private static BusProvider busProvider;

    public static BusProvider getProvider() {
        if (busProvider == null) {
            busProvider = new BusProvider();
        }
        return busProvider;
    }

    private Bus bus;

    public Bus getBus() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

}
