package com.example.hairnada.controller.store;

import com.example.hairnada.dto.store.StoreDto;
import com.example.hairnada.service.store.StoreService;
import com.example.hairnada.vo.hairVo.StoreVo;
import com.example.hairnada.vo.page.CriteriaAdminList;
import com.example.hairnada.vo.page.PageAdminListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/store/*")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

//    상품 리스트
    @GetMapping("/productList")
    public void productList(CriteriaAdminList criteriaAdminList, Model model){
        List<StoreDto> productList = storeService.findStoreList(criteriaAdminList);

        System.out.println(productList);
        model.addAttribute("productList", productList);
        model.addAttribute("pageInfo", new PageAdminListVo(criteriaAdminList,storeService.findStoreListTotal()));
    }

    @GetMapping("/productRead")
    public String productRead(Long storeNumber, Model model){
        StoreDto storeDto = storeService.findStore(storeNumber);
        model.addAttribute("store",storeDto);
        System.out.println(storeDto);
        return "store/productRead";
    }

}
