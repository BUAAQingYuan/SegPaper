package cn.buaaqingyuan.Seg;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.hankcs.hanlp.HanLP;

public class ExtractPhrase {
	
	public static List<String>  phrase(String text)
	{
		List<String> phrases = HanLP.extractPhrase(text, 2);
		return phrases;
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
			String[] temp = line.split(" ");
			if(temp.length !=2)
			{
				System.out.println("error => "+String.valueOf(count));
				continue;
			}
			List<String> phrases = phrase(temp[0]);
			for(String phrase:phrases)
			{
				FileUtils.write(phrase_f, phrase+"\n", true);
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		ExtractPhrase.GeneratePhrase("keywordrank.txt", "keyword.txt");
	}
	
}
