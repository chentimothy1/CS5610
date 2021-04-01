package com.example.wbdvsp2101timothychenserverjava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.wbdvsp2101timothychenserverjava.models.Widget;
import com.example.wbdvsp2101timothychenserverjava.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository repository;

    private List<Widget> widgets = new ArrayList<Widget>();
    {
        // for testing
        Widget w1 = new Widget(123l, "ABC123", "HEADING", 1, "Widgets for topic ABC123");
        Widget w2 = new Widget(234l, "ABC123", "PARAGRAPH", 1, "Hello Professor");
        Widget w3 = new Widget(345l, "ABC234", "HEADING", 2, "Welcome to Widget List!");
        Widget w4 = new Widget(456l, "ABC234", "PARAGRAPH", 1, "Testing testing testing");

        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
    }

    // public Widget createWidget(String topicId, Widget w) {
    // // w.setTopicId(topicId);
    // // w.setId(new Date().getTime());
    // // widgets.add(w);
    // // return w;
    // }
    public Widget createWidget(String topicId, Widget w) {
        return repository.save(w);
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        /* using widgetrepository */
        return repository.findWidgetsForTopic(topicId);

        /* this is using the widget service rather than the db */
        // List<Widget> ws = new ArrayList<Widget>();
        // for (Widget w : widgets) {
        // if (w.getTopicId().equals(topicId)) {
        // ws.add(w);
        // }
        // }
        // return ws;
    }

    public Integer updateWidget(Long widgetId, Widget w) {
        Widget originalWidget = repository.findById(id).get();

        // TODO: copy all the other fields testing for null

        // type
        if (w.getType() != null) {
            originalWidget.setType(w.getType());
        }


        // size
        if (w.getSize() != null) {
            originalWidget.setSize(w.getSize());
        }

        // text
        if (w.getText() != null) {
            originalWidget.setText(w.getText());
        }

        // urlRef
        if (w.getUrlRef() != null) {
            originalWidget.setUrlRef(w.getUrlRef());
        }

        // width
        if (w.getWidth() != null) {
            originalWidget.setWidth(w.getWidth());
        }

        // height
        if (w.getHeight() != null) {
            originalWidget.setHeight(w.getHeight());
        }

        // cssClass
        // if ( !w.getCssClass().equals( null )) {
        // originalWidget.setCssClass( w.getCssClass() );
        // }

        // style
        // if ( !w.getStyle().equals( null )) {
        // originalWidget.setStyle( w.getStyle() );
        // }

        // value
        // if ( !w.getValue().equals( null )) {
        // originalWidget.setValue( w.getValue() );
        // }

        // ordered
        if (w.getOrdered() != null) {
            originalWidget.setOrdered(w.getOrdered());
        }

        // save this new widget to the db
        repository.save(originalWidget);

        // return 1 if successful
        return 1;

        // for( int i = 0; i < widgets.size(); i++ ) {
        // if(widgets.get(i).getId().equals(widgetId)) {
        // widgets.set(i, w);
        // return 1;
        // }
        // }
        // return -1;

        repository.save(originalWidget);

        return 1;

        // for (int i = 0; i < widgets.size(); i++) {
        // if (widgets.get(i).getId().equals(widgetId)) {
        // widgets.set(i, w);
        // return 1;
        // }
        // }
        // return -1;
    }

    public Integer deleteWidget(Long widgetId) {

        repository.deleteById(widgetId);
        return 1;

        // for (int i = 0; i < widgets.size(); i++) {
        // if (widgets.get(i).getId().equals(widgetId)) {
        // widgets.remove(i);
        // return 1;
        // }
        // }
        // return -1;
    }

    public List<Widget> findAllWidgets() {
        /* using the db */
        // return (List<Widget>) repository.findAll();

        /* using widgetrepository */
        return repository.findAllWidgets();

        /* this is using the widget service rather than the db */
        // return widgets;
    }

    public Widget findWidgetById(Long widgetId) {
        /* using the db */
        // return repository.findById(widgetId).get();

        /* using widgetrepository */
        return repository.findWidgetById(widgetId);

        /* this is using the widget service rather than the db */
        // for (int i = 0; i < widgets.size(); i++) {
        // if (widgets.get(i).getId().equals(widgetId)) {
        // return widgets.get(i);
        // }
        // }
        // return null;
    }
}