package com.main.DAO;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Dia_Diem;

@Repository
public class DiaDiemDAOImpl implements DiaDiemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Dia_Diem> tatCaDiaDiem() {
        Session currentSession = sessionFactory.getCurrentSession();
        String sHQL = "FROM Dia_Diem";
        Query<Dia_Diem> query = currentSession.createQuery(sHQL);
        List<Dia_Diem> diaDiemList = query.getResultList();
        return diaDiemList;
    }

    @Override
    public Dia_Diem getDiaDiem(String tenDiaDiem) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        String sHQL = "FROM Dia_Diem WHERE tenDiaDiem = '" + tenDiaDiem + "'";
        Query<Dia_Diem> query = currentSession.createQuery(sHQL);
        List<Dia_Diem> diaDiemList = query.getResultList();
        if (diaDiemList.isEmpty())
            return null;
        return diaDiemList.get(0);
    }

    @Override
    public void save(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(diaDiem);
    }

    @Override
    public Dia_Diem getDiaDiem(int idDiaDiem) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        Dia_Diem temp = currentSession.get(Dia_Diem.class, idDiaDiem);
        Hibernate.initialize(temp.getTuyenXeDiList());
        Hibernate.initialize(temp.getTuyenXeDenList());
        return temp;
    }

    @Override
    public void delete(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(diaDiem);
    }

    @Override
    public void update(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(diaDiem);
    }
}
