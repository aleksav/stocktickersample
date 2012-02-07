package com.opencredo.sandbox.aleksav.esper.listener.collector;

import com.espertech.esper.client.EventBean;
import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksa Vukotic
 */
public class InPlaceEventCollector {
    private Map<String, MarketDataEvent> store = Collections.synchronizedMap(new HashMap<String, MarketDataEvent>());

    private final Object keyPropertyName;

    public InPlaceEventCollector(Object keyPropertyName) {
        this.keyPropertyName = keyPropertyName;
    }

    public void collect(EventBean[] eventBeans)
            throws RuntimeException {
        for(EventBean eventBean : eventBeans){
            addEvent(eventBean);
        }
    }

    private synchronized void addEvent(EventBean eventBean){
        String key = eventBean.get(keyPropertyName.toString()).toString();
        MarketDataEvent marketDataEvent = new MarketDataEvent();
        marketDataEvent.setSymbol(key);
        marketDataEvent.setPrice(new Double(eventBean.get("avg(price)").toString()));
        marketDataEvent.setAsk(new Double(eventBean.get("avg(ask)").toString()));
        marketDataEvent.setBid(new Double(eventBean.get("avg(bid)").toString()));

        store.put(key, marketDataEvent);
    }

    public Map<String, MarketDataEvent> getStore() {
        return store;
    }
}
