package fsoft.tanvx.api.composite.course;

import fsoft.tanvx.api.core.student.Student;

import java.util.List;

public record CourseAggregate(int courseId, int like, int dislike, int registeredUserNumber, List<Student> registerUserDetails) {
}
