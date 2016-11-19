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
		System.out.printf("already processed %f%%, total is %d\n", (float)1*100/500,50);
		
	}
	
}
