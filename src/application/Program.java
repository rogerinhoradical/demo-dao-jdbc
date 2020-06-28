package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=======TEST 1: seller findById=======");
		java.util.Scanner ler = new java.util.Scanner(System.in);
		Seller seller = sellerDao.findById(ler.nextInt());
		
		
		
		System.out.println(seller);
		
	}
}
