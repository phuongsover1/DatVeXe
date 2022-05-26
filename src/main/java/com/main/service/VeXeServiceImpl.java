package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.VeXeDAO;
import com.main.entity.Ve_Xe;

@Service
public class VeXeServiceImpl implements VeXeService {

	@Autowired
	private VeXeDAO veXeDAO;

	@Transactional
	@Override
	public void luuVe(Ve_Xe ve) {
		veXeDAO.luuVe(ve);
	}


	@Transactional
	@Override
	public List<Ve_Xe> getVeXeList(int userId) {
		// TODO Auto-generated method stub
		return veXeDAO.getVeXeList(userId);
	}

	@Transactional
	@Override
	public Ve_Xe getVeXe(int idVe) {
		return veXeDAO.getVeXe(idVe);
	}

	@Transactional
	@Override
	public List<Ve_Xe> getAllVeXeChuaThanhToanList() {
		return veXeDAO.getAllVeXeChuaThanhToanList();
	}

	@Transactional
	@Override
	public void updateVe(Ve_Xe tempVeXe) {
		veXeDAO.updateVe(tempVeXe);
	}

	@Transactional
	@Override
	public List<Ve_Xe> getAllVeXeDaThanhToanVaHuy(int idNhanVien) {
		return veXeDAO.getAllVeXeDaThanhToanVaHuy(idNhanVien);
	}

	@Transactional
	@Override
	public List<Ve_Xe> timKiemVe(int idKH,String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {

		return veXeDAO.timKiemVe(idKH,idVeString, dateInput, tuyenDuongInput, trangThaiInput);
	}

	@Transactional
	@Override
	public List<Ve_Xe> timKiemVeNV(String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {
		// TODO Auto-generated method stub
		return veXeDAO.timKiemVeNV(idVeString, dateInput, tuyenDuongInput, trangThaiInput);
	}

}