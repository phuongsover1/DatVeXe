package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.TuyenXeDAO;
import com.main.entity.Tuyen_Xe;

@Service
public class TuyenXeServiceImpl implements TuyenXeService {

    @Autowired
    TuyenXeDAO tuyenXeDAO;

    @Transactional
    @Override
    public Tuyen_Xe getTuyenXe(int idDiemDi, int idDiemDen) {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXe(idDiemDi, idDiemDen);
    }

    @Transactional
    @Override
    public List<Tuyen_Xe> getTuyenXeList() {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXeList();
    }

    @Transactional
    @Override
    public void save(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.save(tuyenXe);
    }

    @Transactional
    @Override
    public Tuyen_Xe getTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        return tuyenXeDAO.getTuyenXe(idTuyenXe);
    }

    @Transactional
    @Override
    public void deleteTuyenXe(int idTuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.deleteTuyenXe(idTuyenXe);
    }

    @Transactional
    @Override
    public void update(Tuyen_Xe tuyenXe) {
        // TODO Auto-generated method stub
        tuyenXeDAO.update(tuyenXe);
    }

}
