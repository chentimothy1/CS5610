package com.example.wbdvsp2101timothychenserverjava.controllers;

import java.util.List;

import com.example.wbdvsp2101timothychenserverjava.models.Widget;
import com.example.wbdvsp2101timothychenserverjava.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
    @Autowired
    WidgetService service; // = new WidgetService();

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(@PathVariable("tid") String topicId, @RequestBody Widget w) {
        return service.createWidget(topicId, w);
    }

    // getting widgets within the particular context of a topic
    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(@PathVariable("tid") String topicId) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(@PathVariable("wid") Long id) {
        return service.findWidgetById(id);
    }

    @PutMapping("/api/widgets/{wid}")
    public Integer updateWidget(@PathVariable("wid") Long widgetId, @RequestBody Widget w) {
        return service.updateWidget(widgetId, w);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public Integer deleteWidget(@PathVariable("wid") Long widgetId) {
        return service.deleteWidget(widgetId);
    }

}
