package chj.com.myapp.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import chj.com.myapp.dao.Dao;
import chj.com.myapp.dto.Dto;

public class ListCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		ArrayList<Dto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
