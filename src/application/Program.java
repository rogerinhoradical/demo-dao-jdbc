package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=======TEST 1: seller findById=======");
		java.util.Scanner ler = new java.util.Scanner(System.in);
		Seller seller1 = sellerDao.findById(1);
		System.out.println(seller1);
		
		System.out.println("\n=======TEST 2: seller findByDepartment=======");
		List<Seller>seller2 = sellerDao.findByDepartment(new Department(2, null));
		for (Seller obj : seller2) {
			System.out.println(obj);
		}
		
		System.out.println("\n=======TEST 3: seller findAll=======");
		List<Seller>seller3 = sellerDao.findAll();
		for (Seller obi : seller3) {
			System.out.println(obi);
		}
	}
}
