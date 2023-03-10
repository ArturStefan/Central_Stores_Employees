package com.central.stores.employees.mapper;

import java.time.LocalDate;

import com.central.stores.employees.model.Employee;
import com.central.stores.employees.model.dto.AddressDTO;
import com.central.stores.employees.model.dto.RequestEmployeeDTO;
import com.central.stores.employees.model.dto.ResponseEmployeeDTO;
import com.central.stores.employees.model.dto.ResponseSummarizedEmployeeDTO;


public final class EmployeeMapper {
	public static Employee toModel(RequestEmployeeDTO requestEmployeeDTO) {
		return Employee.builder()
				.active(Boolean.TRUE)
				.created(LocalDate.now())
				.rg(requestEmployeeDTO.getRg())
				.cpf(requestEmployeeDTO.getCpf())
				.name(requestEmployeeDTO.getName())
				.role(requestEmployeeDTO.getRole())
				.phone(requestEmployeeDTO.getPhone())
				.email(requestEmployeeDTO.getEmail())
				.gender(requestEmployeeDTO.getGender())
				.build();
	}
	

	public static Employee employeeDelete(Employee employee) {
		employee.setActive(Boolean.FALSE);
		employee.setChanged(LocalDate.now());
		
		return employee;
	}

	public static ResponseSummarizedEmployeeDTO modelToResponseSummarizedEmployeeDTO(Employee employee){
		return ResponseSummarizedEmployeeDTO.builder()
				.id(employee.getId())
				.name(employee.getName())
				.build();
	}
	
	public static Employee updateEmployee(Employee employee, RequestEmployeeDTO requestEmployeeDTO) {
		employee.setChanged(LocalDate.now());
		employee.setRg(requestEmployeeDTO.getRg());
		employee.setCpf(requestEmployeeDTO.getCpf());
		employee.setName(requestEmployeeDTO.getName());
		employee.setRole(requestEmployeeDTO.getRole());
		employee.setPhone(requestEmployeeDTO.getPhone());
		employee.setEmail(requestEmployeeDTO.getEmail());
		employee.setGender(requestEmployeeDTO.getGender());
		
		return employee;
	}
	
	public static ResponseEmployeeDTO modelToResponseEmployeeDTO(Employee employee) {
		return ResponseEmployeeDTO.builder()
				.id(employee.getId())
				.name(employee.getName())
				.cpf(employee.getCpf())
				.rg(employee.getRg())
				.role(employee.getRole())
				.gender(employee.getGender())
				.phone(employee.getPhone())
				.email(employee.getEmail())
				.address(addressIsNull(employee))
				.build();
	}
	
	private static AddressDTO addressIsNull(Employee employee) {
		AddressDTO addressDTO = null;
		
		if(employee.getAddress() != null) {
			addressDTO = AddressMapper.toDTO(employee.getAddress());
		}
		
		return addressDTO;
	}
}
