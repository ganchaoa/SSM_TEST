package com.gan.ssm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gan.ssm.dao.BookDao;
import com.gan.ssm.entity.Book;
import com.gan.ssm.service.BookService;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	    @Resource
	    BookDao bookDao;
	    
	    
	    public List<Book> getAllBook() {    
	        return bookDao.getAllBook();
	    }

}
