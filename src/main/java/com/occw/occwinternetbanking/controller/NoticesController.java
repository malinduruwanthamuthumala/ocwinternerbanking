package com.occw.occwinternetbanking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.occw.occwinternetbanking.entities.Notice;

@RestController
public class NoticesController {

	@GetMapping("/occwp/pnotice")
	public List<Notice> getAccountDetails() {
		List<Notice> noticeList = new ArrayList<>();

        // Create Notice objects and add them to the list
        Notice notice1 = new Notice("Important Update", "Please read the following information carefully.");
        Notice notice2 = new Notice("Meeting Tomorrow", "Reminder: There is a team meeting scheduled for tomorrow.");

        // Add Notice objects to the list
        noticeList.add(notice1);
        noticeList.add(notice2);
        
        return  noticeList;
	}
}


