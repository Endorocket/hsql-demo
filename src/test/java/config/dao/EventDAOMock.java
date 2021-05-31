package config.dao;

import dao.EventDAO;
import model.Event;

import java.util.List;

public class EventDAOMock implements EventDAO {
	@Override
	public void createTableIfNotExists() {
		// do nothing
	}

	@Override
	public void insert(List<Event> events) {
		// do nothing
	}

	@Override
	public void close() throws Exception {
		// do nothing
	}
}
