package com.crossal.task;

public enum TaskStatus {
    QUEUED("QUEUED"), RUNNING("RUNNING"), SUCCESS("SUCCESS"), FAIL("FAIL");

    private String text;

    TaskStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TaskStatus fromString(String text) {
        for (TaskStatus b : TaskStatus.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
