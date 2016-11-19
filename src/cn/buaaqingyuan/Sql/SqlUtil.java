package cn.buaaqingyuan.Sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlUtil {
	
	private static SqlSessionFactory sessionFactory = null;
	
	// 读取mybatis配置文件
	static {
		Reader reader = null;
		Properties prop = null;
		try {
			reader = Resources.getResourceAsReader("mybatis_config.xml");
			prop= new Properties();
			FileInputStream in = new FileInputStream("jdbc.properties");
			prop.load(in);
		} catch (IOException e) {
			System.out.println("读取数据库配置文件出错!");
			e.printStackTrace();
		}
		//sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sessionFactory = new SqlSessionFactoryBuilder().build(reader, prop);
	}
	
	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
	
	//获得一个表的所有列
	public static List<String> getContents(SqlSession session,int offset) {
		
		List<String> contents = new ArrayList<String>();
		
		Map<String, Object> create = new HashMap<String, Object>();
		create.put("offset",String.valueOf(offset));
		
		String state="SqlMapper.TemplateMapper.getContent";
		
		List<Object> data = session.selectList(state, create);
		for(Object one : data)
		{
			String title_cn = (String) ((HashMap<String,Object>)one).get("title_cn");
			String abstract_cn = (String) ((HashMap<String,Object>)one).get("abstract_cn");
			contents.add(title_cn+" "+abstract_cn);
			
		}
		
		return contents;
	}
	
}
