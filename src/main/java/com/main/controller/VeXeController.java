package com.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.entity.Chuyen_Xe;
import com.main.entity.Dia_Diem;
import com.main.entity.Ghe;
import com.main.entity.Nhan_Vien;
import com.main.entity.Tuyen_Xe;
import com.main.entity.User;
import com.main.entity.Ve_Xe;
import com.main.entity.Xe;
import com.main.service.ChuyenXeService;
import com.main.service.TuyenXeService;
import com.main.service.UserService;
import com.main.service.VeXeService;

@Controller
@RequestMapping("/veXe")
public class VeXeController {

    @Autowired
    VeXeService veXeService;

    @Autowired
    ChuyenXeService chuyenXeService;

    @Autowired
    TuyenXeService tuyenXeService;

    @Autowired
    UserService userService;

    public void addCurrentUserFromSessionToModel(HttpServletRequest request, Model model) {
        User tempUser = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", tempUser);
    }

    public void addCurrentNhanVienFromSessionToModel(HttpServletRequest request, Model model) {
        Nhan_Vien tempNV = (Nhan_Vien) request.getSession().getAttribute("nhanVien");
        model.addAttribute("user", tempNV);
    }

    public void addCurrentVeXeFromSessionToModel(HttpServletRequest request, Model model) {
        Ve_Xe tempVeXe = (Ve_Xe) request.getSession().getAttribute("veXeDangDat");
        model.addAttribute("veXe", tempVeXe);
    }

    // cho khách hàng
    @GetMapping("/timKiemVeKH")
    public String timKiemVe(Model model, HttpServletRequest request,
                            @RequestParam(value = "maVeInput", required = false) String idVeString,
                            @RequestParam(value = "dateInput", required = false) String dateInput,
                            @RequestParam("tuyenDuongInput") String tuyenDuongInput,
                            @RequestParam("trangThaiInput") String trangThaiInput) {
        addCurrentUserFromSessionToModel(request, model);
        User tempUser = (User) request.getSession().getAttribute("user");
        List<Ve_Xe> listVeXe = veXeService.timKiemVe(tempUser.getUserId(), idVeString, dateInput, tuyenDuongInput,
                trangThaiInput);

        // Hashmap chứa list ngày, giờ đã được chuẩn hóa ứng với mỗi vé
        HashMap<Integer, List<String>> ngayGioChuanHoa = new HashMap<Integer, List<String>>();

        // Duyệt các vé để lấy những tuyến xe duy nhất
        List<Tuyen_Xe> tuyenXeDuyNhat = new ArrayList<Tuyen_Xe>();


        for (Ve_Xe veXe : listVeXe) {
            String ngayGioSQL = veXe.getNgayLap();
            String ngayThangChuaChuanHoa = ngayGioSQL.split(" ")[0];
            String gioPhutChuaChuanHoa = ngayGioSQL.split(" ")[1];
            String[] tachNgayThang = ngayThangChuaChuanHoa.split("-");
            String ngayThangDaChuanHoa = tachNgayThang[2] + "/" + tachNgayThang[1] + "/" + tachNgayThang[0];
            String gioPhutDaChuanHoa = gioPhutChuaChuanHoa.substring(0, 5);

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
        model.addAttribute("listVeXe", listVeXe);
        model.addAttribute("tuyenXeDuyNhat", tuyenXeDuyNhat);
        return "user/userBookedTickets";
    }

    @GetMapping("/timKiemVeNV")
    public String timKiemVeNV(Model model, HttpServletRequest request,
                              @RequestParam(value = "maVeInput", required = false) String idVeString,
                              @RequestParam(value = "dateInput", required = false) String dateInput,
                              @RequestParam("tuyenDuongInput") String tuyenDuongInput,
                              @RequestParam("trangThaiInput") String trangThaiInput) {
        addCurrentNhanVienFromSessionToModel(request, model);
        List<Ve_Xe> listVeXe = veXeService.timKiemVeNV(idVeString, dateInput, tuyenDuongInput, trangThaiInput);

        // Hashmap chứa list ngày, giờ đã được chuẩn hóa ứng với mỗi vé

        HashMap<Integer, List<String>> ngayGioChuanHoa = new HashMap<Integer, List<String>>();
        // Duyệt các vé để lấy những tuyến xe duy nhất
        List<Tuyen_Xe> tuyenXeDuyNhat = new ArrayList<Tuyen_Xe>();
        for (Ve_Xe veXe : listVeXe) {
            String ngayGioSQL = veXe.getNgayLap();
            String ngayThangChuaChuanHoa = ngayGioSQL.split(" ")[0];
            String gioPhutChuaChuanHoa = ngayGioSQL.split(" ")[1];
            String[] tachNgayThang = ngayThangChuaChuanHoa.split("-");
            String ngayThangDaChuanHoa = tachNgayThang[2] + "/" + tachNgayThang[1] + "/" + tachNgayThang[0];
            String gioPhutDaChuanHoa = gioPhutChuaChuanHoa.substring(0, 5);

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
        model.addAttribute("listVeXe", listVeXe);
        model.addAttribute("tuyenXeDuyNhat", tuyenXeDuyNhat);

        return "nhanvien/veChuaThanhToanList";
    }

    @GetMapping("/datVe/step1")
    public String step1Page(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER")) {
            addCurrentUserFromSessionToModel(request, model);
        } else if (request.isUserInRole("ROLE_EMPLOYEE")) {
            addCurrentNhanVienFromSessionToModel(request, model);
        }
        return "datve/step1";
    }

    // step 2
    @GetMapping("/datVe/step2")
    public String step2Page(@RequestParam(name = "loaive") String loaiVe,
                            @RequestParam(name = "chonDiemDi") int idDiaDiemDi, @RequestParam(name = "chonDiemDen") int idDiemDen,
                            @RequestParam(name = "chonNgayDi") String ngayDi,
                            @RequestParam(name = "chonNgayVe", required = false) String ngayVe, Model model,
                            HttpServletRequest request) {

        if (request.isUserInRole("ROLE_USER")) {
            addCurrentUserFromSessionToModel(request, model);
        } else if (request.isUserInRole("ROLE_EMPLOYEE")) {
            addCurrentNhanVienFromSessionToModel(request, model);

        } // thêm loại vé vào session cho những việc khi cần
        request.getSession().setAttribute("loaiVe", loaiVe);
        model.addAttribute("loaiVe", loaiVe);
        if (loaiVe.equals("khuhoi")) {

            model.addAttribute("ngayVe", ngayVe);
        }

        Tuyen_Xe tuyenXeCanTim = tuyenXeService.getTuyenXe(idDiaDiemDi, idDiemDen);
        // debug System.err.println(tuyenXeCanTim); // end debug
        model.addAttribute("tuyenXeCanTim", tuyenXeCanTim);
        model.addAttribute("ngayDi", ngayDi);
        /*
         * // Lấy Tuyến Xe (Trong TUyến xe) // xong // Ngày đi trong param ban đầu
         * (Trong RequestParam) //xong // Lấy giờ của chuyến xe (Trong chuyen Xe) //
         * xong // Thông tin số km, số giờ dự Trong Tuyen Xe // xong // Số tiền của mỗi
         * ghế trong loại xe (Trong chuyến xe => Xe => loai xe) //xong // Tên loại trong
         * loại xe (Trong Chuyến xe => Xe => Loại xe // xong // tất cả chỗ ngồi của xe
         * đó (Trong chuyến xe => Xe => GheList // xong
         */
        List<Chuyen_Xe> chuyenXeCanTim = chuyenXeService.getChuyenXeThoaMan(ngayDi, tuyenXeCanTim.getIdTuyen());
        model.addAttribute("chuyenXeCanTim", chuyenXeCanTim); // Xử lí giờ trong chuyến xe
        HashMap<Integer, List<String>> hashMapDaXuLiGioDiVaGioDenTuongUng = new HashMap<Integer, List<String>>();
        for (Chuyen_Xe chuyenXe : chuyenXeCanTim) {
            String[] temp = chuyenXe.getGioChay().split(" ")[1].split(":");

            String gioBanDau = temp[0] + ":" + temp[1];
            int GioSauKhongChuaSoKhongBanDau = (((Integer.parseInt(temp[0])) + tuyenXeCanTim.getThoiGianTon()) % 24);
            String gioSauDaCoSoKhong = temp[0];
            if (GioSauKhongChuaSoKhongBanDau < 10)
                gioSauDaCoSoKhong = "0".concat(Integer.toString(GioSauKhongChuaSoKhongBanDau));
            else {
                gioSauDaCoSoKhong = Integer.toString(GioSauKhongChuaSoKhongBanDau);
            }
            gioSauDaCoSoKhong = gioSauDaCoSoKhong.concat(":" + temp[1]);
            List<String> thoiGianList = new ArrayList<String>();
            thoiGianList.add(gioBanDau);
            thoiGianList.add(gioSauDaCoSoKhong);

            hashMapDaXuLiGioDiVaGioDenTuongUng.put(chuyenXe.getMaChuyen(), thoiGianList);
        }
        model.addAttribute("hashMapDaXuLiGio", hashMapDaXuLiGioDiVaGioDenTuongUng);
        // Xử lí số chỗ còn lại của mỗi chuyến xe
        /*
         * // Lấy tất cả các vé đã đặt của chuyến xe đó để xem ghế nào đã được đặt, ghế
         * nào // chưa (Chuyến xe => List<Ve_Xe> => Lặp qua từng vé xe sẽ biết được
         * chiếc xe // này đã được đặt bao nhiêu ghế) /// trước khi qua trang thì mình
         * nên lọc qua từng chuyến xe xem mỗi chuyến xe đã // có bao nhiêu ghế được ////
         * đặt rồi // DÙng HashMap thì mình làm được gì nhỉ ??? // ->
         * HashMap<Integer,List<Ghe>> trong đó List<Ghe> gồm những ghế đã được đặt //
         * ứng với mỗi key là Mã Chuyến Xe // Rồi lúc mà hiện ghế ở mỗi chuyến xe thì
         * mình coi ứng với id chuyến xe đó thì // coi list đó có contains cái ghế đang
         * biểu diễn hay ko => nếu có => add // class"đã đặt" // =>> Xong
         */
        HashMap<Integer, List<Ghe>> hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe = new HashMap<Integer, List<Ghe>>();
        for (Chuyen_Xe chuyenXe : chuyenXeCanTim) {
            List<Ve_Xe> veXeList = chuyenXe.getVeXeList();

            List<Ghe> listCacGheDaDatTuongUngVoiMoiChuyenXe = new ArrayList<Ghe>();
            for (Ve_Xe veXe : veXeList) {
                if (!veXe.getTrangThai().equals("Đã hủy")) {
                    System.err.println(veXe.getTrangThai());
                    List<Ghe> gheList = veXe.getGheList(); // đây là list các ghế đã đặt tương ứng với mỗi vé
                    listCacGheDaDatTuongUngVoiMoiChuyenXe.addAll(gheList);
                }
            }
            hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe.put(chuyenXe.getMaChuyen(),
                    listCacGheDaDatTuongUngVoiMoiChuyenXe); // Xử lí số chỗ còn lại của mỗi chuyến xe
        }

        model.addAttribute("hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe",
                hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe);
        return "datve/step2";
    }

    @PostMapping("/datVe/step2KhuHoi")
    public String testKhuHoi(@RequestParam("ngayVe") String ngayVe, @RequestParam(name = "chuyenXe") int idChuyenXe,
                             @RequestParam("gheCheckBox") List<Ghe> gheList, Model model, HttpServletRequest request) {

        // Vé xe thứ nhất
        Ve_Xe ve = new Ve_Xe();

        // khúc này là xác minh xem người hiện tại đang đặt vé là người dùng hay nhân
        // viên
        // Nếu là người dùng đã đăng kí rồi thì không cần lưu người dùng vào db vì đã có
        // sẵn
        if (request.isUserInRole("ROLE_USER")) {
            User tempUser = (User) request.getSession().getAttribute("user");
            ve.setIdKhachHang(tempUser);
        }

        Chuyen_Xe tempChuyenXe = chuyenXeService.getChuyenXe(idChuyenXe);
        ve.setIdChuyenXe(tempChuyenXe);
        ve.setGheList(gheList);
        Xe tempXe = tempChuyenXe.getMaXe();
        long tongTien = (tempChuyenXe.getMaXe().getMaLoaiXe().getTienVeMoiCho()) * gheList.size();
        ve.setTongTien(tongTien);

        if (request.isUserInRole("ROLE_USER")) {

            request.getSession().setAttribute("veXeChinhThuc1", ve);
        } else if (request.isUserInRole("ROLE_EMPLOYEE"))
            request.getSession().setAttribute("veXeTam1", ve);

        // lúc này làm sao để trả về step2 với những chuyến đi của ngày về
        return "redirect:/veXe/datVe/step2KhuHoi?ngayVe=" + ngayVe;
    }

    @GetMapping("/datVe/step2KhuHoi")
    public String step2KhuHoiPage(Model model, HttpServletRequest request, @RequestParam("ngayVe") String ngayVe) {
        // lấy thông tin chuyến xe từ vé thứ nhất lưu trong session chứ không còn phải
        // tạo các parameter
        Ve_Xe veThuNhat = null;
        if (request.isUserInRole("ROLE_USER")) {
            veThuNhat = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc1");

        } else if (request.isUserInRole("ROLE_EMPLOYEE"))
            veThuNhat = (Ve_Xe) request.getSession().getAttribute("veXeTam1");

        // Bước 1: Tuyến xe cần tìm
        // Đầu tiên là phải lấy được điểm đi, đến trong vé để ta có thể từ đó mà lấy dc
        // tuyến xe tương ứng
        Dia_Diem diaDiemDi = veThuNhat.getIdChuyenXe().getMaTuyen().getDiaDiemDen();
        Dia_Diem diaDiemDen = veThuNhat.getIdChuyenXe().getMaTuyen().getDiaDiemDi();
        // hoán đổi lại 2 địa điểm tại vì là đang làm vé khứ hồi
        Tuyen_Xe tuyenXeCanTim = tuyenXeService.getTuyenXe(diaDiemDi.getIdDiaDiem(), diaDiemDen.getIdDiaDiem());
        // debug
        System.err.println(tuyenXeCanTim);
        // end debug
        model.addAttribute("tuyenXeCanTim", tuyenXeCanTim);
        model.addAttribute("ngayDi", ngayVe);
        // --> lấy được tuyến xe

        // Bước 2: Lấy ra tất cả các chuyến xe thuộc tuyến xe trong ngày hôm đó
        List<Chuyen_Xe> chuyenXeCanTim = chuyenXeService.getChuyenXeThoaMan(ngayVe, tuyenXeCanTim.getIdTuyen());
        model.addAttribute("chuyenXeCanTim", chuyenXeCanTim);

        // Xử lí giờ trong chuyến xe
        HashMap<Integer, List<String>> hashMapDaXuLiGioDiVaGioDenTuongUng = new HashMap<Integer, List<String>>();
        for (Chuyen_Xe chuyenXe : chuyenXeCanTim) {
            String[] temp = chuyenXe.getGioChay().split(" ")[1].split(":");

            String gioBanDau = temp[0] + ":" + temp[1];
            int GioSauKhongChuaSoKhongBanDau = (((Integer.parseInt(temp[0])) + tuyenXeCanTim.getThoiGianTon()) % 24);
            String gioSauDaCoSoKhong = temp[0];
            if (GioSauKhongChuaSoKhongBanDau < 10)
                gioSauDaCoSoKhong = "0".concat(Integer.toString(GioSauKhongChuaSoKhongBanDau));
            else {
                gioSauDaCoSoKhong = Integer.toString(GioSauKhongChuaSoKhongBanDau);
            }
            gioSauDaCoSoKhong = gioSauDaCoSoKhong.concat(":" + temp[1]);
            List<String> thoiGianList = new ArrayList<String>();
            thoiGianList.add(gioBanDau);
            thoiGianList.add(gioSauDaCoSoKhong);

            hashMapDaXuLiGioDiVaGioDenTuongUng.put(chuyenXe.getMaChuyen(), thoiGianList);
        }
        model.addAttribute("hashMapDaXuLiGio", hashMapDaXuLiGioDiVaGioDenTuongUng);

        HashMap<Integer, List<Ghe>> hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe = new HashMap<Integer, List<Ghe>>();
        for (Chuyen_Xe chuyenXe : chuyenXeCanTim) {
            List<Ve_Xe> veXeList = chuyenXe.getVeXeList();

            List<Ghe> listCacGheDaDatTuongUngVoiMoiChuyenXe = new ArrayList<Ghe>();
            for (Ve_Xe veXe : veXeList) {
                // làm chỗ này là lấy những vé mà không bị hủy
                if (!veXe.getTrangThai().equals("Đã hủy")) {// vì đã hủy là các ghế của vé đó vẫn đang chưa được đặt
                    List<Ghe> gheList = veXe.getGheList(); // đây là list các ghế đã đặt tương ứng với mỗi vé
                    listCacGheDaDatTuongUngVoiMoiChuyenXe.addAll(gheList);
                }

            }
            hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe.put(chuyenXe.getMaChuyen(),
                    listCacGheDaDatTuongUngVoiMoiChuyenXe);
            // Xử lí số chỗ còn lại của mỗi chuyến xe
        }

        model.addAttribute("hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe",
                hashMapChuaListCacGheDaDatTuongUngVoiMoiChuyenXe);
        return "datve/step2khuhoi";
    }

    @GetMapping("/datVe/step3")
    public String step3Page(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER")) {
            addCurrentUserFromSessionToModel(request, model);
        } else if (request.isUserInRole("ROLE_EMPLOYEE")) {
            addCurrentNhanVienFromSessionToModel(request, model);

        }
        return "datve/step3";
    }

    @PostMapping("datVe/step3")
    public String step3PagePost(@RequestParam(name = "chuyenXe") int idChuyenXe,
                                @RequestParam("gheCheckBox") List<Ghe> gheList, Model model, HttpServletRequest request) {
        String loaiVe = (String) request.getSession().getAttribute("loaiVe");
        // -> Nếu loại vé là một chiều thì vé bên dưới là vé thứ nhất
        // ->nếu loại vé là khứ hồi thì vé này là vé thứ về, vé đi có tên là
        // veXeChinhThuc1 or veXeTam1 trong session
        // Để cho đỡ rối thì nên lặp code 1 tí

        if (loaiVe.equals("motchieu")) {
            Ve_Xe ve = new Ve_Xe();

            // khúc này là xác minh xem người hiện tại đang đặt vé là người dùng hay nhân
            // viên
            // Nếu là người dùng đã đăng kí rồi thì không cần lưu người dùng vào db vì đã có
            // sẵn
            if (request.isUserInRole("ROLE_USER")) {
                User tempUser = (User) request.getSession().getAttribute("user");
                ve.setIdKhachHang(tempUser);
            }

            Chuyen_Xe tempChuyenXe = chuyenXeService.getChuyenXe(idChuyenXe);
            ve.setIdChuyenXe(tempChuyenXe);
            ve.setGheList(gheList);
            Xe tempXe = tempChuyenXe.getMaXe();
            long tongTien = (tempChuyenXe.getMaXe().getMaLoaiXe().getTienVeMoiCho()) * gheList.size();
            ve.setTongTien(tongTien);

            if (request.isUserInRole("ROLE_USER")) {

                request.getSession().setAttribute("veXeChinhThuc", ve);
            } else if (request.isUserInRole("ROLE_EMPLOYEE"))
                request.getSession().setAttribute("veXeTam", ve);
        }
        // này nếu loại vé là khứ hồi
        else {
            Ve_Xe veThuHai = new Ve_Xe();

            // khúc này là xác minh xem người hiện tại đang đặt vé là người dùng hay nhân
            // viên
            // Nếu là người dùng đã đăng kí rồi thì không cần lưu người dùng vào db vì đã có
            // sẵn
            if (request.isUserInRole("ROLE_USER")) {
                User tempUser = (User) request.getSession().getAttribute("user");
                veThuHai.setIdKhachHang(tempUser);
                // lấy vé thứ nhất ra setIdKhachHang
                Ve_Xe veThuNhat = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc1");
                veThuNhat.setIdKhachHang(tempUser);
                request.getSession().setAttribute("veXeChinhThuc1", veThuNhat);

            }

            Chuyen_Xe tempChuyenXe = chuyenXeService.getChuyenXe(idChuyenXe);
            veThuHai.setIdChuyenXe(tempChuyenXe);
            veThuHai.setGheList(gheList);
            Xe tempXe = tempChuyenXe.getMaXe();
            long tongTien = (tempChuyenXe.getMaXe().getMaLoaiXe().getTienVeMoiCho()) * gheList.size();
            veThuHai.setTongTien(tongTien);

            if (request.isUserInRole("ROLE_USER")) {

                request.getSession().setAttribute("veXeChinhThuc", veThuHai);
            } else if (request.isUserInRole("ROLE_EMPLOYEE"))
                request.getSession().setAttribute("veXeTam", veThuHai);

        }

        return "redirect:/veXe/datVe/step3";
    }

    @GetMapping("/datVe/step4")
    public String step4Page(Model model, HttpServletRequest request) {
        // nếu là vé 1 chiều
        String loaiVe = (String) request.getSession().getAttribute("loaiVe");
        if (loaiVe.equals("motchieu")) {
            Ve_Xe veXe = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            model.addAttribute("veXe", veXe);
        } else { // khu hoi
            Ve_Xe veThuNhat = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc1");
            Ve_Xe veThuHai = (Ve_Xe) request.getSession().getAttribute("veXeChinhThuc");
            model.addAttribute("veXe", veThuNhat);
            model.addAttribute("veXeThu2", veThuHai);
        }

        // nếu là vé 2 chiều
        return "/datve/step4";
    }

    @PostMapping("/datVe/step4")
    public String step4Post(Model model, HttpServletRequest request,
                            @RequestParam(value = "hoTen", required = false) String hoTen,
                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                            @RequestParam(value = "email", required = false) String email,
                            @RequestParam(value = "cmnd", required = false) String cmnd,
                            @RequestParam(value = "diaChi", required = false) String diaChi,
                            @RequestParam(value = "doiThongTin", required = false) String doiThongTin) {
        addCurrentNhanVienFromSessionToModel(request, model);
        // nếu là set thông tin cho vé thứ nhất nếu là một chiều/ vé thứ 2 nếu là khứ
        // hồi
        Ve_Xe veXeDangDat = (Ve_Xe) request.getSession().getAttribute("veXeTam");
        // coi xem phonenumber đặt vào đã có user trong db chưa, nếu có thì hiện bảng
        // xem có muốn
        // thay đổi thông tin user sang cái mời vừa nhập không,
        User tempUser = userService.getUserFromUsername(phoneNumber);
        if (tempUser != null && doiThongTin == null) {
            User newUser = new User();
            newUser.setUserId(tempUser.getUserId());
            newUser.setHoTen(hoTen);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setEmail(email);
            newUser.setCmnd(cmnd);
            newUser.setDiaChi(diaChi);
            request.getSession().setAttribute("tempUser", tempUser);
            request.getSession().setAttribute("newUser", newUser);
            return "redirect:/veXe/datVe/step3?daTonTai=true";
        }
        if (tempUser != null && doiThongTin.equals("true")) {
            User tempUser1 = (User) request.getSession().getAttribute("newUser");
            tempUser.setHoTen(tempUser1.getHoTen());
            tempUser.setEmail(tempUser1.getEmail());
            tempUser.setCmnd(tempUser1.getCmnd());
            tempUser.setDiaChi(tempUser1.getDiaChi());
            int id = tempUser.getUserId();
            userService.update(tempUser);
            tempUser = userService.getUser(id);
        }
        if (tempUser == null) {
            tempUser = new User();
            tempUser.setHoTen(hoTen);
            tempUser.setPhoneNumber(phoneNumber);
            tempUser.setEmail(email);
            tempUser.setCmnd(cmnd);
            tempUser.setDiaChi(diaChi);
        }

        veXeDangDat.setIdKhachHang(tempUser);
        request.getSession().setAttribute("veXeChinhThuc", veXeDangDat);
        // nếu là vé khứ hồi thì lấy vé thứ nhất ra set thông tin như trên, nằm trong
        // veXeTam1
        String loaiVe = (String) request.getSession().getAttribute("loaiVe");
        if (loaiVe.equals("khuhoi")) {
            Ve_Xe veThuNhat = (Ve_Xe) request.getSession().getAttribute("veXeTam1");
            veThuNhat.setIdKhachHang(tempUser);
            request.getSession().setAttribute("veXeChinhThuc1", veThuNhat);
        }
        return "redirect:/veXe/datVe/step4";
    }

}