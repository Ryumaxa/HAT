package org.example.log_utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Инструмент для чтения логов через ADB и поиска логов по фильтру
 */
public class LogReader {
    private final int logcatDelay; // Задержка на формирование лога после воздействия, мс
    private final LogBuffer<String> buffer;

    public LogReader(int bufferSize, int logcatDelay) {
        this.buffer = new LogBuffer<>(bufferSize);
        this.logcatDelay = logcatDelay;
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
            while ((line = reader.readLine()) != null && (System.currentTimeMillis() - startSearchingTime <= logcatDelay)) {
                if (containsAllSubstrings(line, filters)) {
                    buffer.add(line);
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Поймалось исключение: " + e.getMessage());
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
