/**
 * @Copyright: Herb2Sy
 */
package com.herb2sy;


import com.herb2sy.utils.StringUtils;

public class Test {

    public static void main(String [] arg){
        String s = StringUtils.obj2Str(null,"hello world");
        System.out.println(s);
    }
}
