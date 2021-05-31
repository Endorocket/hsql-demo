package services;

import config.dao.EventDAOMock;
import dao.EventDAO;
import model.Event;
import model.ServerLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import config.providers.ServerLogProvider;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

	private static EventService eventService;

	@BeforeAll
	static void init() {
		EventDAO eventDAO = new EventDAOMock();
		eventService = new EventServiceImpl(eventDAO);
	}

	@Test
	void convertsWithDuration_whenServerStartedAndFinished() {
		Map<String, List<ServerLog>> startedAndFinished = ServerLogProvider.getStartedAndFinished();
		List<Event> events = eventService.convert(startedAndFinished);
		assertEquals(1, events.size());
		assertEquals(4, events.get(0).getDuration());
	}

	@Test
	void convertsWithoutDuration_whenServerNotFinished() {
		Map<String, List<ServerLog>> startedAndFinished = ServerLogProvider.getOnlyStarted();
		List<Event> events = eventService.convert(startedAndFinished);
		assertEquals(1, events.size());
		assertNull(events.get(0).getDuration());
	}

	@Test
	void convertsWithoutAlert_whenDurationIsTooLong() {
		Map<String, List<ServerLog>> startedAndFinished = ServerLogProvider.getWithTooLongDuration();
		List<Event> events = eventService.convert(startedAndFinished);
		assertEquals(1, events.size());
		assertTrue(events.get(0).isAlert());
	}

	@Test
	void convertsApplicationServerLog_whenContainsTypeAndHost() {
		Map<String, List<ServerLog>> startedAndFinished = ServerLogProvider.getApplicationServerLogs();
		List<Event> events = eventService.convert(startedAndFinished);
		assertEquals(1, events.size());
		assertEquals("APPLICATION_LOG", events.get(0).getType());
		assertEquals("1234", events.get(0).getHost());
	}
}
