package dao;

import lombok.SneakyThrows;
import model.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EventDAOImpl implements EventDAO {

	private static final String DB_URL = "jdbc:hsqldb:hsql://localhost/testdb";
	private static final String DB_USER = "SA";
	private static final String DB_PASSWORD = "";

	private final Connection connection;

	@SneakyThrows
	public EventDAOImpl() {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	@Override
	public void createTableIfNotExists() {
		//language=SQL
		String sqlCreateTable =
			"CREATE TABLE IF NOT EXISTS Events ( " +
				"id VARCHAR(50) NOT NULL, " +
				"duration BIGINT, " +
				"type VARCHAR(50), " +
				"host VARCHAR(50), " +
				"alert BIT NOT NULL, " +
				"PRIMARY KEY (id)); ";
		try (PreparedStatement statement = connection.prepareStatement(sqlCreateTable)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(List<Event> events) {
		//language=SQL
		String sqlInsert = "INSERT INTO Events (id, duration, type, host, alert) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
			int count = 0;

			for (Event event : events) {
				System.out.println("Preparing to insert: " + event);
				setStatementParameters(statement, event);

				statement.addBatch();
				count++;
				executeBatchEvery100Rows(statement, count, events.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setStatementParameters(PreparedStatement statement, Event event) throws SQLException {
		statement.setString(1, event.getId());
		statement.setLong(2, event.getDuration());
		statement.setString(3, event.getType());
		statement.setString(4, event.getHost());
		statement.setBoolean(5, event.isAlert());
	}

	private void executeBatchEvery100Rows(PreparedStatement statement, int count, int eventsSize) throws SQLException {
		if (count % 100 == 0 || count == eventsSize) {
			statement.executeBatch();
		}
	}

	@Override
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
