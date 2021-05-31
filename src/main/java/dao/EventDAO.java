package dao;

import model.Event;

import java.util.List;

public interface EventDAO extends AutoCloseable {
	void createTableIfNotExists();
	void insert(List<Event> events);
}
