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
	
	
	public static void  seg(String outputfile,int total,int offset) throws IOException
	{
		SqlSession session=SqlUtil.getSession();
		
		int block = 10000;
		
		for(int i=1;i<=total;i++)
		{
			List<String> contents = SqlUtil.getContents(session, offset);
			
			File  phrase_f = new File(outputfile);
			
			for(String content:contents)
			{
				String result = parse(content);
				//System.out.println(result);
				FileUtils.write(phrase_f, result+"\n", true);
			}
			
			offset = offset + block ;
			System.out.format("already processed %f%%, total is %d\n", (float)i*100/total,i*block);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		SegContent.seg("paper.txt",500,0);
	}
	
}
