package com.main.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.entity.Chuyen_Xe;
import com.main.entity.Ghe;
import com.main.entity.Loai_Xe;
import com.main.entity.Nhan_Vien;
import com.main.entity.Tuyen_Xe;
import com.main.entity.User;
import com.main.entity.Ve_Xe;
import com.main.entity.Xe;
import com.main.service.ChuyenXeService;
import com.main.service.LoaiXeService;
import com.main.service.NhanVienService;
import com.main.service.TuyenXeService;
import com.main.service.UserService;
import com.main.service.VeXeService;
import com.main.service.XeService;

@Controller
public class NhanVienController {

    @Autowired
    VeXeService veXeService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    UserService userService;

    @Autowired
    LoaiXeService loaiXeService;

    @Autowired
    XeService xeService;

    @Autowired
    TuyenXeService tuyenXeService;

    @Autowired
    ChuyenXeService chuyenXeService;

    public void addCurrentNhanVienFromSessionToModel(HttpServletRequest request, Model model) {
        Nhan_Vien tempNV = (Nhan_Vien) request.getSession().getAttribute("nhanVien");
        model.addAttribute("nhanVien", tempNV);
    }

    @GetMapping("nhanvien/showInfo")
    public String showInfo(Model model, HttpServletRequest request) {
        addCurrentNhanVienFromSessionToModel(request, model);
        return "/user/userInfo";
    }

    @GetMapping("nhanvien/changeInfo")
    public String changeInfo(Model model, HttpServletRequest request) {
        addCurrentNhanVienFromSessionToModel(request, model);
        return "/user/changeInfo";
    }

    @PostMapping("nhanvien/saveNhanVien")
    public String saveNhanVien(@ModelAttribute("nhanVien") Nhan_Vien nhanVien, @RequestParam("idNhanVien") int id,
                               @RequestParam("dd") String dd, @RequestParam("mm") String mm, @RequestParam("yyyy") String yyyy,
                               HttpServletRequest request) {
        Nhan_Vien tempNV = nhanVienService.getNhanVienFromId(id);
        if (!nhanVien.getHoTen().equals(""))
            tempNV.setHoTen(nhanVien.getHoTen());
        if (!nhanVien.getCmnd().equals(""))
            tempNV.setCmnd(nhanVien.getCmnd());
        if (!nhanVien.getEmail().equals(""))
            tempNV.setEmail(nhanVien.getEmail());
        tempNV.setGioiTinh(nhanVien.getGioiTinh());
        if (mm.length() == 1)
            mm = "0".concat(mm);
        tempNV.setNgaySinh(yyyy + "-" + mm + "-" + dd);

        nhanVienService.updateNV(tempNV);
        Nhan_Vien newNhanVienToAddToSession = nhanVienService.getNhanVienFromId(id);
        request.getSession().setAttribute("nhanVien", newNhanVienToAddToSession);
        return "redirect:/nhanvien/showInfo";
    }

    @GetMapping("nhanvien/nghiepVu")
    public String nghiepVuPage(Model model,
                               @RequestParam(value = "themXeMoi", required = false) String themXeMoi,
                               @RequestParam(value = "themChuyenXeMoi", required = false) String themChuyenXeMoi,
                               @RequestParam(value = "danhDauXe", required = false) String danhDauXe) {
        // nếu button thêm xe mới được chọn
        if (themXeMoi != null) {
            List<Loai_Xe> listLoaiXe = loaiXeService.getList();
            Xe xeMoi = new Xe();
            model.addAttribute("xeMoi", xeMoi);
            model.addAttribute("listLoaiXe", listLoaiXe);
        }
        // end button

        // nếu button thêm chuyến xe mới được chọn
        if (themChuyenXeMoi != null) {
            // lấy ra tất cả các chuyến xe để bỏ vào select
            List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
            model.addAttribute("tuyenXeList", tuyenXeList);
        }
        // nếu button đánh dấu xe được chọn
        if (danhDauXe != null) {
            // lấy list tất cả các chuyến xe mà có field da_hoan_thanh là 0
            List<Chuyen_Xe> listChuyenXeChuaHoanThanh = chuyenXeService.getListChuyenXeChuaHoanThanh();
            model.addAttribute("listChuyenXeChuaHoanThanh", listChuyenXeChuaHoanThanh);
        }

        return "/nhanvien/nghiepVu";
    }

    // thêm xe mới
    @PostMapping("nhanvien/themXeMoi")
    public String themXeMoi(@ModelAttribute("xeMoi") Xe xeMoi, @RequestParam(name = "loaiXe") String idLoaiXeStr) {
        Xe tempXe = xeService.layXeTuBienSoXe(xeMoi.getBienSoXe());
        int idLoaiXe = Integer.parseInt(idLoaiXeStr);
        if (tempXe != null)
            return "redirect:/nhanvien/nghiepVu?themXeMoi=true&thanhCong=false";

        // Nếu không tồn tại xe có biển số đó thì tạo mới
        Loai_Xe loaiXe = loaiXeService.getLoaiXeFromId(idLoaiXe);
        List<Ghe> gheList = new ArrayList<Ghe>();
        //Thêm ghế vào xe
        String gheA = "A";
        String gheB = "B";
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                gheA = gheA.concat("0" + Integer.toString(i));
                gheB = gheB.concat("0" + Integer.toString(i));
                Ghe tempGheA = new Ghe(gheA);
                Ghe tempGheB = new Ghe(gheB);
                gheList.add(tempGheA);
                gheList.add(tempGheB);
                gheA = "A";
                gheB = "B";
            } else {
                gheA = gheA.concat(Integer.toString(i));
                gheB = gheB.concat(Integer.toString(i));
                Ghe tempGheA = new Ghe(gheA);
                Ghe tempGheB = new Ghe(gheB);
                gheList.add(tempGheA);
                gheList.add(tempGheB);
                gheA = "A";
                gheB = "B";
            }
        }
        if (idLoaiXe == 2) {// Ghế ngồi thì thêm mỗi tầng 6 ghế nữa
            gheA = "A";
            gheB = "B";
            for (int i = 13; i <= 18; i++) {

                gheA = gheA.concat(Integer.toString(i));
                gheB = gheB.concat(Integer.toString(i));
                Ghe tempGheA = new Ghe(gheA);
                Ghe tempGheB = new Ghe(gheB);
                gheList.add(tempGheA);
                gheList.add(tempGheB);
                gheA = "A";
                gheB = "B";
            }
        }
        xeMoi.setMaLoaiXe(loaiXe);
        xeMoi.setGheList(gheList);
        xeService.save(xeMoi);
        return "redirect:/nhanvien/nghiepVu?themXeMoi=true&thanhCong=true";
    }


    // thêm chuyến xe mới
    @PostMapping("nhanvien/themChuyenXeMoi")
    public String themChuyenXeMoi(@RequestParam("bienSoXe") String bienSoXe,
                                  @RequestParam("tuyenXe") String tuyenXe,
                                  @RequestParam("ngayChay") String ngayChay,
                                  @RequestParam("hh") String hh,
                                  @RequestParam("mm") String mm) {
        Xe tempXe = xeService.layXeTuBienSoXe(bienSoXe);
        // biển số xe không tồn tại
        if (tempXe == null) {
            return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&tonTaiXe=false";
        }

        // nếu biển số xe đã tồn tại xem bây giờ xe có chạy chuyến nào không
        //1. Có chạy: Khi mà chuyến xe có biển số xe này có field da_hoan_thanh = 0
        //2. Không chạy: không thuộc tuyến xe nào hoặc nếu có thì field da_hoan_thanh = 1
        List<Chuyen_Xe> listChuyenXeCuaChiecXe = chuyenXeService.getListChuyenXeThuocIdXe(tempXe.getIdXe());
        if (listChuyenXeCuaChiecXe != null) {
            return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&tonTaiChuyenXe=true";
        }
        // lấy tuyến xe có địa điểm đi, địa điểm đến biết trước
        String[] idDiaDiem = tuyenXe.split(",");
        Tuyen_Xe tempTuyenXe = tuyenXeService.getTuyenXe(Integer.parseInt(idDiaDiem[0]), Integer.parseInt(idDiaDiem[1]));

        String gioChay = ngayChay + " " + hh + ":" + mm;

        // tạo Chuyến xe mới và thêm vào cơ sở dữ liệu
        Chuyen_Xe chuyenXeMoi = new Chuyen_Xe(tempXe, tempTuyenXe, gioChay);

        // lưu chuyến xe vào cơ sở dl
        chuyenXeService.save(chuyenXeMoi);
        return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&thanhCong=true";
    }

    // đánh dấu xe đã hoàn thành chuyến
    @PostMapping("nhanvien/danhDauChuyenXe")
    public String danhDauChuyenXe(@RequestParam("idChuyenXe") int idChuyenXe) {
        // lấy chuyến xe có idChuyenXe rồi update daHoanThanh = 1;
        Chuyen_Xe chuyenXeTemp = chuyenXeService.getChuyenXe(idChuyenXe);
        chuyenXeTemp.setDaHoanThanh(true);
        chuyenXeService.update(chuyenXeTemp);
        return "redirect:/nhanvien/nghiepVu?danhDauXe=true";
    }


    // end đánh dấu xe đã hoàn thành chuyến


    @PostMapping("nhanvien/veChuaThanhToan")
    public String luuOfflineUser(@RequestParam(name = "hinhThucThanhToan") String hinhThuc, HttpServletRequest request) {
        // làm y chang bên user
/////////////// Nếu là vé 1 chiều
        String loaiVe = (String) request.getSession().getAttribute("loaiVe");
        if (loaiVe.equals("motchieu")) {
            Ve_Xe veXe = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            // set hinh thuc thanh toan
            veXe.setHinhThucThanhToan(hinhThuc);
            // set ngay luu ve để lưu vào cơ sở dữ liệu
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDate = dateObj.format(formatObj);
            veXe.setNgayLap(formatedDate);
            veXe.setTrangThai("Chờ thanh toán");
            // xem user đã từng đăng kí offline chưa, nều rồi thì chỉ lưu vé, nếu chưa thì mới save User mới
            User tempUser = userService.getUserFromUsername(veXe.getIdKhachHang().getPhoneNumber());
            if (tempUser == null) {
                userService.saveUser(veXe.getIdKhachHang());
            } else {
                veXe.setIdKhachHang(tempUser);
            }
            veXeService.luuVe(veXe);
            request.getSession().removeAttribute("veXeChinhThuc");
            request.getSession().removeAttribute("loaiVe");

        } else {    //////////// Nếu là vé khứ hồi

            Ve_Xe veXeThu1 = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc1");
            Ve_Xe veXeThu2 = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            // set hinh thuc thanh toan
            veXeThu1.setHinhThucThanhToan(hinhThuc);
            veXeThu2.setHinhThucThanhToan(hinhThuc);
            // set ngay luu ve để lưu vào cơ sở dữ liệu
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDate = dateObj.format(formatObj);
            veXeThu1.setNgayLap(formatedDate);
            veXeThu2.setNgayLap(formatedDate);
            veXeThu1.setTrangThai("Chờ thanh toán");
            veXeThu2.setTrangThai("Chờ thanh toán");
            userService.saveUser(veXeThu1.getIdKhachHang());
            veXeService.luuVe(veXeThu1);
            veXeService.luuVe(veXeThu2);
            request.getSession().removeAttribute("veXeChinhThuc");
            request.getSession().removeAttribute("veXeChinhThuc1");
            request.getSession().removeAttribute("loaiVe");
        }
        return "redirect:/nhanvien/veChuaThanhToan";
    }

    @GetMapping("nhanvien/veChuaThanhToan")
    public String getListVePage(HttpServletRequest request, Model model, @RequestParam(value = "daThanhToanVaHuy", required = false) boolean daThanhToanVaHuy) {
        addCurrentNhanVienFromSessionToModel(request, model);
        Nhan_Vien tempNV = (Nhan_Vien) request.getSession().getAttribute("nhanVien");
        List<Ve_Xe> veXeList;

        // Nếu ấn vào nút hiển thị các vé đã thanh toán và hủy
        if (daThanhToanVaHuy) {
            veXeList = veXeService.getAllVeXeDaThanhToanVaHuy(tempNV.getIdNhanVien());

        } else {
            veXeList = veXeService.getAllVeXeChuaThanhToanList();

        }
        // Hashmap chứa list ngày, giờ đã được chuẩn hóa ứng với mỗi vé
        // và
        // Duyệt các vé để lấy những tuyến xe duy nhất
        List<Tuyen_Xe> tuyenXeDuyNhat = new ArrayList<Tuyen_Xe>();
        HashMap<Integer, List<String>> ngayGioChuanHoa = new HashMap<Integer, List<String>>();
        for (Ve_Xe veXe : veXeList) {
            String ngayGioSQL =
                    veXe.getNgayLap();
            String ngayThangChuaChuanHoa =
                    ngayGioSQL.split(" ")[0];
            String gioPhutChuaChuanHoa =
                    ngayGioSQL.split(" ")[1];
            String[] tachNgayThang =
                    ngayThangChuaChuanHoa.split("-");
            String ngayThangDaChuanHoa =
                    tachNgayThang[2] + "/" + tachNgayThang[1] + "/" + tachNgayThang[0];
            String
                    gioPhutDaChuanHoa = gioPhutChuaChuanHoa.substring(0, 5);

            List<String> listNgayGio = new ArrayList<>();
            listNgayGio.add(ngayThangDaChuanHoa);
            listNgayGio.add(gioPhutDaChuanHoa);
            ngayGioChuanHoa.put(veXe.getIdVe(), listNgayGio);

            // kiểm tra trong List các tuyến xe duy nhất có tuyến này chưa
            Tuyen_Xe tuyenXeCuaVeNay = veXe.getIdChuyenXe().getMaTuyen();
            if (!tuyenXeDuyNhat.contains(tuyenXeCuaVeNay)) {
                tuyenXeDuyNhat.add(tuyenXeCuaVeNay);
            }

        }


        model.addAttribute("hashMapNgayGio", ngayGioChuanHoa);
        model.addAttribute("listVeXe", veXeList);
        model.addAttribute("tuyenXeDuyNhat", tuyenXeDuyNhat);
        return "nhanvien/veChuaThanhToanList";
    }

    @PostMapping("nhanvien/thanhtoanve")
    public String thanhToanVe(@RequestParam("idVe") int idVe, @RequestParam("idNhanVien") int idNhanVien) {
        // lấy nhân vien ra
        // lấy vé ra
        // set ve.idNhanVien = nv
        // set trạng thái đã thanh toán
        // update vé
        Nhan_Vien tempNV = nhanVienService.getNhanVienFromId(idNhanVien);
        Ve_Xe tempVeXe = veXeService.getVeXe(idVe);
        tempVeXe.setIdNhanVien(tempNV);
        tempVeXe.setTrangThai("Đã thanh toán");

        veXeService.updateVe(tempVeXe);
        return "redirect:/nhanvien/veChuaThanhToan";
    }

    @PostMapping("nhanvien/huyVe")
    public String huyVe(@RequestParam("idVe") int idVe, @RequestParam("idNhanVien") int idNhanVien) {
        Nhan_Vien tempNV = nhanVienService.getNhanVienFromId(idNhanVien);
        Ve_Xe tempVe = veXeService.getVeXe(idVe);
        tempVe.setIdNhanVien(tempNV);
        tempVe.setTrangThai("Đã hủy");
        veXeService.updateVe(tempVe);
        return "redirect:/nhanvien/veChuaThanhToan";
    }


}