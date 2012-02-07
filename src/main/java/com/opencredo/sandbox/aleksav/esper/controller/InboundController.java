package com.opencredo.sandbox.aleksav.esper.controller;

import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;
import com.opencredo.sandbox.aleksav.esper.generator.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Aleksa Vukotic
 */
@Controller
public class InboundController {

    @Autowired
    private EventGateway eventGateway;

    @RequestMapping(value = "/push.json", method = RequestMethod.POST)
    public void pushEvent(@RequestBody MarketDataEvent marketDataEvent){
        eventGateway.sendEvent(marketDataEvent);
    }
}
