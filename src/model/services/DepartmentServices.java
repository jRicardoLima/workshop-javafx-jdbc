package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentServices {
	
	public List<Department> findAll(){
		List<Department> list = new ArrayList<Department>();
		
		list.add(new Department(1,"Books"));
		list.add(new Department(2,"Computers"));
		list.add(new Department(3,"Fashion"));
		list.add(new Department(4,"Electronics"));
		list.add(new Department(5,"Games"));
		
		return list;
	}
}
