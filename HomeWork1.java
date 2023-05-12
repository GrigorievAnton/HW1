import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

/**
 * ������� ���������� ��������� �������:
 *
 * 1. ��� ������ ����������� � ����� ���������� �������
 *      ���� ��� ������ ������� ����� - ��� ���� ������� ���������, �.�. ������ ������ HomeWorkN
 *      �� �������� ����� MyClass {} (��� � ����� �����)
 * 2. ������� ��������� ����������� ���� ��� ����� ��������.
 *      ���� �������� ���� �������� ���� ��������� ���� ?
 * 3. ��� �� ��������� ����� ������� ������ ������� ���� �������� ����� main.
 *      ??�����?? ��� ������ � ������ ������� �� JDK-17, ����� �������� �������� ��� � ����������
 *      ���������� JDK � ������� ����� � Settings -> Project Structure
 */
public class HomeWork1 {
    /*
    �������� ������� #1:

    � ���� ����� ��������� ����������� ������� private

    ����������� ����� �Student�
        ���� ������ - name:String, grade:Integer (+�����������, +�������)
        ����� announce - String announce() {} - ���������� ������ � ������� "/name/ ������ � /grade/ ������"

    ����������� ����� �Teacher�
        ���� ������ - name:String, students:Student[30] (+����������� �� name, +�������)
        ����� - void addStudent(Student student) {} - ��������� �������� � ������ ���������,
            ���� ����������� ��������� �������� ������������� �����������, ������ �� ������
        ����� - String[] rollCall() {} - ���������� ������ ����� �� ����������� ������ ������
            announce ���� ���������� �������������
     */
    public static class Student {
        public String getName() {
            return name;
        }

        public Integer getGrade() {
            return grade;
        }

        public Student(String name, Integer grade) {
            this.name = name;
            this.grade = grade;
        }

        // ������ ����� ���� ���������� ������ Student
        private String name;
        private Integer grade;
        String announce() {
            return name + " ������ � " + grade + " ������ ";
        }
    }

    public static class Teacher {
        public Teacher(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // ������ ����� ���� ���������� ������ Teacher
        private String name;

        public Student[] getStudents() {
            return students;
        }

        Student[] students = new Student[30];
        void addStudent(Student student) {
            for(int i = 0; i <= students.length; i++){
                if(students[i] == null) {
                    students[i] = student;
                    break;
                }
            }
        }
        String[] rollCall() {
            int f = 0;
            for(int i = 0; i < students.length; i++) {
                if(students[i] == null) {
                    f = i;
                    break;
                }
            }
            String[] result = new String[f];
            for(int i = 0; i < f; i++) {
                if(students[i] != null) {
                    result[i] = students[i].announce();
                }
            }
            return result;
        }

    }

    /*
    ��� ����� main - ����� play ��� �� ��������� �����
    ������ �� ����� � ������, ��� ��� �������� ��� ��� �� ��������� ���� �������
    �������� ������ � ����� ���������� �������, ��� ������ ��� ���������� �� ����� �� �����
    � ���� ���������� �� ����������, ���� �� ��� �� ��������, ����� �� ����������
    */
    public static void main(String[] args) {

        var student = new Student(STUDENT_NAME, STUDENT_GRADE);
        print("Student: ������� ��������", true);
        print("Student: ������ �����", Objects.equals(student.getName(), STUDENT_NAME));
        print("Student: ������ ������", Objects.equals(student.getGrade(), STUDENT_GRADE));
        print("Student: announce �������� ���", student.announce().contains(STUDENT_NAME));
        print("Student: announce �������� �����", student.announce().contains(STUDENT_GRADE.toString()));

        var teacher = new Teacher(TEACHER_NAME);
        print("Teacher: ������� ��������", true);
        print("Teacher: ������ �����", teacher.getName() == TEACHER_NAME);
        print("Teacher: ������ ���������", teacher.getStudents() != null);
        print("Teacher: ������ �������� ������ ���� �������� 30", teacher.getStudents().length == 30);

        teacher.addStudent(student);
        print("Teacher: ������� ���������� � ������", teacher.getStudents()[0] == student);
        String[] calls = teacher.rollCall();
        print("Teacher: ������ rollCall ������� �� ������� ��������", calls.length == 1);
        print("Teacher: � ������ ���������� ��� ��������", calls[0].contains(STUDENT_NAME));
    }

    /* ����������� ������ - ���� ������ ������ �� ���� */

    private static void print(String condition, Boolean act) {
        Function<String, String> yellow = str -> "\u001B[33m" + str + "\u001B[0m";
        System.out.print( "TEST CASE " + yellow.apply(constLen(condition, 55)));
        if (act) System.out.print("?"); else System.out.print("?");
        System.out.println();
    }

    private static String constLen(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        while (len-- - str.length() > 0) sb.append(" ");
        return sb.toString();
    }

    private final static String STUDENT_NAME = "NameStudent";
    private final static String TEACHER_NAME = "NameStudent";
    private final static Integer STUDENT_GRADE = 1;
}
