package com.ingroinfo.ubm.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dao.BrandMasterRepository;
import com.ingroinfo.ubm.dto.BrandMasterDto;
import com.ingroinfo.ubm.entity.BrandMaster;
import com.ingroinfo.ubm.service.BrandMasterService;

@Service
public class BrandMasterServiceImpl implements BrandMasterService {

	@Autowired
	private BrandMasterRepository brandMasterRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

	}

	@Override
	public void saveBrand(BrandMasterDto brandMasterDto) {

		BrandMaster brand = new BrandMaster();
		brand.setBrandId(brandMasterDto.getBrandId());
		brand.setBrandName(brandMasterDto.getBrandName());
		brand.setBrandLogo(brandMasterDto.getBrandLogo());
		brand.setLogoDir(brandMasterDto.getLogoDir());

		brandMasterRepository.save(brand);

	}

	@Override
	public List<BrandMasterDto> getAllRecord() {

		List<BrandMaster> brandMaster = this.brandMasterRepository.findAll();
		List<BrandMasterDto> brandMasterDtos = brandMaster.stream()
				.map((brand) -> this.modelMapper.map(brand, BrandMasterDto.class)).collect(Collectors.toList());

		return brandMasterDtos;
	}

	@Override
	public void deleteBrand(Long brandId) {

		brandMasterRepository.deleteById(brandId);

	}

}
