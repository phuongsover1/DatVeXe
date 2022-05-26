package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.XeDAO;
import com.main.entity.Xe;

@Service
public class XeServiceImpl implements XeService {

	@Autowired
	private XeDAO xeDAO;
	
	@Transactional
	@Override
	public Xe getXe(int idXe) {
		// TODO Auto-generated method stub
		return xeDAO.getXe(idXe);
	}

	@Transactional
	@Override
	public Xe layXeTuBienSoXe(String bienSoXe) {
		// TODO Auto-generated method stub
		return xeDAO.getXeTuBienSo(bienSoXe);
	}

	@Transactional
	@Override
	public void save(Xe xeMoi) {
		xeDAO.save(xeMoi);
		
	}

}
