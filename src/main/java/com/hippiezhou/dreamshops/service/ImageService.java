package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.image.ImageDto;
import com.hippiezhou.dreamshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
