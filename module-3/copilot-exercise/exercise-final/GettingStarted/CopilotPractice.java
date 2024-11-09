public class CopilotPractice {
    private String[] studentNames;

    public CopilotPractice(String[] studentNames) {
        this.studentNames = studentNames;
    }

    public String[] getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String[] studentNames) {
        this.studentNames = studentNames;
    }

    // Check if a student name is in the array
    public boolean hasStudent(String studentName) {
        for (String name : studentNames) {
            if (name.equals(studentName)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(String studentName) {
        String[] newStudentNames = new String[studentNames.length + 1];
        for (int i = 0; i < studentNames.length; i++) {
            newStudentNames[i] = studentNames[i];
        }
        newStudentNames[studentNames.length] = studentName;
        studentNames = newStudentNames;
    }

    public static void main(String[] args) {
        String[] studentNames = {"Alice", "Bob", "Charlie"};
        CopilotPractice practice = new CopilotPractice(studentNames);
        System.out.println(practice.hasStudent("Alice")); // true
        System.out.println(practice.hasStudent("David")); // false

        practice.addStudent("David");
    }
}
