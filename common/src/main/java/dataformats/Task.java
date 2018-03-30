package dataformats;

public class Task {

    private Long    taskId;
    private String  summary;
    private String  description;
    private Double  stake;
    private Long    timestamp;
    private Long    deadline;
    private Boolean completed;
    private Long    opponentId;
    private Long    tribblerId;

    public Task(Long taskId, String summary, String description, Double stake, Long timestamp, Long deadline, Boolean completed, Long opponentId, Long tribblerId) {
        this.taskId = taskId;
        this.summary = summary;
        this.description = description;
        this.stake = stake;
        this.timestamp = timestamp;
        this.deadline = deadline;
        this.completed = completed;
        this.opponentId = opponentId;
        this.tribblerId = tribblerId;
    }

    public boolean isValid() {
        return  taskId      != null &&
                summary     != null &&
                stake       != null &&
                timestamp   != null &&
                deadline    != null &&
                completed   != null &&
                opponentId  != null &&
                tribblerId  != null;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(Long opponentId) {
        this.opponentId = opponentId;
    }

    public Long getTribblerId() {
        return tribblerId;
    }

    public void setTribblerId(Long tribblerId) {
        this.tribblerId = tribblerId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", stake=" + stake +
                ", timestamp=" + timestamp +
                ", deadline=" + deadline +
                ", completed=" + completed +
                ", opponentId=" + opponentId +
                ", tribblerId=" + tribblerId +
                '}';
    }
}
