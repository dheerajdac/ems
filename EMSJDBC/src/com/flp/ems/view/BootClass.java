package com.flp.ems.view;

import java.util.Scanner;

public class BootClass {

	public static void main(String[] args) {
		menuSelection();
	}
	
	public static void menuSelection(){
		UserInteraction userInteraction = new UserInteraction();
		int choice;
		boolean flag=true;
		while(flag){
			System.out.println("\n\n\n\nEnter the choice");
			System.out.println("1. Add Employee");
			System.out.println("2. Modify Employee");
			System.out.println("3. Remove Employee");
			System.out.println("4. Search Employee");
			System.out.println("5. Get all Employee");
			System.out.println("6. Exit");
			Scanner sc =new Scanner(System.in);
			choice=sc.nextInt();
			switch(choice){
				case 1:
					userInteraction.AddEmployee();
					break;
				case 2:
					userInteraction.ModifyEmployee();
					break;
				case 3:
					userInteraction.RemoveEmployee();
					break;
				case 4:
					userInteraction.SearchEmployee();
					break;
				case 5:
					userInteraction.getAllEmployee();
					break;
				case 6:
					flag=false;
					break;
				default:
					System.out.println("Invalid Choice\ntry again\n\n");
			}
			
		}
	}
}
