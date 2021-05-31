package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
	private String id;
	private Long duration;
	private String type;
	private String host;
	private boolean alert;
}
