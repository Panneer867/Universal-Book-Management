package com.ingroinfo.ubm.service.Impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.BrandRepository;
import com.ingroinfo.ubm.dao.UnitsRepository;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<UnitOfMeasures> getUnits() {

		return unitsRepository.findAll();
	}

	@Override
	public UnitOfMeasures findByUnitId(Long unitId) {

		return unitsRepository.findByUnitId(unitId);
	}

	@Override
	public void saveUnitOfMeasure(String unitOfMeasure) {

		UnitOfMeasures unit = new UnitOfMeasures();
		unit.setUnitOfMeasure(unitOfMeasure);

		unitsRepository.save(unit);
	}

	@Override
	public boolean unitExists(String unitOfMeasure) {

		return unitsRepository.findByUnitOfMeasure(unitOfMeasure) != null;
	}

	@Override
	public boolean unitOfMeasureCheck(Long unitId, String unitOfMeasure) {

		List<UnitOfMeasures> unitList = unitsRepository.findAll();

		List<UnitOfMeasures> filteredList = unitList.stream().filter(x -> !unitId.equals(x.getUnitId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUnitOfMeasure().equals(unitOfMeasure)).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public void updateUnitOfMeasure(UnitOfMeasures unit) {

		unitsRepository.save(unit);
	}

	@Override
	public void deleteByUnitId(Long unitId) {

		unitsRepository.deleteById(unitId);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean brandExists(String brandName) {

		return brandRepository.findByBrandName(brandName) != null;
	}

	@Override
	public void saveBrand(Brand brand) {
		brandRepository.save(brand);

	}

	@Override
	public List<Brand> getBrands() {

		return brandRepository.findAll();
	}

	@Override
	public Brand findByBrandId(Long brandId) {

		return brandRepository.findByBrandId(brandId);
	}

	@Override
	public void updateBrand(Brand brand) {

		brandRepository.save(brand);
	}

	@Override
	public void fileRename(String brandName, String companyName, Brand brand) {

		boolean isThere = brandName + ".png" == brand.getBrandLogo();

		if (!isThere) {
			File folder = new File("C:\\Company\\" + companyName + "\\Brands\\" + brand.getBrandLogo());
			folder.renameTo(new File("C:\\Company\\" + companyName + "\\Brands\\" + brandName + ".png"));
		}
	}

	@Override
	public boolean brandNameCheck(Long brandId, String brandName) {

		List<Brand> brandList = brandRepository.findAll();

		List<Brand> filteredList = brandList.stream().filter(x -> !brandId.equals(x.getBrandId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getBrandName().equals(brandName)).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public void deleteByBrandId(Long brandId) {

		brandRepository.deleteById(brandId);

	}

}
