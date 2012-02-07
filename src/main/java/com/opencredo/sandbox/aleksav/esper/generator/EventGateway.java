package com.opencredo.sandbox.aleksav.esper.generator;

import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;

/**
 * @author Aleksa Vukotic
 */
public interface EventGateway {
    void sendEvent(MarketDataEvent event);
}
