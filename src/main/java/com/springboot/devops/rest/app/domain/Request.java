package com.springboot.devops.rest.app.domain;

public class Request {
	
	private String message;
	
	private String to;
	
	private String from;
	
	private Long timeToLifeSec;
	
	public Request(String message, String to, String from, Long timeToLifeSec) {
		this.message = message;
		this.to = to;
		this.from = from;
		this.timeToLifeSec = timeToLifeSec;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Long getTimeToLifeSec() {
		return timeToLifeSec;
	}

	public void setTimeToLifeSec(Long timeToLifeSec) {
		this.timeToLifeSec = timeToLifeSec;
	}
}
