package com.example.hairnada.controller.admin;

import com.example.hairnada.dto.buy.AdminBuyDto;
import com.example.hairnada.dto.hair.HairDto;
import com.example.hairnada.dto.store.StoreDto;
import com.example.hairnada.dto.user.UserDto;
import com.example.hairnada.service.admin.AdminService;
import com.example.hairnada.vo.hairVo.HairVo;
import com.example.hairnada.vo.level.LevelVo;
import com.example.hairnada.vo.page.CriteriaAdmin;
import com.example.hairnada.vo.page.CriteriaAdminList;
import com.example.hairnada.vo.page.PageAdminListVo;
import com.example.hairnada.vo.page.PageAdminVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminR/*")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;

    // 회원 정지
    @GetMapping("/suspension")
    public void suspensionUser(Long userNumber){
        adminService.suspensionUser(userNumber);
    }

    // 회원 복구
    @GetMapping("/restore")
    public void restoreUser(Long userNumber){
        adminService.restoreUser(userNumber);
    }


    // 등업 요청 수락
    @PostMapping("/membership")
    public void acceptQuest(Long userNumber, Long membershipNumber){
        adminService.acceptQuest(userNumber, membershipNumber);
    }

    // 카테고리로 상품 조회
    @GetMapping("/storeList")
    public List<StoreDto> findStoreList(Long storeCategoryNumber){
        List<StoreDto> categoryStore =  adminService.findStoreListByCategory(storeCategoryNumber);
        return categoryStore;
    }

    // 이름으로 상품 조회
    @GetMapping("/storeTitle")
    public List<StoreDto> findStoreListByTitle(String storeTitle){
        List<StoreDto> titleStore = adminService.findStoreListByTitle(storeTitle);
        return titleStore;
    }

    // 카테고리로 헤어 조회
    @GetMapping("/hairList")
    public Map<String, Object> findHairList (Long lengthNumber, Long shapeNumber, String hairGender, CriteriaAdminList criteriaAdminList){
        Map<String, Object> result = new HashMap<>();
        List<HairVo> categoryHair = adminService.findHairListByCategory( lengthNumber, shapeNumber,hairGender, criteriaAdminList);

        result.put("categoryHair", categoryHair);
        result.put("pageInfo", new PageAdminListVo(criteriaAdminList, adminService.getCategoryHairTotal(lengthNumber, shapeNumber, hairGender)));
        return result;
    }

    // 이름으로 헤어스타일 조회
    @GetMapping("/hairName")
    public Map<String, Object> findHairListByName(String hairName, CriteriaAdminList criteriaAdminList){
        Map<String, Object> result = new HashMap<>();
         List<HairVo> nameHair = adminService.findHairListByName(hairName, criteriaAdminList);
        result.put("nameHair", nameHair);
        result.put("pageInfo", new PageAdminListVo(criteriaAdminList, adminService.getNameHairTotal(hairName)));
        return result;
    }

    // 배송 완료 목록 조회
    @GetMapping("/complete")
    @ResponseBody
    public Map<String, Object> findCompleteList(CriteriaAdmin criteriaAdmin){
        Map<String, Object> result = new HashMap<>();
        List<AdminBuyDto> completeList = adminService.findCompleteList(criteriaAdmin);
        result.put("completeList", completeList);

        int completeTotal = adminService.getCompleteTotal();
        result.put("completeTotal", completeTotal);

        result.put("pageInfo", new PageAdminVo(criteriaAdmin, completeTotal));
        return result;
    }

    // 배송 미완료 목록 조회
    @GetMapping("/incomplete")
    @ResponseBody
    public Map<String, Object> findIncompleteList(CriteriaAdmin criteriaAdmin){
        Map<String, Object> result = new HashMap<>();
        List<AdminBuyDto> incompleteList = adminService.findIncompleteList(criteriaAdmin);
        result.put("incompleteList", incompleteList);

        result.put("pageInfo", new PageAdminVo(criteriaAdmin, adminService.getIncompleteTotal()));
        return result;
    }


    // 배송 상태 변경
    @GetMapping("/delivery")
    public void modifyDelivery(Long deliveryNumber, Long buyNumber){
        adminService.modifyDeliveryStatus(deliveryNumber, buyNumber);
    }

}
