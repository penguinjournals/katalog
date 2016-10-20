package net.programania;

import java.util.List;

interface LogDriver {
	void persist(List<String> logBuffer);
}