package org.example.log_utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Инструмент для чтения логов через ADB и поиска логов по фильтру
 */
// TODO: предусмотреть логику фильтрации по ИЛИ
public class LogReader {
	public final boolean PRINT_OK_LOGS = false;
	public final boolean PRINT_ALL_LOGS = false;
	
    private final int logcatDelay;
    private final LogBuffer<String> buffer;
    private int scopeCounter;

    public LogReader(int bufferSize, int logcatDelay) {
        this.buffer = new LogBuffer<>(bufferSize);
        this.logcatDelay = logcatDelay;
        this.scopeCounter = 0;
    }

    private void searchLogs(String... requiredSubstrings) {
        long startSearchingTime = System.currentTimeMillis();
        List<String> filters = List.of(requiredSubstrings);
        try {
            Process clearProcess = new ProcessBuilder("adb", "logcat", "-c").start();
            clearProcess.waitFor();
            Process process = new ProcessBuilder("adb", "logcat", "-v", "threadtime").start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null && (System.currentTimeMillis() - startSearchingTime <= logcatDelay) && buffer.isEmpty()) {
				if(PRINT_ALL_LOGS) System.out.println(line);
                if (line.contains("{")) scopeCounter++;
                if (line.contains("}")) scopeCounter--;

                if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
                stringBuilder.append(line);

                if (scopeCounter <= 0) {
                    if (containsAllSubstrings(stringBuilder.toString(), filters)) {
                        buffer.add(stringBuilder.toString());
	                    if(PRINT_OK_LOGS) System.out.println(stringBuilder);
                    }
                    stringBuilder.setLength(0);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogsByFilter(String... requiredSubstrings) {
        buffer.clear();
        searchLogs(requiredSubstrings);
        return !buffer.isEmpty();
    }

    private static boolean containsAllSubstrings(String line, List<String> substrings) {
        for (String substring : substrings) {
            if (!line.contains(substring)) {
                return false;
            }
        }
        return true;
    }
}
