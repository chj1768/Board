package chj.com.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chj.com.myapp.command.Command;
import chj.com.myapp.command.ContentCommand;
import chj.com.myapp.command.DeleteCommand;
import chj.com.myapp.command.ListCommand;
import chj.com.myapp.command.ModifyCommand;
import chj.com.myapp.command.ReplyCommand;
import chj.com.myapp.command.ReplyViewCommand;
import chj.com.myapp.command.WriteCommand;

@Controller
public class MainController {
	
	Command command;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new ListCommand();
		command.execute(model);
		
		return "list";
		
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		
		return "write_view";
		
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new WriteCommand();
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new ContentCommand();
		command.execute(model);
		
		return "content_view";
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new ModifyCommand();
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new ReplyCommand();
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new ReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		command = new DeleteCommand();
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	
}
