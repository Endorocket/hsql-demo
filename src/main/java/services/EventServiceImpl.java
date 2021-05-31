package services;

import dao.EventDAO;
import dao.EventDAOImpl;
import lombok.AllArgsConstructor;
import model.Event;
import model.ServerLog;
import model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventDAO eventDAO;

	public void saveLogs(Map<String, List<ServerLog>> serverLogsById) {
		List<Event> events = convert(serverLogsById);
		eventDAO.createTableIfNotExists();
		eventDAO.insert(events);
	}

	@Override
	public List<Event> convert(Map<String, List<ServerLog>> serverLogsById) {
		List<Event> events = new ArrayList<>();
		for (List<ServerLog> serverLogs : serverLogsById.values()) {
			Event event = createEvent(serverLogs.get(0));
			long startedTimestamp = 0;
			long finishedTimestamp = 0;
			for (ServerLog serverLog : serverLogs) {
				if (serverLog.getState() == State.STARTED) {
					startedTimestamp = serverLog.getTimestamp();
				} else {
					finishedTimestamp = serverLog.getTimestamp();
				}
			}
			if (startedTimestamp == 0 || finishedTimestamp == 0) {
				System.err.println("Missing STARTED or FINISHED event");
			} else {
				long duration = finishedTimestamp - startedTimestamp;
				event.setDuration(duration);
				if (duration > 4) {
					event.setAlert(true);
				}
			}
			events.add(event);
		}
		return events;
	}

	private Event createEvent(ServerLog serverLog) {
		return Event.builder()
			.id(serverLog.getId())
			.type(serverLog.getType())
			.host(serverLog.getHost())
			.build();
	}
}
