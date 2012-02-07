package com.opencredo.sandbox.aleksav.esper.controller;

import com.opencredo.sandbox.aleksav.esper.domain.MarketDataEvent;
import com.opencredo.sandbox.aleksav.esper.listener.collector.InPlaceEventCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Aleksa Vukotic
 */
@Controller
public class TestController {

    @Autowired
    private InPlaceEventCollector inPlaceEventCollector;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/inplace.json")
    public @ResponseBody Map<String, MarketDataEvent> inPlace() {
        return inPlaceEventCollector.getStore();
    }
}
