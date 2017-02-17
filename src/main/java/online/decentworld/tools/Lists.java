package online.decentworld.tools;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sammax on 2017/1/14.
 */
public class Lists {


    public static List create(Object... arr){
        List list=new LinkedList<>();
        for(Object e:arr){
            list.add(e);
        }
        return list;
    }


    public static void main(String[] args) {
        List<Integer> integers=Lists.create(123,3333,1,3,4,5,7,4);
        integers.stream().filter(e->e%2==0).map(e->e*2).forEach(e-> System.out.println(e));
        for(int e:integers){
            System.out.println(e);
        }
    }

}
