package com.example.retrofitroom.models;

public class AllocationRequest {
    private Long courseId;
    private String day;
    private String end;
    private String start;
    private Long teacherId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "AllocationRequest{" +
                "courseId=" + courseId +
                ", day='" + day + '\'' +
                ", end='" + end + '\'' +
                ", start='" + start + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }
}
