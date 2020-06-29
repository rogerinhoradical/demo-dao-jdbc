package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
	
	public static void main(String[] args) {
		java.util.Scanner ler = new java.util.Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n=======TEST 1: department insert=======");
		System.out.print("Insert a new Department :");
		Department dep = new Department(null, ler.nextLine());
		departmentDao.insert(dep);
		System.out.println("Done, new Department ID: "+ dep.getId());
		
		
		System.out.println("\n=======TEST 2: department update=======");
		System.out.println("Insert a department id to update: ");
		int id = ler.nextInt();
		System.out.println("Insert a department name to update: ");
		String name = ler.next();
		dep = new Department(id, name);
		departmentDao.update(dep);
		
		System.out.println("\n=======TEST 3: department delete=======");
		System.out.println("Insert a department id to delete: ");
		id = ler.nextInt();
		departmentDao.deleteById(id);
		
		System.out.println("\n=======TEST 4: department findById=======");
		System.out.println("Insert a department id to search: ");
		id = ler.nextInt();
		Department novo = departmentDao.findById(id);
		System.out.println(novo);
	}
}
