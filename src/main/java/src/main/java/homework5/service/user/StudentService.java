package src.main.java.homework5.service.user;

import src.main.java.homework5.model.Student;
import src.main.java.homework5.model.User;
import src.main.java.homework5.repository.StudentRepository;
import src.main.java.homework5.repository.UserRepository;
import src.main.java.homework5.service.comparator.UserComparator;
import src.main.java.homework5.service.randomizer.StudentRandomizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService implements UserService<Student> {

    private final UserRepository<Student> studentRepository;
    private final StudentRandomizer studentRandomizer;

    public StudentService() throws IOException {
        this.studentRepository = StudentRepository.getInstance();
        this.studentRandomizer = new StudentRandomizer();
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber) {
        for (Student student : studentRepository.getAll()) {
            if (student.getFullName().equals(fullName)) {
                System.out.println("(createStudent:)'" + fullName + "' <- запись уже существует");
                return;
            }
        }
        Long id = studentRepository.getMaxId() + 1;
        Student student = new Student(id, fullName, age, phoneNumber);
        studentRepository.add(student);
    }

    @Override
    public void createRandom(Integer quantity) {
        for (int i = 0; i < quantity; i++) {
            create(studentRandomizer.getData().get(0),
                    Integer.valueOf(studentRandomizer.getData().get(1)),
                    studentRandomizer.getData().get(2)
            );
        }
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public List<Student> getAllUsersSortedByFirstName() {
        List<Student> students = studentRepository.getAll();
        Collections.sort(students);

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedByLastName() {
        List<Student> students = studentRepository.getAll();
        students.sort(new UserComparator<>());

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedByAge() {
        List<Student> students = studentRepository.getAll();
        students.sort((Comparator.comparing(User::getAge)));

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedById() {
        List<Student> students = studentRepository.getAll();
        students.sort((Comparator.comparing(User::getId)));

        return students;
    }

    @Override
    public void remove(String fullName) {
        studentRepository.remove(fullName);
    }

    @Override
    public void edit(String fullName, Integer age, String phoneNumber) {
        for (Student student : studentRepository.getAll()) {
            if (student.getFullName().equals(fullName)) {
                System.out.println("(editStudent:)'" + fullName + "' <- запись найдена");
                student.setAge(age);
                student.setPhoneNumber(phoneNumber);
                System.out.println("(editStudent:)*поля age и phoneNumber обновлены*");
                return;
            }
        }
        System.out.println("(editStudent:)'" + fullName + "' <- запись не найдена");
    }

    @Override
    public List<Student> getAvailableUsers() {
        List<Student> studentList = new ArrayList<>();
        for (Student student : studentRepository.getAll()) {
            if (student.getTeam_id() == null) {
                studentList.add(student);
            }
        }
        return studentList;
    }

}