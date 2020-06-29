package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection  conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
		
	}

	@Override
	public void deleteById(Integer id) {
		
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*, department.Name as DepName "
					+"from seller as seller "
					+"INNER JOIN department as department "
					+"on DepartmentId = department.Id "
					+"where seller.Id = ? ");
					
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = instanciateDepartment(rs);
				Seller obj = instanciateSeller(rs, dep);
				return obj;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name AS DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name");
			
			rs = st.executeQuery();
			List<Seller> obj = findSemRepeticao(rs, st);
			
			return obj;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name AS DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name");
			
			st.setInt(1, department.getId());
			
			List<Seller> obj = findSemRepeticao(rs, st);
			
			return obj;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
		
	public Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	public Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}
	
	public List<Seller> findSemRepeticao(ResultSet rs, PreparedStatement st) throws SQLException{
		rs = st.executeQuery();
		List<Seller> obj = new ArrayList<>();
		
		Map<Integer, Department> map = new HashMap<>();
		while(rs.next()) {
			
			Department dep = map.get(rs.getInt("DepartmentId"));
			
			if(dep == null) {
				dep = instanciateDepartment(rs);
				map.put(rs.getInt("DepartmentId"), dep);
			}
			
			Seller seller = instanciateSeller(rs, dep);
			obj.add(seller);
			
		}
		return obj;
	}
}
