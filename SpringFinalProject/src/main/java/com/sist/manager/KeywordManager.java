package com.sist.manager;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class KeywordManager {
   public static void main(String[] args) {
	    String keyword="���� �޴��ϰ� ���� ���̰��� �ִ� ��ä���� �Ҷ����� ����. ���尡���� ����� �ſ� ���� ���� �� ���� ������ ����, ������ ������ ���ŵ� �ɸ�ŭ �ڱ������� �ʰ� ���� ����. ��� �İ� ����ִ� ��. �������� �˵��� �İ��� ������ ������ �ʰ� ������ �ε巴�� �������� �İ��� ������ �ѵ��� ������ �����ʾ� �ж��Ŀ��Ե� ȯ������ ���� ���̴�.";
	    KeywordExtractor ke=new KeywordExtractor();
	    KeywordList list=ke.extractKeyword(keyword, true);
	    
	    for(int i=0;i<list.size();i++)
	    {
	    	Keyword wrd=list.get(i);
	    	System.out.println(wrd.getString()+":"+wrd.getCnt());
	    }
   }
}