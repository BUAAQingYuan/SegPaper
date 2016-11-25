package cn.buaaqingyuan.Seg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.hankcs.hanlp.HanLP;

public class SegPhrase {
	
	private static List<String> patterns = new ArrayList<String>(Arrays.asList("ng,a,p","ng,d","a,ng,ng",
			"v,nz","n,ng,j","n,g","vg,g,n","d,v,q","n,ag,ag","v,k","v,v,k","gb,j","gi,n","m,ng","vn,k","vn,ng"));
	
	public static List<Integer> findPattern(String naturestrs,String pattern)
	{
		//System.out.println(naturestrs);
		List<Integer> pos = new ArrayList<Integer>();
		int start = naturestrs.indexOf(pattern);
		//System.out.println(start);
		if (start == -1 || naturestrs.charAt(start-1) != ','||naturestrs.charAt(start+pattern.length())!=',')
		{
			return null;
		}
		String[] items = naturestrs.split(",");
		int target = 0;
		for(int i=0;i<items.length;i++)
		{
			start = start - items[i].length() -1 ;
			if(start == 0)
			{
				target = i;
				break;
			}
		}
		int length = pattern.split(",").length;
		for(int i=1;i<=length;i++)
		{
			pos.add(target+i-1);
		}
		return pos;
	}
	
	public static String  mergeSplit(List<String> splits,int begin,int end)
	{
		String result ="";
		for(int i=begin;i<=end;i++)
		{
			result = result+splits.get(i);
		}
		return result;
	}
	
	public static List<String>  phrase(String text)
	{
		List<String> words =new ArrayList<String>();
		List<Term> parse = ToAnalysis.parse(text);
		System.out.println(parse);
		String naturestrs = "";
		List<String> splits = new ArrayList<String>();
		for(Term one:parse)
		{
			naturestrs = naturestrs +","+one.getNatureStr();
			splits.add(one.getName());
		}
		naturestrs = naturestrs+",";
		
		for(String pattern:patterns)
		{
			List<Integer> pos = findPattern(naturestrs,pattern);
			if(pos != null)
			{
				//System.out.println(pos);
				//System.out.println(pattern);
				String result = mergeSplit(splits,pos.get(0),pos.get(pos.size()-1));
				//如果长度超过5，就不合并
				if(result.length()>5)
					continue;
				words.add(result);
			}
		}
		
		return words;
	}
	
	
	public static void  GeneratePhrase(String input,String output) throws IOException
	{
		final LineIterator it = FileUtils.lineIterator(new File(input), "UTF-8");
		
		File  phrase_f = new File(output);
		
		int count = 0;
		while( it.hasNext())
		{
			String line = it.nextLine();
			count++;
			/*
			List<String> phrases = phrase(line);
			for(String phrase : phrases)
			{
				FileUtils.write(phrase_f,phrase+"\n", true);
			}
			*/
			//seg
			FileUtils.write(phrase_f,SegContent.parse(line)+"\n", true);
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		//String text="低真空变压";
		//System.out.println(SegPhrase.phrase(text));
		SegPhrase.GeneratePhrase("merge.txt", "merge_fenci.txt");
		
	}

}
