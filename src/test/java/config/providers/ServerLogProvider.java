package config.providers;

import model.ServerLog;
import model.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerLogProvider {
	public static Map<String, List<ServerLog>> getStartedAndFinished() {
		Map<String, List<ServerLog>> serverLogsById = new HashMap<>();
		String firstId = "server1";
		List<ServerLog> serverLogs = new ArrayList<>();
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(100L).state(State.STARTED).build());
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(104L).state(State.FINISHED).build());
		serverLogsById.put(firstId, serverLogs);
		return serverLogsById;
	}

	public static Map<String, List<ServerLog>> getOnlyStarted() {
		Map<String, List<ServerLog>> serverLogsById = new HashMap<>();
		String firstId = "server1";
		List<ServerLog> serverLogs = new ArrayList<>();
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(100L).state(State.STARTED).build());
		serverLogsById.put(firstId, serverLogs);
		return serverLogsById;
	}

	public static Map<String, List<ServerLog>> getWithTooLongDuration() {
		Map<String, List<ServerLog>> serverLogsById = new HashMap<>();
		String firstId = "server1";
		List<ServerLog> serverLogs = new ArrayList<>();
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(100L).state(State.STARTED).build());
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(108L).state(State.FINISHED).build());
		serverLogsById.put(firstId, serverLogs);
		return serverLogsById;
	}

	public static Map<String, List<ServerLog>> getApplicationServerLogs() {
		Map<String, List<ServerLog>> serverLogsById = new HashMap<>();
		String firstId = "server1";
		List<ServerLog> serverLogs = new ArrayList<>();
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(100L).host("1234").type("APPLICATION_LOG").state(State.STARTED).build());
		serverLogs.add(ServerLog.builder().id(firstId).timestamp(108L).host("1234").type("APPLICATION_LOG").state(State.FINISHED).build());
		serverLogsById.put(firstId, serverLogs);
		return serverLogsById;
	}
}
