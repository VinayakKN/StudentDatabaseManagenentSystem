package sdbms;

import java.util.Scanner;

import CustomException.InvalidChoiceException;

public class Solution {

	public static void main(String[] args) {
		System.out.println("Welcome to Student Database Management System");
		System.out.println("-----------------------------------------------");

		Scanner scanner = new Scanner(System.in);
		StudentManagementSystem sms = new StudentManagementSystemImpl();
		
		while(true) {
			System.out.println("1: Add Student\n2: Display Student\n3: Dispaly All Student\n4: Remove Student\n5: Remove All Student\n6: Update Student\n7: Count Students\n8: Sort Student\n9: Get Student with Lower marks\n10: Get Student with Higest Marks\n11: Exit"+ "");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				sms.addStudent();
				break;

			case 2:
				sms.displayStudent();
				break;

			case 3:
				sms.displayAllStudent();
				break;

			case 4:
				sms.removeStudent();
				break;

			case 5:
				sms.removeAllStudent();
				break;

			case 6:
				sms.updateStudent();
				break;

			case 7:
				sms.countStudent();
				break;

			case 8:
				sms.sortStudents();
				break;
				

			case 9:
				sms.getStudentWithLowerMarks();
				break;

			case 10:
				sms.getStudentWithHigestMarks();
				break;

			case 11:
				System.out.println("Thank You...!");
				System.exit(0);
				
			default:
				try {
					throw new InvalidChoiceException("Invalid Choice, Enter valid choice");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			System.out.println("--------------------------------------------");
		}	// end of while loop
	}	//end of main method
}	// end of class
