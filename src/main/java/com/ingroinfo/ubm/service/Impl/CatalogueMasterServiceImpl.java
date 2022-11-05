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
import com.ingroinfo.ubm.dao.CatalogueMasterRepository;
import com.ingroinfo.ubm.dto.CatalogueMasterDto;
import com.ingroinfo.ubm.entity.CatalogueMaster;
import com.ingroinfo.ubm.service.CatalogueMasterService;

@Service
public class CatalogueMasterServiceImpl implements CatalogueMasterService {

	@Autowired
	private CatalogueMasterRepository catalogueRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CatalogueMasterDto saveCatalogue(CatalogueMasterDto catalogueMasterDto) {

		CatalogueMaster catalogue = this.modelMapper.map(catalogueMasterDto, CatalogueMaster.class);
		CatalogueMaster savedCatalogue = catalogueRepo.save(catalogue);
		return this.modelMapper.map(savedCatalogue, CatalogueMasterDto.class);
	}

	@Override
	public List<CatalogueMasterDto> getAllCatalogueData() {
		List<CatalogueMaster> catalogueMasters = this.catalogueRepo.findAll();
		List<CatalogueMasterDto> catalogueMasterDtos = catalogueMasters.stream()
				.map((catalogue) -> this.modelMapper.map(catalogue, CatalogueMasterDto.class))
				.collect(Collectors.toList());
		return catalogueMasterDtos;
	}

	@Override
	public void deleteCatalogue(Long catalogueId) {

		catalogueRepo.deleteById(catalogueId);
	}

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

}
