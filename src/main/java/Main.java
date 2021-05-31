import dao.EventDAO;
import dao.EventDAOImpl;
import model.ServerLog;
import services.EventService;
import services.EventServiceImpl;
import utils.FileUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (
			Scanner sc = new Scanner(System.in);
			EventDAO eventDAO = new EventDAOImpl()
		) {
			System.out.println("Type path to logfile.txt: ");
			String pathName = sc.nextLine();
			Map<String, List<ServerLog>> serverLogsById = FileUtils.readServerLogsFromFile(pathName);

			EventService eventService = new EventServiceImpl(eventDAO);
			eventService.saveLogs(serverLogsById);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
