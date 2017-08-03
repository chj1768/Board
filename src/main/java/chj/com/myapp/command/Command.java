package chj.com.myapp.command;

import org.springframework.ui.Model;

import chj.com.myapp.dao.Dao;

public interface Command {

	public void execute( Model model );
	
}
