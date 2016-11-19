package cn.buaaqingyuan.Seg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;

import cn.buaaqingyuan.Sql.SqlUtil;


public class SegContent {
	
	
	public static String parse(String content)
	{
		List<Term> parse = ToAnalysis.parse(Util.filter(content));
		String result = "";
		for(Term one:parse)
		{
			result += one.getName()+" ";
		}
		return result.trim();
	}
	
	
	public static void  seg(String outputfile) throws IOException
	{
		SqlSession session=SqlUtil.getSession();
		
		List<String> contents = SqlUtil.getContents(session, 0);
		
		File  phrase_f = new File(outputfile);
		
		for(String content:contents)
		{
			String result = parse(content);
			System.out.println(result);
			FileUtils.write(phrase_f, result+"\n", true);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		SegContent.seg("paper.txt");
	}
	
}
