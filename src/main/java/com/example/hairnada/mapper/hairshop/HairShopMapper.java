package com.example.hairnada.mapper.hairshop;

import com.example.hairnada.dto.hairshop.HairShopDto;
import com.example.hairnada.vo.hairshop.HairShopVo;
import com.example.hairnada.vo.page.Criteria03;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HairShopMapper {
    // 전체 조회
    public List<HairShopVo> selectAll(Criteria03 criteria03);

    // 전체 게시글 수 조회
    public int selectTotal();

    // 게시물 선택
    public HairShopVo select(Long hairShopNumber);

    // 게시물 삽입
    public void insert(HairShopDto hairShopDto);

    // 게시물 삭제
    public void delete(Long hairShopNumber);

    // 게시물 수정
    public void update(HairShopDto hairShopDto);
}
