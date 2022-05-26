package com.main.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.main.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.main.service.AccountService;
import com.main.service.DiaDiemService;
import com.main.service.NhanVienService;
import com.main.service.TuyenXeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/quanly")
public class ManagerController {

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    AccountService accountService;

    @Autowired
    TuyenXeService tuyenXeService;

    @Autowired
    DiaDiemService diaDiemService;

    static String months[] =
            {
                    null , "January" , "February" , "March" , "April", "May",
                    "June", "July", "August", "September", "October",
                    "November", "December"
            };

    @RequestMapping("/")
    public String managerIndex(Model model, @RequestParam(name = "isShowList", required = false) boolean isShowList,
            @RequestParam(name = "isThemNV", required = false) boolean isThemNV,
            @RequestParam(name = "isQuanLyTuyenXe", required = false) boolean isQuanLyTuyenXe,
            @RequestParam(name = "isThemTuyenXe", required = false) boolean isThemTuyenXe,
            @RequestParam(name = "isQuanLyDiaDiem", required = false) boolean isQuanLyDiaDiem,
            @RequestParam(name = "isThemDiaDiem", required = false) boolean isThemDiaDiem) {
        System.out.println(isShowList);

        // Lấy list danh sách nhân viên
        if (isShowList) {
            List<Nhan_Vien> nhanVienList = nhanVienService.getListNV();
            model.addAttribute("nvList", nhanVienList);
        }
        if (isThemNV) {
            model.addAttribute("nhanVien", new Nhan_Vien());
        }
        if (isQuanLyTuyenXe) {
            List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
            model.addAttribute("tuyenXeList", tuyenXeList);
        }
        if (isThemTuyenXe) {
            model.addAttribute("tuyenXe", new Tuyen_Xe());
            model.addAttribute("places", diaDiemService.tatCaDiaDiem());
        }
        if (isQuanLyDiaDiem) {
            model.addAttribute("places", diaDiemService.tatCaDiaDiem());
        }
        if (isThemDiaDiem) {
            model.addAttribute("diaDiem", new Dia_Diem());
        }

        return "/manager/manager";
    }

    @PostMapping("/xoaNV")
    public String xoaNV(@RequestParam("idNV") int nvID) {
        nhanVienService.xoaNV(nvID);
        return "redirect:/quanly/";
    }

    @PostMapping("/themNV")
    public String themNV(@ModelAttribute("nhanVien") Nhan_Vien nhanVien, @RequestParam("dd") String dd,
            @RequestParam("mm") String mm, @RequestParam("yyyy") String yyyy, @RequestParam("bdlv-dd") String bdlv_dd,
            @RequestParam("bdlv-mm") String bdlv_mm, @RequestParam("bdlv-yyyy") String bdlv_yyyy,
            @RequestParam("password") String password, Model model) {
        Account tempAccount = accountService.getAccountFromUsername(nhanVien.getSoDienThoai());
        if (tempAccount != null) {
            model.addAttribute("tontaiSDT", true);
            return "redirect:/quanly/?isThemNV=true";
        }
        if (mm.length() == 1)
            mm = "0".concat(mm);
        if (bdlv_mm.length() == 1)
            bdlv_mm = "0".concat(bdlv_mm);
        nhanVien.setNgaySinh(yyyy + "-" + mm + "-" + dd);
        nhanVien.setNgayBDLamViec(bdlv_yyyy + "-" + bdlv_mm + "-" + bdlv_dd);
        Account newAccount = new Account(nhanVien.getSoDienThoai(), "{noop}" + password);
        Role nvRole = new Role("ROLE_EMPLOYEE");
        newAccount.addRole(nvRole);
        nhanVien.setIdTaiKhoan(newAccount);
        nhanVienService.themNV(nhanVien);

        return "redirect:/quanly/?isShowList=true";
    }

    @PostMapping(value = "xuLyTuyenXe", params = "add")
    public String themTuyenXe(@ModelAttribute("tuyenXe") Tuyen_Xe tuyenXe, Model model) {
        System.err.println(tuyenXe);
        // Kiểm tra tồn tại của địa điểm đi và đến
        boolean tonTaiDiemDi = false;
        boolean tonTaiDiemDen = false;
        Dia_Diem diaDiemDi = null;
        Dia_Diem diaDiemDen = null;
        List<Dia_Diem> diaDiemList = diaDiemService.tatCaDiaDiem();
        for (Dia_Diem diaDiem : diaDiemList) {
            if (diaDiem.getTenDiaDiem().equals(tuyenXe.getDiaDiemDi().getTenDiaDiem())) {
                tonTaiDiemDi = true;
                diaDiemDi = diaDiem;

            }
            if (diaDiem.getTenDiaDiem().equals(tuyenXe.getDiaDiemDen().getTenDiaDiem())) {
                tonTaiDiemDen = true;
                diaDiemDen = diaDiem;
            }
        }
        if (!tonTaiDiemDi || !tonTaiDiemDen) {
            model.addAttribute("diaDiemKhongTonTai", true);
            return "redirect:/quanly/?isThemTuyenXe=true";
        }
        Tuyen_Xe tempTuyenXe = tuyenXeService.getTuyenXe(diaDiemDi.getIdDiaDiem(), diaDiemDen.getIdDiaDiem());
        if (tempTuyenXe != null) {
            model.addAttribute("tonTaiTuyenXe", true);
            System.err.println(tempTuyenXe);
            return "redirect:/quanly/?isThemTuyenXe=true";
        }
        tuyenXe.setDiaDiemDi(diaDiemDi);
        tuyenXe.setDiaDiemDen(diaDiemDen);
        tuyenXeService.save(tuyenXe);
        return "redirect:/quanly/?isQuanLyTuyenXe=true";
    }

    @PostMapping(value = "xuLyTuyenXe", params = "update")
    public String updateTuyenXe(@ModelAttribute("tuyenXe") Tuyen_Xe tuyenXe, @RequestParam("idTuyenXe") int idTuyenXe,
            @RequestParam("diaDiemDiCu") String diaDiemDiCu, @RequestParam("diaDiemDenCu") String diaDiemDenCu,
            Model model) {
        // Kiểm tra sự trùng tuyến xe
        List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
        for (int i = 0; i < tuyenXeList.size(); i++) {
            if (tuyenXeList.get(i).getDiaDiemDi().getTenDiaDiem().equals(diaDiemDiCu)
                    && tuyenXeList.get(i).getDiaDiemDen().getTenDiaDiem().equals(diaDiemDenCu)) {
                tuyenXeList.remove(i);
                break;
            }
        }
        for (Tuyen_Xe temp : tuyenXeList) {
            if (temp.getDiaDiemDi().getTenDiaDiem().equals(tuyenXe.getDiaDiemDi().getTenDiaDiem())
                    && temp.getDiaDiemDen().getTenDiaDiem().equals(tuyenXe.getDiaDiemDen().getTenDiaDiem())) {
                model.addAttribute("isUpdateTuyenXe", true);
                model.addAttribute("tonTaiTuyenXe", true);
                return "/manager/manager";
            }
        }
        tuyenXe.setIdTuyen(idTuyenXe);
        List<Dia_Diem> places = diaDiemService.tatCaDiaDiem();
        Dia_Diem diaDiemDi = null;
        Dia_Diem diaDiemDen = null;

        for (Dia_Diem place : places) {
            if (place.getTenDiaDiem().equals(tuyenXe.getDiaDiemDi().getTenDiaDiem())) {
                diaDiemDi = place;
            }
            if (place.getTenDiaDiem().equals(tuyenXe.getDiaDiemDen().getTenDiaDiem())) {
                diaDiemDen = place;
            }
        }

        System.err.println(diaDiemDi);
        System.err.println(diaDiemDen);
        tuyenXe.setDiaDiemDi(diaDiemDi);
        tuyenXe.setDiaDiemDen(diaDiemDen);
        System.err.println(tuyenXe);

        tuyenXeService.update(tuyenXe);
        return "redirect:/quanly/?isQuanLyTuyenXe=true";
    }

    @PostMapping(value = "xuLyTuyenXe", params = "deleteBtn")
    public String deleteTuyenXe(@RequestParam("idTuyenXe") int idTuyenXe, Model model) {
        // Chỉ được xóa khi không có chuyến xe nào chạy tuyến này
        Tuyen_Xe tuyenXe = tuyenXeService.getTuyenXe(idTuyenXe);
        if (tuyenXe.getChuyenXeList().size() > 0) {
            model.addAttribute("tonTaiChuyenXe", true);
            return "redirect:/quanly/?isQuanLyTuyenXe=true";
        }
        tuyenXeService.deleteTuyenXe(idTuyenXe);
        model.addAttribute("xoaTuyenXeThanhCong", true);
        return "redirect:/quanly/?isQuanLyTuyenXe=true";
    }

    @PostMapping(value = "xuLyTuyenXe", params = "updateBtn")
    public String updateTuyenXe(@RequestParam("idTuyenXe") int idTuyenXe, Model model) {
        // -Nếu tuyến xe không có, hoặc có chuyến xe (chưa có ai đặt vé, hoặc toàn bộ vé
        // đã bị hủy)
        // thì sửa bình thường
        Tuyen_Xe tuyenXe = tuyenXeService.getTuyenXe(idTuyenXe);
        // -- Tuyến xe không có chuyến xe nào
        if (tuyenXe.getChuyenXeList().isEmpty()) {
            model.addAttribute("tuyenXe", tuyenXe);
            System.err.println(tuyenXe);
            model.addAttribute("isUpdateTuyenXe", true);
            model.addAttribute("diPlaces", diaDiemService.tatCaDiaDiem());
            model.addAttribute("denPlaces", diaDiemService.tatCaDiaDiem());
            return "/manager/manager";
        } else { // -- Tuyến xe có chuyến xe
            /*
             * Lặp qua từng chuyến xe xem có ai đặt vé chưa bằng cách lặp qua danh sách các
             * vé xe của mỗi chuyến xe. +Nếu có: thì xem tất cả vé đó có đang bị hủy không
             * -Tất cả vé bị hủy -> update được -Có 1 vé chờ thanh toán hoặc đã thanh toán
             * -> không update được +Nếu không: update bình thường
             */
            // + Nếu có
            for (Chuyen_Xe chuyenXe : tuyenXe.getChuyenXeList()) {
                for (Ve_Xe veXe : chuyenXe.getVeXeList()) {
                    if (veXe.getTrangThai().equals("Chờ thanh toán") || veXe.getTrangThai().equals("Đã thanh toán")) {
                        model.addAttribute("daBiThanhToan", true);
                        return "redirect:/quanly/?isQuanLyTuyenXe=true";
                    }
                }
            }
            // + Nếu không
            model.addAttribute("tuyenXe", tuyenXe);
            System.err.println(tuyenXe);
            model.addAttribute("isUpdateTuyenXe", true);
            return "/manager/manager";
        }

        // -Nếu tuyến xe có chuyến xe mà đã có vé đặt
        // (chờ thanh toán hoặc đã thanh toán thì không thể update) tuyến xe

    }

    @PostMapping("themDiaDiem")
    public String themDiaDiem(@ModelAttribute("diaDiem") Dia_Diem diaDiem, Model model) {
        // Kiểm tra địa điểm tồn tại
        List<Dia_Diem> places = diaDiemService.tatCaDiaDiem();
        for (Dia_Diem temp : places) {
            if (temp.getTenDiaDiem().equals(diaDiem.getTenDiaDiem())) {
                model.addAttribute("tonTaiDiaDiem", true);
                return "redirect:/quanly/?isThemDiaDiem=true";
            }
        }
        diaDiemService.save(diaDiem);
        model.addAttribute("themDiaDiemThanhCong", true);
        return "redirect:/quanly/?isThemDiaDiem=true";
    }

    @PostMapping(value = "xuLyDiaDiem", params = "deleteDiaDiemBtn")
    public String deleteDiaDiem(@RequestParam("idDiaDiem") int idDiaDiem, Model model) {

        // -Nếu địa điểm đó chưa gắn trong tuyến xe nào
        Dia_Diem diaDiem = diaDiemService.getDiaDiem(idDiaDiem);

        if (!diaDiem.getTuyenXeDiList().isEmpty() || !diaDiem.getTuyenXeDenList().isEmpty()) {
            model.addAttribute("diaDiemTonTaiTuyen", true);
            return "redirect:/quanly/?isQuanLyDiaDiem=true";
        }
        diaDiemService.delete(diaDiem);
        model.addAttribute("xoaDiaDiem", true);
        return "redirect:/quanly/?isQuanLyDiaDiem=true";
    }

    @PostMapping(value = "xuLyDiaDiem", params = "updateDiaDiemBtn")
    public String updateDiaDiem(@RequestParam("idDiaDiem") int idDiaDiem, Model model) {
        // -Trường hợp update được:
        // 1. Địa điểm chưa gắn trong tuyến xe nào
        // 2. Đã gắn vào tuyến xe nhưng
        // - Có chuyến xe: tất cả các vé phải là bị hủy, hoặc là chuyến xe không có vé
        // nào
        // -Không có chuyến xe
        Dia_Diem diaDiem = diaDiemService.getDiaDiem(idDiaDiem);
        // 1. Địa điểm chưa gắn trong tuyến xe nào
        if (diaDiem.getTuyenXeDiList().isEmpty() && diaDiem.getTuyenXeDenList().isEmpty()) {

        } else { // 2. Đã gắn vào tuyến xe, thì lập qua từng chuyến xe trong tuyến coi đã có vé
                 // chưa
            List<Tuyen_Xe> tuyenXeDiList = diaDiem.getTuyenXeDiList();
            List<Tuyen_Xe> tuyenXeDenList = diaDiem.getTuyenXeDenList();
            // -Có chuyến xe
            // Nếu chuyến xe đi not empty
            if (!tuyenXeDiList.isEmpty()) {
                for (Tuyen_Xe tuyenXe : tuyenXeDiList) {
                    // coi tuyến có chuyến nào không, nếu có thì lặp cho từng chuyến xe của tuyến đó
                    // để kiểm tra điều kiện vé
                    if (!tuyenXe.getChuyenXeList().isEmpty()) {
                        for (Chuyen_Xe chuyenXe : tuyenXe.getChuyenXeList()) {
                            // Nếu chuyến có vé
                            if (!chuyenXe.getVeXeList().isEmpty()) {

                                for (Ve_Xe veXe : chuyenXe.getVeXeList()) {
                                    // không bị hủy thì không update được
                                    if (!veXe.getTrangThai().equals("Đã hủy")) {
                                        model.addAttribute("tonTaiVe", true);
                                        return "redirect:/quanly/?isQuanLyDiaDiem=true";
                                    }
                                }
                                // Thỏa không có vé nào hoặc tất cả vé đã hủy của chuyến xe này.
                            }
                        }
                        // Thỏa không có vé nào hoặc tất cả vé đã hủy của tất cả chuyến xe của tuyến xe
                        // này
                    }

                }
            }

            if (!tuyenXeDenList.isEmpty()) {
                for (Tuyen_Xe tuyenXe : tuyenXeDenList) {
                    // coi tuyến có chuyến nào không, nếu có thì lặp cho từng chuyến xe của tuyến đó
                    // để kiểm tra điều kiện vé
                    if (!tuyenXe.getChuyenXeList().isEmpty()) {
                        for (Chuyen_Xe chuyenXe : tuyenXe.getChuyenXeList()) {
                            // Nếu chuyến có vé
                            if (!chuyenXe.getVeXeList().isEmpty()) {

                                for (Ve_Xe veXe : chuyenXe.getVeXeList()) {
                                    // không bị hủy thì không update được
                                    if (!veXe.getTrangThai().equals("Đã hủy")) {
                                        model.addAttribute("tonTaiVe", true);
                                        return "redirect:/quanly/?isQuanLyDiaDiem=true";
                                    }
                                }
                                // Thỏa không có vé nào hoặc tất cả vé đã hủy của chuyến xe này.
                            }
                        }
                        // Thỏa không có vé nào hoặc tất cả vé đã hủy của tất cả chuyến xe của tuyến xe
                        // này
                    }

                }
            }

        }
        model.addAttribute("DuDKChinhSuaDD", true);
        model.addAttribute("diaDiem", diaDiem);
        return "/manager/manager";
    }

    @PostMapping("updateDiaDiem")
    public String updateDiaDiem(@ModelAttribute("diaDiem") Dia_Diem diaDiem) {
        System.err.println(diaDiem);
        diaDiemService.update(diaDiem);
        return "redirect:/quanly/?isQuanLyDiaDiem=true";
    }

//    ------------------------------------ THỐNG KÊ --------------------------------------------------

    @RequestMapping("/thongKe")
    public String ql(HttpServletRequest request) {
        List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
        List<doanthu> dts= new ArrayList<doanthu>();
        List<List<doanthu>> a=new ArrayList<List<doanthu>>();
        HttpSession session=request.getSession();
        Set<Integer> nam=new HashSet<Integer>();
        long sum=0;
        for(Tuyen_Xe s: tuyenXeList) {
            doanthu dt=new doanthu();
            long temp=0;
            for(Chuyen_Xe x:s.getChuyenXeList()) {
                for(Ve_Xe y:x.getVeXeList()) {
                    temp+=y.getTongTien();
                    sum+=y.getTongTien();
                }
                nam.add(Integer.parseInt(x.getGioChay().substring(0, 4)));
            }
            dt.setTen(s.getDiaDiemLenXe()+" đến "+s.getDiaDiemXuongXe());
            dt.setTien(temp);
            dts.add(dt);
        }
        for(doanthu s:dts) {
            s.setTien(s.getTien()/sum*100);
        }
        System.out.print(nam.size());
        a.add(dts);
        session.setAttribute("listNam",nam);
        session.setAttribute("doanhthu", a);
        return "manager/ThongKe";
    }


    @RequestMapping("/thongKe/{Nam}")
    public String qlnam(HttpServletRequest request,@PathVariable("Nam") int Nam) {
        List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
        List<doanhthuMonth> a=new ArrayList<doanhthuMonth>();
        for(int i=1;i<13;i++) {
            long temp=0;
            for(Tuyen_Xe s: tuyenXeList) {
                doanthu dt=new doanthu();

                for(Chuyen_Xe x:s.getChuyenXeList()) {
                    if(Integer.parseInt(x.getGioChay().substring(5, 7))==i)
                    {
                        for(Ve_Xe y:x.getVeXeList()) {
                            temp+=y.getTongTien();
                        }
                    }
                }
            }
            doanhthuMonth k=new doanhthuMonth();
            k.setThang(months[i]);
            k.setTien(temp);
            a.add(k);

        }
        HttpSession session=request.getSession();
        session.setAttribute("doanhthulist", a);
        return "manager/ThongKe";
    }
}