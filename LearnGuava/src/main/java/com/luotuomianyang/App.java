package com.luotuomianyang;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        String sourceData=null;
        boolean biaoshi = true;

        checkState(biaoshi);

        System.out.println(Objects.equal(null,"a"));
        System.out.println(Objects.equal(null,null));
        System.out.println(Objects.equal("a","a"));
        System.out.println( Objects.hashCode("a","b"));
        System.out.println( Objects.hashCode("a"));
        System.out.println( Objects.hashCode("ab"));

        MoreObjects.toStringHelper("");


        try {
            System.out.print(checkNotNull(sourceData));
        }catch (NullPointerException e){
            System.out.print("null..please init the string");
        }
    }
}
