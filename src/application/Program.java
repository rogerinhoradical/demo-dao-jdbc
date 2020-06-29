package application;

import java.util.List;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=======TEST 1: seller findById=======");
		java.util.Scanner ler = new java.util.Scanner(System.in);
		Seller seller1 = sellerDao.findById(3);
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
		
		System.out.println("\n=======TEST 4: seller insert=======");
		Seller seller4 = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.0, new Department(2, null));
		sellerDao.insert(seller4);
		System.out.println("Inserted! new id = "+ seller4.getId());
		
		System.out.println("\n=======TEST 5: seller update=======");
		seller1 = sellerDao.findById(1);
		seller1.setName("Marthe Wayne");
		sellerDao.update(seller1);
		System.out.println("Update completed!");
		
		System.out.println("\n=======TEST 6: seller delete=======");
		System.out.println("Enter id for delete test: ");
		sellerDao.deleteById(ler.nextInt());
		System.out.println("Delete completed ");
		
		ler.close();
	}
}
