package org.example.log_utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Инструмент для чтения логов через ADB и поиска логов по фильтру
 */
// TODO: предусмотреть логику фильтрации по ИЛИ
// TODO: сделать, чтобы логи в несколько строк записывались в одну (иначе проблемка)
public class LogReader {
    private final int logcatDelay; // Задержка на формирование лога после воздействия, мс
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
//                System.out.println(line);
                if (line.contains("{")) scopeCounter++;
                if (line.contains("}")) scopeCounter--;

                if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
                stringBuilder.append(line);

                if (scopeCounter <= 0) {
                    if (containsAllSubstrings(stringBuilder.toString(), filters)) {
                        buffer.add(stringBuilder.toString());
                        System.out.println(stringBuilder);
                    }
                    stringBuilder.setLength(0);
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

    private void accumulateFilteredBuffer(String line, List<String> filters) {


        if (line.contains("{")) scopeCounter++;
        if (line.contains("}")) scopeCounter--;


        if (containsAllSubstrings(line, filters)) {
            buffer.add(line);
        }
    }
}
