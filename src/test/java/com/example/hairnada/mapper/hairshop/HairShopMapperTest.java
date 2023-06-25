package com.example.hairnada.mapper.hairshop;

import com.example.hairnada.dto.hairshop.HairShopDto;
import com.example.hairnada.vo.hairshop.HairShopVo;
import com.example.hairnada.vo.page.Criteria03;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
public class HairShopMapperTest {
    @Autowired
    private HairShopMapper hairShopMapper;
    @Autowired
    private Criteria03 criteria03;
    private HairShopDto hairShopDto;
    private HairShopVo hairShopVo;

    @BeforeEach
    void setUp(){
        hairShopDto = new HairShopDto();
        hairShopDto.setHairShopAddress("서울 어딘가");
        hairShopDto.setHairShopName("헤어샵");
        hairShopDto.setHairShopOpenTime("오진 9시");
        hairShopDto.setHairShopPhoneNumber("01012341234");
        hairShopDto.setHairShopLink("www.aaa.co.kr");
        hairShopDto.setHairShopContent("헤어샵 소개글");
        hairShopDto.setUserNumber(2L);
        hairShopMapper.insert(hairShopDto);
    }

    @Test
    void selectAll(){
        List<HairShopVo> result = hairShopMapper.selectAll(criteria03);
        assertThat(result).isNotNull(); // 결과가 null이 아닌지 확인합니다.
        assertThat(result).hasSizeGreaterThan(0); // 결과 리스트의 크기가 0보다 큰지 확인합니다.
    }

    @Test
    void selectTotal(){
        int size = hairShopMapper.selectTotal();
        assertThat(size).isEqualTo(161);
    }

    @Test
    void select(){}

    @Test
    void insert(){}

    @Test
    void delete(){
        hairShopMapper.delete(hairShopDto.getHairShopNumber());
        assertThat(hairShopMapper.select(hairShopDto.getHairShopNumber())).isNull();
    }

    @Test
    void upadate(){
        hairShopDto.setHairShopName("변경된 헤어샵 이름");
        hairShopDto.setHairShopAddress("경기도 어딘가");
        hairShopMapper.update(hairShopDto);
        assertThat(hairShopMapper.select(hairShopDto.getHairShopNumber()).getHairShopName())
                .isEqualTo(hairShopDto.getHairShopName());
    }
}
