package com.zhipengs.resteasy.demo.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ObjectMapper;

import com.zhipengs.resteasy.demo.model.Author;
import com.zhipengs.resteasy.demo.model.Book;

/**
 * BookDao
 * 
 * @author shaozhipeng
 * 
 */
public class BookDao extends AbstractDao {

	@Override
	public void init() {
		super.setStatementPrefix("Book");
	}

	/**
	 * 返回单个Book
	 * 
	 * @param id
	 * @return Book
	 */
	public Book getBookById(int id) {
		Object obj = this.selectOne(getStatement("getBookById"), id);
		logger.info("bookInfo==" + obj);
		return obj == null ? null : (Book) obj;
	}

	/**
	 * 返回数据列表
	 * 
	 * @param start
	 * @param limit
	 * @return List<Book>
	 */
	public List<Book> getBookList(int start, int limit) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", start);
		paramMap.put("limit", limit);
		List<Book> bookList = this.selectList(getStatement("getBookList"),
				paramMap);
		return bookList == null ? new ArrayList<Book>() : bookList;
	}

	/**
	 * 演示MyBatis插入数据(对Author的新增数据就写在BookDao里面～～演示而已)
	 * 
	 * @param authorStr
	 * @return Map<String, Object>
	 */
	public Map<String, Object> addAuthor(String authorStr) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		SqlSession session = null;
		try {
			ObjectMapper om = new ObjectMapper();
			Author author = om.readValue(authorStr, Author.class);

			session = getSqlSessionFactory().openSession(ExecutorType.BATCH,
					false);
			Connection conn = session.getConnection();
			conn.setAutoCommit(false);

			session.insert(getStatement("addAuthor"), author);
			session.commit();

			resultMap.put("code", 200);
		} catch (Exception e) {
			if (session != null) {
				session.rollback(true);
			}
			logger.error("插入Author数据失败");
			e.printStackTrace();
			resultMap.put("code", 500);
			return resultMap;
		} finally {
			if (session != null)
				session.close();
		}

		return resultMap;
	}
}
