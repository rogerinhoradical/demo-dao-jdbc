package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	
	public static void main(String[] args) {
		java.util.Scanner ler = new java.util.Scanner(System.in);
		
		System.out.println("\n=======TEST 1: department insert=======");
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		System.out.print("Insert a new Department :");
		Department dep = new Department(null, ler.nextLine());
		departmentDao.insert(dep);
		System.out.println("Done, new Department ID: "+ dep.getId());
		
		
		
	}
}
