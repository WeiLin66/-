public class Student {

    private String stuId;
    private String classId;
    private int math;
    private int computer;

    public Student(String stuId, String classId, int math, int computer) {
        this.stuId = stuId;
        this.classId = classId;
        this.math = math;
        this.computer = computer;
    }

    @Override
    public int hashCode() {
        int B = 53;
        int hash = 0;

        hash = hash * B + stuId.toLowerCase().hashCode(); // 忽略大小寫
        hash = hash * B + classId.toLowerCase().hashCode();
//        hash = hash * B + stuId.hashCode();
//        hash = hash * B + classId.hashCode();
        hash = hash * B + math;
        hash = hash * B + computer;

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }

        if(o == null){
            return false;
        }

        if(getClass() != o.getClass()){
            return false;
        }

        Student another = (Student) o;

        return this.stuId.toLowerCase().equals(another.stuId.toLowerCase())
                && this.classId.toLowerCase().equals(another.classId.toLowerCase()) &&
                this.math == another.math && this.computer == another.computer;
    }
}
