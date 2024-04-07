package salaba.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import salaba.vo.rentalHome.RentalHomeFacility;
import salaba.vo.rentalHome.RentalHomePhoto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import salaba.service.HostService;
import salaba.service.StorageService;
import salaba.vo.host.HostReservation;
import salaba.vo.rentalHome.RentalHome;
import salaba.vo.rentalHome.Theme;

//호스트 페이지 컨트롤러
@RequiredArgsConstructor
@Controller
@RequestMapping("/host")
public class HostController {
  private static final Log log = LogFactory.getLog(HostController.class);
  private final HostService hostService;
  private final StorageService storageService;
  private String uploadDir = "rentalHome/";

  @Value("${ncp.bucketname}")
  private String bucketName;

  // 호스트 숙소등록 시작화면
  @GetMapping("hostStart")
  public void hostStart() {
  }

  // 호스트 숙소등록 기본정보 폼
  @GetMapping("rentalHomeForm")
  public void rentalHomeForm() {
  }

  // 호스트 숙소 기본정보 저장
  @PostMapping("rentalHomeSave")
  public String rentalHomeSave(
      HttpSession session, RentalHome rentalHome,
      MultipartFile[] photos) throws Exception {

    // 숙소 사진 추가하는 메서드
    ArrayList<RentalHomePhoto> files = new ArrayList<>();
    int order = 1;  // 사진 순서
    for (MultipartFile file : photos) {
      if (file.getSize() == 0) {
        continue;
      }
      String filename = storageService.upload(this.bucketName, this.uploadDir, file); // uuid_file_name
      files.add(
          RentalHomePhoto.builder()
              .uuidPhotoName(filename)
              .oriPhotoName(file.getOriginalFilename()) // ori_file_name
              .photoOrder(order)
              .build());
      // 사진 설명 추가하는 부분은 고민중..
      order++;
    }

    if (files.size() > 0) {
      rentalHome.setRentalHomePhotos(files);
    }

    session.setAttribute("rentalHome",rentalHome);
    return "redirect:themeForm";
  }

  // 호스트 숙소 테마 폼
  @GetMapping("themeForm")
  public void themeForm(Model model){
    model.addAttribute("themeList", hostService.themeList());
  }

  // 호스트 숙소 테마 저장
  @PostMapping("themeSave")
  public String themeSave(HttpSession session,
      @RequestParam List<Integer> themeNos,
      @RequestParam List<String> themeNames,
      @RequestParam String type) {
    RentalHome rentalHome = (RentalHome) session.getAttribute("rentalHome");
    List<Theme> themes = new ArrayList<>();

    String[] typeArr = type.split(",");

    Theme typeTheme = new Theme();

    typeTheme.setThemeNo(Integer.parseInt(typeArr[0]));
    typeTheme.setThemeName(typeArr[1]);

    themes.add(typeTheme);

    for (int i = 0; i < themeNos.size(); i++) {
      Theme theme = new Theme();
      theme.setThemeNo(themeNos.get(i));
      theme.setThemeName(themeNames.get(i));
      themes.add(theme);
    }

    rentalHome.setRentalHomeThemes(themes);
    session.setAttribute("rentalHome", rentalHome);

    return "redirect:rentalHomeFacilityForm";
  }

  // 호스트 숙소 시설 폼
  @GetMapping("rentalHomeFacilityForm")
  public void rentalHomeFacilityForm(Model model){
    model.addAttribute("facilityList", hostService.facilityList());
  }

  // 호스트 숙소 시설 저장
  @PostMapping("rentalHomeFacilitySave")
  public String rentalHomeFacilitySave(HttpSession session,
      @RequestParam int capacity,
      @RequestParam List<Integer> facilityCount,
      @RequestParam List<Integer> facilityNos,
      @RequestParam List<String> facilityNames
      ) {
    RentalHome rentalHome = (RentalHome) session.getAttribute("rentalHome");
    List<RentalHomeFacility> facilityList = new ArrayList<>();

    for (int i = 0; i < facilityCount.size(); i++) {
      RentalHomeFacility rentalHomeFacility = new RentalHomeFacility();
      rentalHomeFacility.setFacilityNo(facilityNos.get(i));
      rentalHomeFacility.setFacilityName(facilityNames.get(i));
      rentalHomeFacility.setFacilityCount(facilityCount.get(i));

      facilityList.add(rentalHomeFacility);
    }

    rentalHome.setRentalHomeFacilities(facilityList);
    rentalHome.setCapacity(capacity);

    session.setAttribute("rentalHome", rentalHome);

    return "redirect:rentalHomeConfirm";
  }

  // 호스트 숙소 등록 최종 확인
  @GetMapping("rentalHomeConfirm")
  public void rentalHomeConfirm() {
  }

  // DB에 숙소 등록
  @Transactional
  @GetMapping("rentalHomeAdd")
  public String rentalHomeAdd(
      HttpSession session) {

    RentalHome rentalHome = (RentalHome) session.getAttribute("rentalHome");

    hostService.addRentalHome(rentalHome);

    // 숙소 등록 후 세션 invalid -> 로그인정보 재등록해야됨
    session.setAttribute("rentalHome", rentalHome);

    return "host/reservationList";
  }

  // 숙소 관리 리스트
  @GetMapping("rentalHomeList")
  public void rentalHomeList(Model model, int hostNo) {
    model.addAttribute("list", hostService.rentalHomeList(hostNo));
  }

  // 예약 내역 리스트
  @GetMapping("reservationList")
  public void reservationList(Model model, int hostNo) {
    model.addAttribute("list", hostService.list(hostNo));
  }

  // 예약 상태 업데이트
  @PostMapping("reservationCheck")
  public String stateUpdate(HostReservation hostReservation) {
    hostService.stateUpdate(hostReservation.getState(),
        hostReservation.getReservationNo());
    return "redirect:reservationList?hostNo=" + hostReservation.getHostNo();
  }
}
