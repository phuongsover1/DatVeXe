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
        // n???u button th??m xe m???i ???????c ch???n
        if (themXeMoi != null) {
            List<Loai_Xe> listLoaiXe = loaiXeService.getList();
            Xe xeMoi = new Xe();
            model.addAttribute("xeMoi", xeMoi);
            model.addAttribute("listLoaiXe", listLoaiXe);
        }
        // end button

        // n???u button th??m chuy???n xe m???i ???????c ch???n
        if (themChuyenXeMoi != null) {
            // l???y ra t???t c??? c??c chuy???n xe ????? b??? v??o select
            List<Tuyen_Xe> tuyenXeList = tuyenXeService.getTuyenXeList();
            model.addAttribute("tuyenXeList", tuyenXeList);
        }
        // n???u button ????nh d???u xe ???????c ch???n
        if (danhDauXe != null) {
            // l???y list t???t c??? c??c chuy???n xe m?? c?? field da_hoan_thanh l?? 0
            List<Chuyen_Xe> listChuyenXeChuaHoanThanh = chuyenXeService.getListChuyenXeChuaHoanThanh();
            model.addAttribute("listChuyenXeChuaHoanThanh", listChuyenXeChuaHoanThanh);
        }

        return "/nhanvien/nghiepVu";
    }

    // th??m xe m???i
    @PostMapping("nhanvien/themXeMoi")
    public String themXeMoi(@ModelAttribute("xeMoi") Xe xeMoi, @RequestParam(name = "loaiXe") String idLoaiXeStr) {
        Xe tempXe = xeService.layXeTuBienSoXe(xeMoi.getBienSoXe());
        int idLoaiXe = Integer.parseInt(idLoaiXeStr);
        if (tempXe != null)
            return "redirect:/nhanvien/nghiepVu?themXeMoi=true&thanhCong=false";

        // N???u kh??ng t???n t???i xe c?? bi???n s??? ???? th?? t???o m???i
        Loai_Xe loaiXe = loaiXeService.getLoaiXeFromId(idLoaiXe);
        List<Ghe> gheList = new ArrayList<Ghe>();
        //Th??m gh??? v??o xe
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
        if (idLoaiXe == 2) {// Gh??? ng???i th?? th??m m???i t???ng 6 gh??? n???a
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


    // th??m chuy???n xe m???i
    @PostMapping("nhanvien/themChuyenXeMoi")
    public String themChuyenXeMoi(@RequestParam("bienSoXe") String bienSoXe,
                                  @RequestParam("tuyenXe") String tuyenXe,
                                  @RequestParam("ngayChay") String ngayChay,
                                  @RequestParam("hh") String hh,
                                  @RequestParam("mm") String mm) {
        Xe tempXe = xeService.layXeTuBienSoXe(bienSoXe);
        // bi???n s??? xe kh??ng t???n t???i
        if (tempXe == null) {
            return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&tonTaiXe=false";
        }

        // n???u bi???n s??? xe ???? t???n t???i xem b??y gi??? xe c?? ch???y chuy???n n??o kh??ng
        //1. C?? ch???y: Khi m?? chuy???n xe c?? bi???n s??? xe n??y c?? field da_hoan_thanh = 0
        //2. Kh??ng ch???y: kh??ng thu???c tuy???n xe n??o ho???c n???u c?? th?? field da_hoan_thanh = 1
        List<Chuyen_Xe> listChuyenXeCuaChiecXe = chuyenXeService.getListChuyenXeThuocIdXe(tempXe.getIdXe());
        if (listChuyenXeCuaChiecXe != null) {
            return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&tonTaiChuyenXe=true";
        }
        // l???y tuy???n xe c?? ?????a ??i???m ??i, ?????a ??i???m ?????n bi???t tr?????c
        String[] idDiaDiem = tuyenXe.split(",");
        Tuyen_Xe tempTuyenXe = tuyenXeService.getTuyenXe(Integer.parseInt(idDiaDiem[0]), Integer.parseInt(idDiaDiem[1]));

        String gioChay = ngayChay + " " + hh + ":" + mm;

        // t???o Chuy???n xe m???i v?? th??m v??o c?? s??? d??? li???u
        Chuyen_Xe chuyenXeMoi = new Chuyen_Xe(tempXe, tempTuyenXe, gioChay);

        // l??u chuy???n xe v??o c?? s??? dl
        chuyenXeService.save(chuyenXeMoi);
        return "redirect:/nhanvien/nghiepVu?themChuyenXeMoi=true&thanhCong=true";
    }

    // ????nh d???u xe ???? ho??n th??nh chuy???n
    @PostMapping("nhanvien/danhDauChuyenXe")
    public String danhDauChuyenXe(@RequestParam("idChuyenXe") int idChuyenXe) {
        // l???y chuy???n xe c?? idChuyenXe r???i update daHoanThanh = 1;
        Chuyen_Xe chuyenXeTemp = chuyenXeService.getChuyenXe(idChuyenXe);
        chuyenXeTemp.setDaHoanThanh(true);
        chuyenXeService.update(chuyenXeTemp);
        return "redirect:/nhanvien/nghiepVu?danhDauXe=true";
    }


    // end ????nh d???u xe ???? ho??n th??nh chuy???n


    @PostMapping("nhanvien/veChuaThanhToan")
    public String luuOfflineUser(@RequestParam(name = "hinhThucThanhToan") String hinhThuc, HttpServletRequest request) {
        // l??m y chang b??n user
/////////////// N???u l?? v?? 1 chi???u
        String loaiVe = (String) request.getSession().getAttribute("loaiVe");
        if (loaiVe.equals("motchieu")) {
            Ve_Xe veXe = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            // set hinh thuc thanh toan
            veXe.setHinhThucThanhToan(hinhThuc);
            // set ngay luu ve ????? l??u v??o c?? s??? d??? li???u
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDate = dateObj.format(formatObj);
            veXe.setNgayLap(formatedDate);
            veXe.setTrangThai("Ch??? thanh to??n");
            // xem user ???? t???ng ????ng k?? offline ch??a, n???u r???i th?? ch??? l??u v??, n???u ch??a th?? m???i save User m???i
            User tempUser = userService.getUserFromUsername(veXe.getIdKhachHang().getPhoneNumber());
            if (tempUser == null) {
                userService.saveUser(veXe.getIdKhachHang());
            } else {
                veXe.setIdKhachHang(tempUser);
            }
            veXeService.luuVe(veXe);
            request.getSession().removeAttribute("veXeChinhThuc");
            request.getSession().removeAttribute("loaiVe");

        } else {    //////////// N???u l?? v?? kh??? h???i

            Ve_Xe veXeThu1 = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc1");
            Ve_Xe veXeThu2 = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            // set hinh thuc thanh toan
            veXeThu1.setHinhThucThanhToan(hinhThuc);
            veXeThu2.setHinhThucThanhToan(hinhThuc);
            // set ngay luu ve ????? l??u v??o c?? s??? d??? li???u
            LocalDateTime dateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatedDate = dateObj.format(formatObj);
            veXeThu1.setNgayLap(formatedDate);
            veXeThu2.setNgayLap(formatedDate);
            veXeThu1.setTrangThai("Ch??? thanh to??n");
            veXeThu2.setTrangThai("Ch??? thanh to??n");
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

        // N???u ???n v??o n??t hi???n th??? c??c v?? ???? thanh to??n v?? h???y
        if (daThanhToanVaHuy) {
            veXeList = veXeService.getAllVeXeDaThanhToanVaHuy(tempNV.getIdNhanVien());

        } else {
            veXeList = veXeService.getAllVeXeChuaThanhToanList();

        }
        // Hashmap ch???a list ng??y, gi??? ???? ???????c chu???n h??a ???ng v???i m???i v??
        // v??
        // Duy???t c??c v?? ????? l???y nh???ng tuy???n xe duy nh???t
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

            // ki???m tra trong List c??c tuy???n xe duy nh???t c?? tuy???n n??y ch??a
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
        // l???y nh??n vien ra
        // l???y v?? ra
        // set ve.idNhanVien = nv
        // set tr???ng th??i ???? thanh to??n
        // update v??
        Nhan_Vien tempNV = nhanVienService.getNhanVienFromId(idNhanVien);
        Ve_Xe tempVeXe = veXeService.getVeXe(idVe);
        tempVeXe.setIdNhanVien(tempNV);
        tempVeXe.setTrangThai("???? thanh to??n");

        veXeService.updateVe(tempVeXe);
        return "redirect:/nhanvien/veChuaThanhToan";
    }

    @PostMapping("nhanvien/huyVe")
    public String huyVe(@RequestParam("idVe") int idVe, @RequestParam("idNhanVien") int idNhanVien) {
        Nhan_Vien tempNV = nhanVienService.getNhanVienFromId(idNhanVien);
        Ve_Xe tempVe = veXeService.getVeXe(idVe);
        tempVe.setIdNhanVien(tempNV);
        tempVe.setTrangThai("???? h???y");
        veXeService.updateVe(tempVe);
        return "redirect:/nhanvien/veChuaThanhToan";
    }


}