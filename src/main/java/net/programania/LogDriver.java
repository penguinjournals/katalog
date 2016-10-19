package net.programania;

import java.util.List;

public interface LogDriver {
	void persist(List<String> logBuffer);
	String retrieveLog(List<String> logBuffer);
}