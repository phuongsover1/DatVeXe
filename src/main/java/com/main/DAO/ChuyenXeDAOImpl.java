package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Chuyen_Xe;

@Repository
public class ChuyenXeDAOImpl implements ChuyenXeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Chuyen_Xe> getChuyenXeThoaMan(String ngayDi, int idTuyenXe) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createSQLQuery("EXEC Lay_Chuyen_Xe_Trong_Ngay :Ngay,:idTuyenXe").addEntity(Chuyen_Xe.class)
																									 .setParameter("Ngay", ngayDi)
																									 .setParameter("idTuyenXe", idTuyenXe);
		List<Chuyen_Xe> result = query.list();
		return result;
	}

	@Override
	public Chuyen_Xe getChuyenXe(int idChuyenXe) {
		Session currentSession = sessionFactory.getCurrentSession();
		Chuyen_Xe tempUser = currentSession.get(Chuyen_Xe.class, idChuyenXe);
		return tempUser;
	}

	@Override
	public void save(Chuyen_Xe chuyenXeMoi) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(chuyenXeMoi);
		
	}

	@Override
	public List<Chuyen_Xe> getListChuyenXeThuocIdXe(int idXe) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM Chuyen_Xe WHERE maXe.idXe = :idXe" + " AND daHoanThanh = 0";
		Query<Chuyen_Xe> query =  currentSession.createQuery(sHQL)
								  .setParameter("idXe", idXe);
		List<Chuyen_Xe> result = query.getResultList();
		if (result.isEmpty())
			return null;
		return result;
		
	}

	@Override
	public List<Chuyen_Xe> getListChuyenXeChuaHoanThanh() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		String sHQL = "FROM Chuyen_Xe WHERE daHoanThanh = 0";
		Query<Chuyen_Xe> query = currentSession.createQuery(sHQL);
		List<Chuyen_Xe> result = query.getResultList();
		if (result.isEmpty())
			return null;
		return result;
	}

	@Override
	public void update(Chuyen_Xe chuyenXeTemp) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.update(chuyenXeTemp);
	}

}
