package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ServerLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static Map<String, List<ServerLog>> readServerLogsFromFile(String pathName) throws IOException {
		Map<String, List<ServerLog>> serverLogsById = new HashMap<>();

		File file = new File(pathName);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String content;
			while ((content = br.readLine()) != null) {
				System.out.println("Reading content from file: " + content);
				ServerLog serverLog = objectMapper.readValue(content, ServerLog.class);
				assignServerLogToMap(serverLogsById, serverLog);
			}
		}

		return serverLogsById;
	}

	private static void assignServerLogToMap(Map<String, List<ServerLog>> serverLogsById, ServerLog serverLog) {
		if (serverLogsById.containsKey(serverLog.getId())) {
			List<ServerLog> serverLogs = serverLogsById.get(serverLog.getId());
			serverLogs.add(serverLog);
		} else {
			List<ServerLog> serverLogs = new ArrayList<>();
			serverLogs.add(serverLog);
			serverLogsById.put(serverLog.getId(), serverLogs);
		}
	}
}
