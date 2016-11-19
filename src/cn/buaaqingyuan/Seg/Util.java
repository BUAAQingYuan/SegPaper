package cn.buaaqingyuan.Seg;

public class Util {
	
	public static String  filter(String content)
	{
		content = content.replaceAll("[(]", "").replaceAll("[)]", "");
		String result = content.replaceAll("[^(\\u4E00-\\u9FA5)]", "");
		return result;
	}
	
	
	
	public static void main(String[] args)
	{
		String content = "";
		
		System.out.println(Util.filter(content));
		
	}
	
}
