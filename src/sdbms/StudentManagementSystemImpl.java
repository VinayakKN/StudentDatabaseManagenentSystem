package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import CustomException.InvalidChoiceException;
import CustomException.StudentNotFoundException;
import CustomSorting.SortStudentByAge;
import CustomSorting.SortStudentById;
import CustomSorting.SortStudentByMarks;
import CustomSorting.SortSudentByName;

public class StudentManagementSystemImpl implements StudentManagementSystem{

	Scanner scanner = new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String, Student>(); 

	@Override
	public void addStudent() {
		System.out.println("Enter the Age:");
		int age = scanner.nextInt();
		System.out.println("Enter the Name");
		String name = scanner.next();
		System.out.println("Enter Student Marks:");
		int marks = scanner.nextInt();

		Student std = new Student(age, name, marks);
		db.put(std.getId(), std);
		System.out.println("Student Record Inserted Sucessfully");
		System.out.println("Student Id is->"+std.getId());
	}

	@Override
	public void displayStudent() {
		System.out.println("Enter the Student Id");
		String id = scanner.next().toUpperCase();

		if(db.containsKey(id)) {
			Student std = db.get(id);
			System.out.println("Id:"+std.getId());
			System.out.println("Name: "+std.getName());
			System.out.println("Age: "+std.getAge());
			System.out.println("Marks: "+std.getMarks());

		}
		else {
			try {
				String message="Student with the Id"+id+" is Not Found!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudent() {
		if(db.size()!=0) {
			System.out.println("Student Details are Follows");
			System.out.println("----------------------------");
			Set<String> keys = db.keySet();	// JSP101
			for(String key: keys) {
				Student value = db.get(key);	
				System.out.println(value);	//System.out.println(db.get(key));
			}
		}
		else {
			try {
				String message="Student Record Not Available to Display!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		System.out.println("Enter the Student Id");
		String id =scanner.next().toUpperCase();
		if(db.containsKey(id)) {
			System.out.println("Student record found.!");
			System.out.println(db.get(id));	// printing student object
			db.remove(id);
			System.out.println("Student Record Deleted Sucessfully");
		}
		else {
			try {
				String message="Student with the Id "+id+" is Not Found!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudent() {
		if(db.size()!=0) {
			System.out.println("Student Records Available "+db.size());
			db.clear();
			System.out.println("All Studens Records Deleted Sucessfully!");
			System.out.println("Student Records Available"+db.size());	
		}
		else {
			try {
				String message="Student Database is Empty!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter Student Id");
		String id = scanner.next().toUpperCase();

		if(db.containsKey(id)) {
			Student std = db.get(id);

			System.out.println("1: Update Age\n2: Update Name\n3: Update Marks");
			System.out.println("Enter the Choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1 : 
				System.out.println("Enter the Age");
				int age = scanner.nextInt();
				std.setAge(age);
				break;

			case 2:
				System.out.println("Enter the Name");
				String name = scanner.next();
				std.setName(name);
				break;

			case 3:
				System.out.println("Enter the Marks");
				int marks = scanner.nextInt();
				std.setMarks(marks);
				break;

			default:
				try {
					String message="Invalid Choice, Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} 
				catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		}
		else {
			try {
				String message="Student with the Id"+id+" is Not Found!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void countStudent() {
		System.out.println("No of Student Records: "+db.size());
	}

	@Override
	public void sortStudents() {
		if(db.size()>=2) {
			Set<String> keys = db.keySet();	// JSP101
			List<Student> list= new ArrayList<Student>();
			for(String key: keys) {
				list.add(db.get(key));	
			}

			System.out.println("1: Sort by Id\n2: Sort by Age\n3: Sort by Name\n4: Sort by Marks\nEnter choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				Collections.sort(list, new SortStudentById());
				display(list);
				break;

			case 2:
				Collections.sort(list, new SortStudentByAge());
				display(list);
				break;

			case 3:
				Collections.sort(list, new SortSudentByName());
				display(list);
				break;

			case 4:
				Collections.sort(list, new SortStudentByMarks());
				display(list);
				break;


			default:
				try {
					String message="Invalid Choice, Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} 
				catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		}
		else {
			try {
				String message="No Sufficient Student Objects to Compare!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void display(List<Student> list) {
		for(Student s: list) {
			System.out.println(s);
		}
	}


	@Override
	public void getStudentWithLowerMarks() {
		if( db.size() >= 2) {
			Set<String> keys =db.keySet();
			List<Student> list =new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(0));	// getting std object & printing it -> toString()
		}
		else {
			try {
				String message="No Sufficient Student Objects to Comapre!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithHigestMarks() {
		if( db.size()>=2) {
			Set<String> keys =db.keySet();
			List<Student> list =new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(list.size()-1));	
		}
		else {
			try {
				String message="No Sufficient Student Objects to Comapre!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
















