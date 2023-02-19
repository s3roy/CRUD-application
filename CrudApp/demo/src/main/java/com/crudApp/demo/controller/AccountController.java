package com.crudApp.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crudApp.demo.exception.ResourceNotFoundException;
import com.crudApp.demo.model.Account;
import com.crudApp.demo.repository.AccountRepository;

@RestController
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	//get all the account
	
	@GetMapping("/accounts")
	public ModelAndView getAllAccount(){
		ModelAndView modelAndView = new ModelAndView();
		List<Account> allAccounts=this.accountRepository.findByDeletedOnIsNull();
		modelAndView.setViewName("index");
		modelAndView.addObject("accounts",allAccounts);
		return modelAndView;
	}
	
	// get account by id
	
	@GetMapping("/accounts/{id}")
	public ModelAndView getAccountId(@PathVariable(value="id") Long accountId ) 
		throws ResourceNotFoundException{
			Account account =accountRepository.findByIdAndDeletedOnIsNull(accountId)
					.orElseThrow(()->new ResourceNotFoundException("Account Not found for this Id: " + accountId));
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("index");
		    modelAndView.addObject("accounts",account);
		    return modelAndView;
		    
	}
	
	 //Insert
	@PostMapping("/accounts")
	public String createAccount(@ModelAttribute Account account,HttpServletResponse httpResponse) throws IOException, ResourceNotFoundException {
		
		if(account.getId()!=null) {
			Long accountId=account.getId();
			Account newaccount =accountRepository.findByIdAndDeletedOnIsNull(accountId)
					.orElseThrow(()->new ResourceNotFoundException("Account Not found for this Id: " + accountId));
			newaccount.setFullName(account.getFullName());
			newaccount.setCountry(account.getCountry());
			newaccount.setCurrency(account.getCurrency());
			newaccount.setExternlid(account.getExternlid());
			accountRepository.save(newaccount);
			httpResponse.sendRedirect("/accounts");
	        return null;
		}
		accountRepository.save(account);
		httpResponse.sendRedirect("/accounts");
        return null;
	}
	
	// update
//	@PutMapping("/edit/{id}")
//	public ResponseEntity<Account> updateAccount(@PathVariable(value="id") Long accountId,
//			@Valid @RequestBody Account accountDetails) throws ResourceNotFoundException{
//		Account account = accountRepository.findById(accountId)
//				.orElseThrow(()->new ResourceNotFoundException("Account not found for this is:" +accountId));
//				
//		account.setFullName(accountDetails.getFullName());
//		account.setCountry(accountDetails.getCountry());
//		account.setCurrency(accountDetails.getCurrency());
//		account.setExternlid(accountDetails.getExternlid());
//		final Account updatedAccount = accountRepository.save(account);
//		return ResponseEntity.ok(updatedAccount);
//	}
//	@GetMapping("/edit")
//	public ModelAndView editForm(@RequestParam Long accountId) {
//		
//	}
	
	
	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable(value="id") Long accountId,HttpServletResponse httpResponse) throws ResourceNotFoundException, IOException{
			
			LocalDateTime localDateTime = LocalDateTime.now();
			Account account = accountRepository.findById(accountId)
					.orElseThrow(()->new ResourceNotFoundException("Account not found for this is:" +accountId));
			account.setDeletedOn(localDateTime);
			accountRepository.save(account);
			
			//getAllAccounts();
			//return "redirect:/accounts";
			
//			ModelAndView modelAndView = new ModelAndView();
//			List<Account> allAccounts=this.accountRepository.findByDeletedOnIsNull();
//			modelAndView.setViewName("index");
//			modelAndView.addObject("accounts",allAccounts);
//			return modelAndView;
			//return "index";
			httpResponse.sendRedirect("/accounts");
	        return null;
			
		    
	}
	
}
