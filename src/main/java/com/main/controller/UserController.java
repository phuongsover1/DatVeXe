package com.main.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.main.entity.Tuyen_Xe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.entity.User;
import com.main.entity.Ve_Xe;
import com.main.service.ChuyenXeService;
import com.main.service.TuyenXeService;
import com.main.service.UserService;
import com.main.service.VeXeService;
import com.main.service.XeService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TuyenXeService tuyenXeService;

    @Autowired
    ChuyenXeService chuyenXeService;

    @Autowired
    VeXeService veXeService;

    @Autowired
    XeService xeService;

    public void addCurrentUserFromSessionToModel(HttpServletRequest request, Model model) {
        User tempUser = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", tempUser);
    }

    public void addCurrentVeXeFromSessionToModel(HttpServletRequest request, Model model) {
        Ve_Xe tempVeXe = (Ve_Xe) request.getSession().getAttribute("veXeDangDat");
        model.addAttribute("veXe", tempVeXe);
    }

    @GetMapping("/showInfo")
    public String showUserInfo(HttpServletRequest request, Model model) {
        addCurrentUserFromSessionToModel(request, model);
        return "user/userInfo";
    }

    @GetMapping("/changeUserInfo")
    public String showChangeInfoPage(HttpServletRequest request, Model model) {
        addCurrentUserFromSessionToModel(request, model);
        return "user/changeInfo";
    }

    @PostMapping("/saveUser")
    public String saveUserInfo(@ModelAttribute("user") User user, @RequestParam("userId") int id,
                               @RequestParam("dd") String dd, @RequestParam("mm") String mm, @RequestParam("yyyy") String yyyy,
                               HttpServletRequest request) {
        User tempUser = userService.getUser(id);
        if (!user.getHoTen().equals(""))
            tempUser.setHoTen(user.getHoTen());
        if (!user.getCmnd().equals(""))
            tempUser.setCmnd(user.getCmnd());
        if (!user.getEmail().equals(""))
            tempUser.setEmail(user.getEmail());
        if (!user.getNgheNghiep().equals(""))
            tempUser.setNgheNghiep(user.getNgheNghiep());
        tempUser.setGioiTinh(user.getGioiTinh());
        if (mm.length() == 1)
            mm = "0".concat(mm);
        tempUser.setNgaySinh(yyyy + "-" + mm + "-" + dd);

        userService.update(tempUser);
        User newUserToAddToSession = userService.getUser(id);
        request.getSession().setAttribute("user", newUserToAddToSession);
        return "redirect:/user/showInfo?userId=" + tempUser.getUserId();
    }

    @PostMapping("userBookedTickets")
    public String luuVePage(@RequestParam(name = "hinhThucThanhToan") String hinhThuc, HttpServletRequest request) {

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
            veXeService.luuVe(veXeThu1);
            veXeService.luuVe(veXeThu2);
            request.getSession().removeAttribute("veXeChinhThuc");
            request.getSession().removeAttribute("veXeChinhThuc1");
            request.getSession().removeAttribute("loaiVe");
        }
        return "redirect:/user/userBookedTickets";
    }

    @PostMapping("huyVe")
    public String huyVe(@RequestParam("idVe") int idVe) {
        Ve_Xe tempVe = veXeService.getVeXe(idVe);
        tempVe.setTrangThai("Đã hủy");
        veXeService.updateVe(tempVe);
        return "redirect:/user/userBookedTickets";
    }


    /// Trang các vé đã đặt
    @GetMapping("userBookedTickets")
    public String bangCacVeDaDat(HttpServletRequest request, Model model) {
        addCurrentUserFromSessionToModel(request, model);
        User tempUser = (User) request.getSession().getAttribute("user");
        List<Ve_Xe> veXeList = veXeService.getVeXeList(tempUser.getUserId());
        // Hashmap chứa list ngày, giờ đã được chuẩn hóa ứng với mỗi vé

        HashMap<Integer, List<String>> ngayGioChuanHoa = new HashMap<Integer, List<String>>();

        // Duyệt các vé để lấy những tuyến xe duy nhất
        List<Tuyen_Xe> tuyenXeDuyNhat = new ArrayList<Tuyen_Xe>();

        for (Ve_Xe veXe : veXeList) {
            String ngayGioSQL = veXe.getNgayLap();
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
        return "/user/userBookedTickets";
    }

}