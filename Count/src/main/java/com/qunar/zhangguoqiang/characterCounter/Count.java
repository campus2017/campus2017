package com.qunar.zhangguoqiang.characterCounter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.*;

public class Count {
    public int en,ch,num,pu;
    public List<String> Topelement=new ArrayList<String>();
    public List<String> Topcount=new ArrayList<String>();
    public String text;
    public Count(String text)
    {
        this.text=text.trim();
        if(this.text!=null)
            characterCounter();
    }
    private void characterCounter()
    {
        Multiset<String> Top=HashMultiset.create();
        String enreg="[a-zA-Z]",chreg="[\\u4e00-\\u9fa5]",numreg="[0-9]",pureg="[\\pP]";
        for(int i=0;i<text.length();i++) {
            String temptext = text.charAt(i) + "";
            if (temptext.matches(enreg))
                en++;
            else if (temptext.matches(chreg))
                ch++;
            else if (temptext.matches(numreg))
                num++;
            else if (temptext.matches(pureg))
                pu++;
            Top.add(temptext);
        }
        ImmutableSet<Multiset.Entry<String>> topentry=Multisets.copyHighestCountFirst(Top).entrySet();
        int i=0;
        for(Multiset.Entry<String> t:topentry)
        {
            Topelement.add(t.getElement());
            Topcount.add(t.getCount()+"");
            if(i++>=2)
                break;
        }
    }
}
