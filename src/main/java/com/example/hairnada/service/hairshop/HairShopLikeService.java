package com.example.hairnada.service.hairshop;

import com.example.hairnada.dto.hairshop.HairShopLikeDto;
import com.example.hairnada.mapper.hairshop.HairShopLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HairShopLikeService {
    private final HairShopLikeMapper hairShopLikeMapper;

    // 좋아요 추가
    public void addLike(HairShopLikeDto hairShopLikeDto){
        if(hairShopLikeDto == null){
            throw new IllegalArgumentException("좋아요한 헤어샵 정보가 없습니다.");
        }
        hairShopLikeMapper.insert(hairShopLikeDto);
    }

    // 좋아요 삭제
    public void subtractLike(HairShopLikeDto hairShopLikeDto){
        if(hairShopLikeDto == null){
            throw new IllegalArgumentException("좋아요한 헤어샵이 존재하지 않습니다.");
        }
        hairShopLikeMapper.delete(hairShopLikeDto);
    }
}
