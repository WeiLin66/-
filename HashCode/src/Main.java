public class Main {

    public static void main(String[] args) {

        int a = 50;
        System.out.println(((Integer)a).hashCode());

        int b = -94;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "code";
        System.out.println(d.hashCode());

        Student std = new Student("106618002", "A", 90, 90);
        System.out.println(std.hashCode());
    }
}
