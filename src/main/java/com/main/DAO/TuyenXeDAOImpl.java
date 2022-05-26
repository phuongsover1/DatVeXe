package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Tuyen_Xe;

@Repository
public class TuyenXeDAOImpl implements TuyenXeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tuyen_Xe getTuyenXe(int idDiemDi, int idDiemDen) {
        Session currentSession = sessionFactory.getCurrentSession();
        String sHQL = "FROM Tuyen_Xe TX WHERE TX.diaDiemDi.idDiaDiem = :idDiemDi"
                + " AND TX.diaDiemDen.idDiaDiem = :idDiemDen";
        Query<Tuyen_Xe> query = currentSession.createQuery(sHQL);
        query.setParameter("idDiemDi", idDiemDi);
        query.setParameter("idDiemDen", idDiemDen);

        List<Tuyen_Xe> tuyenXeList = query.getResultList();
        if (tuyenXeList.isEmpty())
            return null;
        return tuyenXeList.get(0);
    }

    @Override
    public List<Tuyen_Xe> getTuyenXeList() {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        String sHQL = "FROM Tuyen_Xe";
        Query<Tuyen_Xe> query = currentSession.createQuery(sHQL);
        List<Tuyen_Xe> tuyenXeList = query.getResultList();
        return tuyenXeList;
    }

    @Override
    public void save(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(tuyenXe);
    }

    @Override
    public Tuyen_Xe getTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Tuyen_Xe.class, idTuyenXe);

    }

    @Override
    public void deleteTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        Tuyen_Xe tuyenXe = currentSession.get(Tuyen_Xe.class, idTuyenXe);
        currentSession.delete(tuyenXe);
    }

    @Override
    public void update(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(tuyenXe);
    }

}
