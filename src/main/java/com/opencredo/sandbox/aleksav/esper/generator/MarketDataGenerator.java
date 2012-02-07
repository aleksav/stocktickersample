package com.opencredo.sandbox.aleksav.esper.generator;

import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;

import java.util.List;

/**
 * @author Aleksa Vukotic
 */
public interface MarketDataGenerator {

    List<MarketDataEvent> generateEvents();
}
