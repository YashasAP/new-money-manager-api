package com.mymanger.moneymanager.service;

import com.mymanger.moneymanager.dto.CatagoryDTO;
import com.mymanger.moneymanager.entity.CatagoryEntity;
import com.mymanger.moneymanager.entity.ProfileEntity;
import com.mymanger.moneymanager.repository.CatagoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatagoryService {

       private final ProfileService profileService;
       private final CatagoryRepository catagoryRepository;

  //sava category
  public CatagoryDTO saveCategory(CatagoryDTO categoryDTO) {
      ProfileEntity profile = profileService.getCurrentProfile();
      if (catagoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId())) {
          throw new RuntimeException("Category with this name already exists");
      }

      CatagoryEntity newCategory = toEntity(categoryDTO, profile);
      newCategory = catagoryRepository.save(newCategory);
      return toDTO(newCategory);
  }


    //get categories for current user
    public List<CatagoryDTO> getCategoriesForCurrentUser() {
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CatagoryEntity> categories = catagoryRepository.findByProfileId(profile.getId());
        return categories.stream().map(this::toDTO).toList();
    }

    //get catagories by type for current user
    public List<CatagoryDTO> getCategoriesByTypeForCurrentUser(String type) {
        ProfileEntity profile = profileService.getCurrentProfile();
        List<CatagoryEntity> entities = catagoryRepository.findByTypeAndProfileId(type, profile.getId());
        return entities.stream().map(this::toDTO).toList();
    }

    public CatagoryDTO updateCategory(Long categoryId, CatagoryDTO dto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        CatagoryEntity existingCategory = catagoryRepository.findByIdAndProfileId(categoryId, profile.getId())
                .orElseThrow(() -> new RuntimeException("Category not found or not accessible"));
        existingCategory.setName(dto.getName());
        existingCategory.setIcon(dto.getIcon());
        existingCategory = catagoryRepository.save(existingCategory);
        return toDTO(existingCategory);
    }



    // helper methods
    private CatagoryEntity toEntity(CatagoryDTO catagoryDTO, ProfileEntity profile) {
        return CatagoryEntity.builder()
                .name(catagoryDTO.getName())
                .icon(catagoryDTO.getIcon())
                .profile(profile)
                .type(catagoryDTO.getType())
                .build();
    }
    private CatagoryDTO toDTO(CatagoryEntity entity) {
        return CatagoryDTO.builder()
                .id(entity.getId())
                .profileId(entity.getProfile() != null ? entity.getProfile().getId() : null)
                .name(entity.getName())
                .icon(entity.getIcon())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .type(entity.getType())
                .build();
    }

}