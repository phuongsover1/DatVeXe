package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Loai_Xe;

@Repository
public class LoaiXeDAOImpl implements LoaiXeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Loai_Xe> getList() {
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM Loai_Xe";
		Query<Loai_Xe> query = currentSession.createQuery(sHQL);
		List<Loai_Xe> list = query.getResultList();
		return list;
	}

	@Override
	public Loai_Xe getLoaiXeFromId(int idLoaiXe) {
		Session currentSession = sessionFactory.getCurrentSession();
		return ((Loai_Xe) currentSession.get(Loai_Xe.class, idLoaiXe));
	}

}
