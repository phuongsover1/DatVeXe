package com.main.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.entity.Ve_Xe;

@Repository
public class VeXeDAOImpl implements VeXeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void luuVe(Ve_Xe ve) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(ve);

    }

    @Override
    public List<Ve_Xe> getVeXeList(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createSQLQuery("EXEC GET_VE_XE_LIST :userId").addEntity(Ve_Xe.class)
                .setParameter("userId", userId);
        List<Ve_Xe> result = query.list();
        return result;
    }

    @Override
    public Ve_Xe getVeXe(int idVe) {
        Session currentSession = sessionFactory.getCurrentSession();
        Ve_Xe tempVeXe = currentSession.get(Ve_Xe.class, idVe);
        return tempVeXe;
    }

    @Override
    public List<Ve_Xe> getAllVeXeChuaThanhToanList() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createSQLQuery("EXEC GET_ALL_VE_XE_CHUA_THANH_TOAN").addEntity(Ve_Xe.class);
        List<Ve_Xe> result = query.list();
        return result;
    }

    @Override
    public void updateVe(Ve_Xe tempVeXe) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(tempVeXe);
        ;
    }

    @Override
    public List<Ve_Xe> getAllVeXeDaThanhToanVaHuy(int idNhanVien) {
        Session currentSession = sessionFactory.getCurrentSession();
        List<Ve_Xe> veXeList = currentSession.createQuery("from Ve_Xe vx where vx.idNhanVien.idNhanVien = " + idNhanVien)
                                .getResultList();
        return veXeList;
    }

    @Override
    public List<Ve_Xe> timKiemVe(int idKH, String idVeString, String dateInput, String tuyenDuongInput,
                                 String trangThaiInput) {
        Session currentSession = sessionFactory.getCurrentSession();

        List<Ve_Xe> result;
        if (!idVeString.equals("")) {
            int idVe = Integer.parseInt(idVeString);
            Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE :idKH ,:maVe").addEntity(Ve_Xe.class)
                    .setParameter("maVe", idVe).setParameter("idKH", idKH);
            result = query.list();
        } else {
            if (!dateInput.equals("") && tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) // o x x
            {
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE :idKH, DEFAULT,'" + dateInput + "'")
                        .addEntity(Ve_Xe.class).setParameter("idKH", idKH);
                result = query.list();
                ///////////////////////////////////////// sửa lại không dùng setParameter nữa
            } else if (!dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && trangThaiInput.equals("default")) { // o o x
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);
                Query query = currentSession
                        .createSQLQuery(
                                "EXEC TIM_KIEM_VE :idKH,DEFAULT,'" + dateInput + "'," + diaDiemDi + "," + diaDiemDen)
                        .addEntity(Ve_Xe.class).setParameter("idKH", idKH);
                result = query.list();
            } else if (!dateInput.equals("") && tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // o x o
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE :idKH,DEFAULT,'" + dateInput
                        + "', DEFAULT, DEFAULT, N'" + trangThaiInput + "'").addEntity(Ve_Xe.class)
                        .setParameter("idKH", idKH);
                result = query.list();
            } else if (!dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // o o o
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession
                        .createSQLQuery("EXEC TIM_KIEM_VE :idKH,DEFAULT, '" + dateInput + "', " + diaDiemDi + ", "
                                + diaDiemDen + ", N'" + trangThaiInput + "'")
                        .addEntity(Ve_Xe.class).setParameter("idKH", idKH);
                result = query.list();
            } else if (dateInput.equals("") && !tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) { // x
                // o
                // x
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession
                        .createSQLQuery("EXEC TIM_KIEM_VE :idKH,DEFAULT, DEFAULT, " + diaDiemDi + "," + diaDiemDen)
                        .addEntity(Ve_Xe.class).setParameter("idKH", idKH);
                result = query.list();

            } else if (dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // x o o
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE :idKH,DEFAULT, DEFAULT, " + diaDiemDi
                        + ", " + diaDiemDen + ", N'" + trangThaiInput + "'").addEntity(Ve_Xe.class)
                        .setParameter("idKH", idKH);
                result = query.list();
            } else if (dateInput.equals("") && tuyenDuongInput.equals("default") && !trangThaiInput.equals("default")) { // x
                // x
                // o
                Query query = currentSession
                        .createSQLQuery(
                                "EXEC TIM_KIEM_VE :idKH,DEFAULT, DEFAULT, DEFAULT, DEFAULT, N'" + trangThaiInput + "'")
                        .addEntity(Ve_Xe.class).setParameter("idKH", idKH);
                result = query.list();
            } else {
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE :idKH").addEntity(Ve_Xe.class)
                        .setParameter("idKH", idKH);
                result = query.list();
            }
        }

        return result;
    }


    @Override
    public List<Ve_Xe> timKiemVeNV(String idVeString, String dateInput, String tuyenDuongInput, String trangThaiInput) {
        Session currentSession = sessionFactory.getCurrentSession();

        List<Ve_Xe> result;
        if (!idVeString.equals("")) {
            int idVe = Integer.parseInt(idVeString);
            Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE_NV :maVe").addEntity(Ve_Xe.class)
                    .setParameter("maVe", idVe);
            result = query.list();
        } else {
            if (!dateInput.equals("") && tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) // o x x
            {
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE_NV DEFAULT,'" + dateInput + "'")
                        .addEntity(Ve_Xe.class);
                result = query.list();
                ///////////////////////////////////////// sửa lại không dùng setParameter nữa
            } else if (!dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && trangThaiInput.equals("default")) { // o o x
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);
                Query query = currentSession
                        .createSQLQuery(
                                "EXEC TIM_KIEM_VE_NV DEFAULT,'" + dateInput + "'," + diaDiemDi + "," + diaDiemDen)
                        .addEntity(Ve_Xe.class);
                result = query.list();
            } else if (!dateInput.equals("") && tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // o x o
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE_NV DEFAULT,'" + dateInput
                        + "', DEFAULT, DEFAULT, N'" + trangThaiInput + "'").addEntity(Ve_Xe.class);
                result = query.list();
            } else if (!dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // o o o
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession
                        .createSQLQuery("EXEC TIM_KIEM_VE_NV DEFAULT, '" + dateInput + "', " + diaDiemDi + ", "
                                + diaDiemDen + ", N'" + trangThaiInput + "'")
                        .addEntity(Ve_Xe.class);
                result = query.list();
            } else if (dateInput.equals("") && !tuyenDuongInput.equals("default") && trangThaiInput.equals("default")) { // x
                // o
                // x
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession
                        .createSQLQuery("EXEC TIM_KIEM_VE_NV DEFAULT, DEFAULT, " + diaDiemDi + "," + diaDiemDen)
                        .addEntity(Ve_Xe.class);
                result = query.list();

            } else if (dateInput.equals("") && !tuyenDuongInput.equals("default")
                    && !trangThaiInput.equals("default")) { // x o o
                // tách tuyến đường input ra thành 2 địa điểm đi đến
                String[] tuyenDuongArray = tuyenDuongInput.split(",");
                int diaDiemDi = Integer.parseInt(tuyenDuongArray[0]);
                int diaDiemDen = Integer.parseInt(tuyenDuongArray[1]);

                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE_NV DEFAULT, DEFAULT, " + diaDiemDi
                        + ", " + diaDiemDen + ", N'" + trangThaiInput + "'").addEntity(Ve_Xe.class);
                result = query.list();
            } else if (dateInput.equals("") && tuyenDuongInput.equals("default") && !trangThaiInput.equals("default")) { // x
                // x
                // o
                Query query = currentSession
                        .createSQLQuery(
                                "EXEC TIM_KIEM_VE_NV DEFAULT, DEFAULT, DEFAULT, DEFAULT, N'" + trangThaiInput + "'")
                        .addEntity(Ve_Xe.class);
                result = query.list();
            } else {
                Query query = currentSession.createSQLQuery("EXEC TIM_KIEM_VE_NV").addEntity(Ve_Xe.class);
                result = query.list();
            }
        }

        return result;
    }

}