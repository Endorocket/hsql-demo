package model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ServerLog implements Serializable {
	private String id;
	private State state;
	private long timestamp;
	private String type;
	private String host;
}
