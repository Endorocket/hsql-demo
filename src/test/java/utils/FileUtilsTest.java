package utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.SneakyThrows;
import model.ServerLog;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilsTest {

	@Test
	@SneakyThrows
	void readsFileAndConvertsServerLogs_whenCorrectFileExists() {
		Map<String, List<ServerLog>> serverLogsById = FileUtils.readServerLogsFromFile("src/test/resources/logfile.txt");
		assertEquals(3, serverLogsById.size());
		long actualLogsSize = serverLogsById.values()
			.stream()
			.mapToLong(Collection::size)
			.sum();
		assertEquals(6, actualLogsSize);
	}

	@Test
	void throwsException_whenFileNotExists() {
		assertThrows(FileNotFoundException.class, () -> FileUtils.readServerLogsFromFile("src/test/resources/not_exists.txt"));
	}

	@Test
	void throwsException_whenFileContentFormatIsInvalid() {
		assertThrows(JsonParseException.class, () -> FileUtils.readServerLogsFromFile("src/test/resources/invalid_format_logfile.txt"));
	}

	@Test
	void throwsException_whenFileContainsInvalidObject() {
		assertThrows(InvalidFormatException.class, () -> FileUtils.readServerLogsFromFile("src/test/resources/invalid_object_logfile.txt"));
	}
}
