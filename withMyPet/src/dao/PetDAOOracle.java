package dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.Pet;
import exception.AddException;
import exception.DeleteException;
import exception.UpdateException;
@Repository("PetDAO")
public class PetDAOOracle implements PetDAO {

	@Autowired
	private SqlSession session;

	
	@Override
	public void insert(Pet p) throws AddException {
		// TODO Auto-generated method stub
		session.insert("PetMapper.insert",p);
	}

	@Override
	public ArrayList<Pet> selectByPetName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Pet p) throws DeleteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void revise(Pet p) throws UpdateException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Pet> selectByEmail(String email) {
		// TODO Auto-generated method stub
		ArrayList<Pet> petList = new ArrayList<Pet>();
		petList = (ArrayList)session.selectList("PetMapper.selectByEmail",email);
		return petList;
	}

	@Override
	public void deleteByPetNo(int petNo) {
		// TODO Auto-generated method stub
		session.delete("PetMapper.deleteByPetNo",petNo);
	}

}
