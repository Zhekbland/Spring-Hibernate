package zhekbland.demo;

import zhekbland.jdbc.DataOneToMany;
import zhekbland.jdbc.DatabaseCreator;

public class Test {


    public static void main(String[] args) {
       final DatabaseCreator databaseCreator = DataOneToMany.getInstance();

        final CreateInstructorDemo instructorDemo = CreateInstructorDemo.getInstance();

        instructorDemo.getInstructorCourses(1);
    }
}
