package org.nmfw.foodietree.domain.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.customer.dto.resp.CustomerIssueDetailDto;
import org.nmfw.foodietree.domain.customer.dto.resp.CustomerMyPageDto;
import org.nmfw.foodietree.domain.customer.dto.resp.MyPageReservationDetailDto;
import org.nmfw.foodietree.domain.customer.service.CustomerMyPageService;
import org.nmfw.foodietree.domain.product.dto.response.CategoryByAreaDto;
import org.nmfw.foodietree.domain.product.dto.response.ProductDto;
import org.nmfw.foodietree.domain.product.dto.response.CategoryByFoodDto;
import org.nmfw.foodietree.domain.product.service.ProductMainPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductMainPageController {

    private final ProductMainPageService productMainPageService;
    private final CustomerMyPageService customerMyPageService;

    @GetMapping("/mainpage")
    public String mainpageMain(HttpSession session
                               , Model model
                                , HttpServletRequest request
                                , HttpServletResponse response) {
        log.info("/product/mainpage-main POST");

        //1. 로그인해논 회원 아이디 가져오기
        String customerId = "test@gmail.com";
        //2. DB에서 해당회원 데이터 조회하기
        CustomerMyPageDto customerMyPageDto = customerMyPageService.getCustomerInfo(customerId, request, response);

        List<MyPageReservationDetailDto> myPageReservationDetailDto = customerMyPageService.getReservationInfo(customerId);

        List<CustomerIssueDetailDto> customerIssueDetailDto = customerMyPageService.getCustomerIssues(customerId);
        // 3. JSP파일에 조회한 데이터 보내기
        model.addAttribute("customerMyPageDto", customerMyPageDto);
        model.addAttribute("reservations", myPageReservationDetailDto);
        model.addAttribute("issues", customerIssueDetailDto);

        List<ProductDto> productInfo = productMainPageService.getProductInfo();
        model.addAttribute("productList", productInfo);

        List<CategoryByFoodDto> categoryByFood = productMainPageService.getCategoryByFood(customerId);
        model.addAttribute("categoryByFood", categoryByFood);

        List<CategoryByAreaDto> categoryByArea = productMainPageService.getCategoryByArea(customerId);
        model.addAttribute("categoryByArea" ,categoryByArea);


        return "product/mainpage";
    }

}