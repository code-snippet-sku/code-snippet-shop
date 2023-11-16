package com.sku.codesnippetshop.domain.Brand.service;


import com.sku.codesnippetshop.domain.Brand.dao.BrandRepository;
import com.sku.codesnippetshop.domain.Brand.domain.Brand;
import com.sku.codesnippetshop.domain.Brand.dto.BrandCreateDTO;
import com.sku.codesnippetshop.domain.Brand.dto.BrandReadDTO;
import com.sku.codesnippetshop.domain.Brand.dto.BrandUpdateDTO;
import com.sku.codesnippetshop.global.error.NotFoundException;
import com.sku.codesnippetshop.global.response.ResponseFormat;
import com.sku.codesnippetshop.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    /*브랜드 생성 서비스
   param : 생성 브랜드 info*/
    @Transactional
    public void createBrand(BrandCreateDTO create){
        final Brand brand = Brand.dtoToEntity(create);
        brandRepository.save(brand);
    }

    /*브랜드 전부읽기 서비스
    param : X */
    public List<BrandReadDTO> getBrands(){
        final List<Brand> brandList = brandRepository.findAll();

        return brandList.stream()
                .map(Brand::entityToDto)
                .toList();
    }


    /*브랜드 수정 서비스
    param : 수정 브랜드 아이디(pk), 수정 info*/
    @Transactional
    public void updateBrand(BrandUpdateDTO update){

        Brand brand = brandRepository.findById(update.getBrandId())
                .orElseThrow(()-> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        brand.updateBrand(update);
    }

    /*브랜드 삭제 서비스
    param : 삭제 브랜드 아이디(pk)*/
    @Transactional
    public void deleteBrandByBrandId(Long brandId){
        final Brand brand = brandRepository
                .findById(brandId)
                .orElseThrow(()->new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        brandRepository.delete(brand);
    }

}
