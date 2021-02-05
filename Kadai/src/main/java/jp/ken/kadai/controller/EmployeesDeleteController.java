package jp.ken.kadai.controller;

import jp.ken.kadai.dao.EmployeesDAO;
import jp.ken.kadai.entity.Employees;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("delete")
public class EmployeesDeleteController {

	private static ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	@SuppressWarnings("unchecked")
	private static EmployeesDAO<Employees> empDAO = (EmployeesDAO<Employees>)context.getBean("employeesDAO");

	@RequestMapping(method=RequestMethod.GET)
	public String toDelete(@RequestParam int paramId, Model model) {
		Employees emp = empDAO.getById(paramId);
		model.addAttribute("deleteEmployeesData", emp);
		return "deleteEmployeesData";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String delete(@RequestParam int employee_id, Model model) {
		if (empDAO.deleteEmployeesData(employee_id) == 1) {
			return "redirect:/change";
		} else {
			model.addAttribute("errorMessage", "SQLエラーが発生しています");
			return "deleteEmployeesData";
		}
	}
}