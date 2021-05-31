package services;

import model.Event;
import model.ServerLog;

import java.util.List;
import java.util.Map;

public interface EventService {
	void saveLogs(Map<String, List<ServerLog>> serverLogsById);
	List<Event> convert(Map<String, List<ServerLog>> serverLogsById);
}
