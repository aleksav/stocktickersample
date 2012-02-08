package com.opencredo.sandbox.aleksav.esper.generator;

import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;
import org.opencredo.esper.EsperTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Aleksa Vukotic
 */
public class RandomMarketDataGenerator implements MarketDataGenerator {
    private final EventGateway eventGateway;

    @Autowired
    public RandomMarketDataGenerator(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    enum Symbol {
        ACME(1, "ACME", 100, 1), FGTS(2, "FGTS", 13, 5), JUYY(3, "JUYY", 5, 10), NYSR(4, "NYSR", 657, 7);

        private int id;
        private String name;
        private double med = 100;
        private double varPercentage = 5;

        Symbol(int id, String name, double med, double var) {
            this.id = id;
            this.name = name;
            this.varPercentage = var;
            this.med = med;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static Symbol fromInt(int id) {
            switch (id) {
                case 1:
                    return ACME;
                case 2:
                    return FGTS;
                case 3:
                    return JUYY;
                case 4:
                    return NYSR;
            }
            throw new IllegalArgumentException("Unknown symbol");
        }

        public double getMed() {
            return med;
        }

        public double getVarPercentage() {
            return varPercentage;
        }
    }

    @Override
    public List<MarketDataEvent> generateEvents() {
        List<MarketDataEvent> result = new ArrayList<MarketDataEvent>();
        int eventBatchSize = getRandomAbsInt(100000);
        for (int i = 0; i < eventBatchSize; i++) {
            MarketDataEvent event = new MarketDataEvent();
            Symbol symbol = Symbol.fromInt(getRandomAbsInt(4) + 1);
            event.setSymbol(symbol.name());
            event.setName(symbol.toString());
            event.setBid((getRandomAbsBigDecimal(100).doubleValue()));
            event.setAsk(getRandomAbsBigDecimal(100).doubleValue());
            event.setPrice(generateNewPrice(symbol).doubleValue());
            result.add(event);
            eventGateway.sendEvent(event);
        }


        return result;
    }

    private BigDecimal generateNewPrice(Symbol symbol) {
        return new BigDecimal(symbol.getMed() + getRandomDouble(symbol.getVarPercentage()) * symbol.getMed() / 100).setScale(2, RoundingMode.HALF_EVEN);
    }

    private int getRandomInt(int max) {
        return new Random().nextInt(max);
    }

    private double getRandomDouble(Double max) {
        return new Random().nextDouble() * max;
    }

    private int getRandomAbsInt(int max) {
        return Math.abs(new Random().nextInt(max));
    }

    private BigDecimal getRandomAbsBigDecimal(double max) {
        return new BigDecimal(Math.abs(getRandomDouble(max))).setScale(2, RoundingMode.HALF_EVEN);
    }

}
