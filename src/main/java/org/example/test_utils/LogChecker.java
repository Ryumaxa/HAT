package org.example.test_utils;

import org.example.log_utils.LogReader;

import java.util.concurrent.*;

/**
 * Класс для реализации чтения логов в отдельном потоке
 */
public class LogChecker {

    public static boolean checkLogsInBackground(Runnable testAction, String... filters) {
        return checkLogsInBackground(testAction, 10, 10000, filters);
    }

    public static boolean checkLogsInBackground(Runnable testAction, int bufferSize, int timeoutMs, String... filters) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            LogReader logReader = new LogReader(bufferSize, timeoutMs);
            return logReader.checkLogsByFilter(filters);
        });

        try {
            testAction.run();
            return future.get(timeoutMs + 10, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw new RuntimeException("Проверка логов превысила таймаут", e);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при проверке логов", e);
        } finally {
            executor.shutdown();
        }

    }
}