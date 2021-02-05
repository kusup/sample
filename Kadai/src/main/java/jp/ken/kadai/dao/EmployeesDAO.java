package jp.ken.kadai.dao;

import java.util.List;

import jp.ken.kadai.entity.Employees;

public interface EmployeesDAO<T> {

	public List<T> allList();

	public Employees getById(int id);

	public List<T> getByName(String name);

	public boolean insertEmployeesData(Employees employees);

	public int updateEmployeesData(Employees employees);

	public int deleteEmployeesData(int id);
}