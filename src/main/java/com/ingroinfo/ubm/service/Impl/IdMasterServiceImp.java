package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.Id_Master_Repo;
import com.ingroinfo.ubm.dto.Id_MasterDTO;
import com.ingroinfo.ubm.entity.Id_Master;
import com.ingroinfo.ubm.service.Id_MasterService;

@Service
public class IdMasterServiceImp implements Id_MasterService {

	@Autowired
	private Id_Master_Repo idMasterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createIdMaster(Id_MasterDTO idMasterDto) {

		Id_Master id_Master = new Id_Master();
		id_Master.setAllMasterid(idMasterDto.getAllMasterid());
		id_Master.setId_type(idMasterDto.getId_type());
		id_Master.setSerial_number(idMasterDto.getSerial_number());
		id_Master.setLast_number(idMasterDto.getLast_number());
		id_Master.setDescription(idMasterDto.getDescription());
		idMasterRepo.save(id_Master);

	}

	@Override
	public List<Id_MasterDTO> getAllIdMaster() {

		// TODO Auto-generated method stub
		List<Id_Master> idMasters = this.idMasterRepo.findAll();
		List<Id_MasterDTO> idMasterDtos = idMasters.stream()
				.map((id_Master) -> this.modelMapper.map(id_Master, Id_MasterDTO.class)).collect(Collectors.toList());

		return idMasterDtos;
	}

	@Override
	public void deleteallMaster(Integer allMasterid) {
		// TODO Auto-generated method stub
		idMasterRepo.deleteById(allMasterid);
	}

	/*
	 * @Override public List<Id_MasterDTO> getId_Master() { // TODO Auto-generated
	 * 
	 * List<Id_Master> idMasterDetails =this.idMasterRepo.findAll();
	 * List<Id_MasterDTO> idDto = idMasterDetails.stream().map((id_Master)
	 * this.modelMapper.map(id_Master,Id_MasterDTO.class)).collect(Collectors.toList
	 * ()); return idDto; }
	 */

	/*
	 * @Override public Id_MasterDTO updateIdMaster(Id_MasterDTO idMasterDto,
	 * Integer id) { // TODO Auto-generated method stub Id_Master id_Master=
	 * this.idMasterRepo.findById(id).orElseThrow(()-> new
	 * ResourceNotFoundException("Id_Master","id", id));
	 * id_Master.setId_type(idMasterDto.getId_type());
	 * id_Master.setSerial_number(idMasterDto.getSerial_number());
	 * id_Master.setLast_number(idMasterDto.getLast_number());
	 * id_Master.setDescription(idMasterDto.getDescription());
	 * 
	 * Id_Master updateId_Master = this.idMasterRepo.save(id_Master);
	 * 
	 * 
	 * return this.modelMapper.map(updateId_Master,Id_MasterDTO.class); }
	 * 
	 * @Override public void deleteIdMaster(Integer id) { // TODO Auto-generated
	 * method stub Id_Master id_Master=
	 * this.idMasterRepo.findById(id).orElseThrow(()-> new
	 * ResourceNotFoundException("Id_Master", "id", id) );
	 * 
	 * this.idMasterRepo.delete(id_Master); }
	 * 
	 * @Override public Id_MasterDTO getId_Master(Integer id) { // TODO
	 * Auto-generated method stub
	 * 
	 * Id_Master id_Master= this.idMasterRepo.findById(id) .orElseThrow(()-> new
	 * ResourceNotFoundException("Id_Master","id", id) );
	 * 
	 * return this.modelMapper.map(id_Master,Id_MasterDTO.class); }
	 * 
	 * @Override public List<Id_MasterDTO> getId_Master() { // TODO Auto-generated
	 * method stub
	 * 
	 * List<Id_Master> idMasterDetails =this.idMasterRepo.findAll();
	 * List<Id_MasterDTO> idDto = idMasterDetails.stream().map((id_Master)->
	 * this.modelMapper.map(id_Master,Id_MasterDTO.class)).collect(Collectors.toList
	 * ()); return idDto; }
	 */

}
