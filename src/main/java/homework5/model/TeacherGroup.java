package src.main.java.homework5.model;

import lombok.Getter;
import lombok.ToString;
import src.main.java.homework5.service.iterator.TeacherGroupIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@ToString
public class TeacherGroup implements Iterable<Teacher> {

    private final List<Teacher> teachersList;

    public TeacherGroup() {
        this.teachersList = new ArrayList<>();
    }

    @Override
    public Iterator<Teacher> iterator() {
        return new TeacherGroupIterator(teachersList);
    }

}