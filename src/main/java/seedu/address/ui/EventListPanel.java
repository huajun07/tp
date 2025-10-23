package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.ui.detailedpanel.DetailedPanel;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";

    @FXML
    private ListView<Event> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<Event> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

    /**
     * Binds the EventDetailedView to the currently selected event from {@code ListView}.
     * When the selected event changes, the new details are reflected in EventDetailedView.
     */
    public void listenForSelectionEvent(DetailedPanel eventDetailedPanel) {
        eventListView.getSelectionModel().selectedItemProperty().addListener(((
                observable, oldValue, newValue) -> {
                    eventDetailedPanel.updateDetails(newValue);
                }));
    }

    public void setSelectedEvent(Event p) {
        if (p == null) {
            eventListView.getSelectionModel().clearSelection();
        }
        eventListView.getSelectionModel().select(p);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);

            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EventCard(event, getIndex() + 1).getRoot());
            }
        }
    }
}
