package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.ChuyenXeDAO;
import com.main.entity.Chuyen_Xe;


@Service
public class ChuyenXeServiceImpl implements ChuyenXeService {
	@Autowired
	ChuyenXeDAO chuyenXeDAO;
	
	
	@Transactional
	@Override
	public List<Chuyen_Xe> getChuyenXeThoaMan(String ngayDi, int idChuyenXe) {
		return chuyenXeDAO.getChuyenXeThoaMan(ngayDi, idChuyenXe);
	}

	@Transactional
	@Override
	public Chuyen_Xe getChuyenXe(int idChuyenXe) {
		
		return chuyenXeDAO.getChuyenXe(idChuyenXe);
	}

	@Transactional
	@Override
	public void save(Chuyen_Xe chuyenXeMoi) {
		chuyenXeDAO.save(chuyenXeMoi);
	}

	
	@Transactional
	@Override
	public List<Chuyen_Xe> getListChuyenXeThuocIdXe(int idXe) {
		// TODO Auto-generated method stub
		return chuyenXeDAO.getListChuyenXeThuocIdXe(idXe);
	}
	
	@Transactional
	@Override
	public List<Chuyen_Xe> getListChuyenXeChuaHoanThanh() {
		// TODO Auto-generated method stub
		return chuyenXeDAO.getListChuyenXeChuaHoanThanh();
	}

	@Transactional
	@Override
	public void update(Chuyen_Xe chuyenXeTemp) {
		// TODO Auto-generated method stub
		chuyenXeDAO.update(chuyenXeTemp);
	}

}
