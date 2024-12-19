package com.springboot.cruddemo;

import com.springboot.cruddemo.dao.StudentDao;
import com.springboot.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.SplittableRandom;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

//	define new bean for command line runner : this is to crate command line runner application
//	CommandLineRunner -  from Springboot framework
//	Executed after spring bean loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao)
	{
      return runner->{

//		  createStudent(studentDao);
//		  createMultipleStudent(studentDao);
//		  readStudent(studentDao);
		  queryForStudent(studentDao);
//		  queryForStudentByLastName(studentDao);
//		  update(studentDao);
//		  deleteStudent(studentDao);
//		  deleteAllStudent(studentDao);
	  };

	}

	private void deleteAllStudent(StudentDao studentDao) {
        int numOfRowsDeleted  = studentDao.deleteAll();
		System.out.println("No of Rows Deleted "+ numOfRowsDeleted);
	}

	private void deleteStudent(StudentDao studentDao) {
		int id = 4;
		System.out.println("Deleting id :"+id);
		studentDao.delete(id);

	}


	private void update(StudentDao studentDao) {
		Student st1 = studentDao.findById(4);
		System.out.println("Current Student Details :"+ st1);
		st1.setLastName("Shyam Sundar");
		studentDao.updateStudent(st1);
		System.out.println("Updated Student Data :"+st1);

		/*  Update LastName for all students
		int numsRowUpdated  = entityManager.createQuery("udpate Student set lastName = 'Tester' "
		.executeUpdate();
		*  */
	}

	private void queryForStudentByLastName(StudentDao studentDao) {
		List<Student> studentList =  studentDao.findByLastName("Arjun");
		for(Student s : studentList)
		{
			System.out.println("Fetched Student Details "+s);
		}
	}

	private void queryForStudent(StudentDao studentDao) {
		List<Student> studentList = studentDao.findAll();

		for(Student s:studentList)
		{
			System.out.println("Student Deatils "+s);
		}
	}

	private void createMultipleStudent(StudentDao studentDao) {
		Student tempStduent1 = new Student("Ram" ,"Laskman" ,"ramlucky@gmail.com");
		Student tempStudent2 = new Student("Krishna","Arjun" ,"Kriarju@gmail.com");
		Student tempStudent3 = new Student("Bhargav" ,"Shyam" ,"Bhaggysham@gmail.com");

		studentDao.save(tempStduent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);
	}

	private void readStudent(StudentDao studentDao) {
//		Whateeve id you wll provide , the corresponding student details fetched out
		Student st = studentDao.findById(1);
		System.out.println("Found the Student" +st);
	}

	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating Student Object ");
		Student tempStudent =  new Student("Sai" ,"Kiran" , "saikiran@gmail.com");

//		save student object
		studentDao.save(tempStudent);

//		displaying student details
		System.out.println("Student Details : "+tempStudent.toString());
	}

}
