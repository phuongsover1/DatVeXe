package com.main.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Xe;

@Repository
public class XeDAOImpl implements XeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Xe getXe(int idXe) {
		Session currentSession = sessionFactory.getCurrentSession();
		Xe tempXe = currentSession.get(Xe.class, idXe);
		return tempXe;
	}

	@Override
	public Xe getXeTuBienSo(String bienSoXe) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM Xe WHERE bienSoXe = :bienSoXe";
		List<Xe> result = currentSession.createQuery(sHQL).setParameter("bienSoXe", bienSoXe).getResultList();
		if (result == null || result.isEmpty())
			return null;
		return result.get(0);
	}

	@Override
	public void save(Xe xeMoi) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(xeMoi);
	}

}
