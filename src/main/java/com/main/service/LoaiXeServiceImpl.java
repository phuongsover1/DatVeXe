package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.LoaiXeDAO;
import com.main.entity.Loai_Xe;

@Service
public class LoaiXeServiceImpl implements LoaiXeService {

	@Autowired
	private LoaiXeDAO loaiXeDAO;
	
	
	@Transactional
	@Override
	public List<Loai_Xe> getList() {
		// TODO Auto-generated method stub
		return loaiXeDAO.getList();
	}

	@Transactional
	@Override
	public Loai_Xe getLoaiXeFromId(int idLoaiXe) {
		// TODO Auto-generated method stub
		return loaiXeDAO.getLoaiXeFromId(idLoaiXe);
	}

}
