package jp.ken.kadai.controller;

import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import jp.ken.kadai.dao.DepartmentsDAO;
import jp.ken.kadai.dao.EmployeesDAO;
import jp.ken.kadai.entity.Departments;
import jp.ken.kadai.entity.Employees;
import jp.ken.kadai.model.EmployeesModel;
import jp.ken.kadai.model.ErrorCheckGroup1;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("regist")
public class EmployeesRegistController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	@SuppressWarnings("unchecked")
	private static EmployeesDAO<Employees> empDAO = (EmployeesDAO<Employees>)context.getBean("employeesDAO");

	private DepartmentsDAO<Departments> deptDAO = null;

	@GroupSequence({ Default.class, ErrorCheckGroup1.class })
	interface GroupOrder {}

	@RequestMapping(method=RequestMethod.GET)
	public String toRegist(Model model) {
		EmployeesModel eModel = new EmployeesModel();
		model.addAttribute("employeesModel", eModel);
		model.addAttribute("deptList", this.makeSelectDepartments());
		return "registEmployeesData";
	}

	@RequestMapping(method=RequestMethod.POST, params="regist")
	public String regist(
		@Validated(GroupOrder.class) @ModelAttribute EmployeesModel eModel,
		BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "エラーが発生しています");
			model.addAttribute("deptList", this.makeSelectDepartments());
			return "registEmployeesData";
		} else {
			Employees emp = new Employees();
			BeanUtils.copyProperties(eModel, emp);
			if (empDAO.insertEmployeesData(emp)) {
				return "resultEmployeesData";
			} else {
				model.addAttribute("errorMessage", "SQLエラーが発生しています");
				model.addAttribute("deptList", this.makeSelectDepartments());
				return "registEmployeesData";
			}
		}
	}

	@RequestMapping(method=RequestMethod.POST, params="reset")
	public String reset(Model model) {
		EmployeesModel eModel = new EmployeesModel();
		model.addAttribute("employeesModel", eModel);
		model.addAttribute("deptList", this.makeSelectDepartments());
		return "registEmployeesData";
	}

	@SuppressWarnings("unchecked")
	private List<Departments> makeSelectDepartments() {
		if (deptDAO == null) {
			deptDAO = (DepartmentsDAO<Departments>)context.getBean("departmentsDAO");
		}
		return deptDAO.allList();
	}
}