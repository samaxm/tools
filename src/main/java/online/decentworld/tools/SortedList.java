package online.decentworld.tools;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sammax on 2016/10/19.
 */
public class SortedList<T> {

    private HashMap<String,SortedElement<T>> table=new HashMap<>();
    private LinkedList<SortedElement<T>> queue=new LinkedList<>();
    private void sort(){
        Collections.sort(queue);
    }

    public void addElement(long score,String key,T t){
        SortedElement e=new SortedElement(score,key,t);
        T v= (T) table.putIfAbsent(key,e);

        if(v==null){
            queue.add(e);
        }else{
            table.put(key,e);
            queue.remove(e);
            queue.add(e);
        }
        sort();
    }

    public void addTimelineElement(String key,T t){
        addElement(System.currentTimeMillis(),key,t);
    }

    public void removeByKey(String key){
        table.remove(key);
        queue.remove(SortedElement.KeyExample(key));
    }

    public SortedElement<T> getByKey(String key){
        return table.get(key);
    }


    public SortedElement<T> getByIndex(int index){
        return queue.get(index);
    }

    public List<SortedElement<T>> getRangeByIndex(int start,int end){
        Range range=getRange(start,end);
        if(range.getStart()>queue.size()-1){
            return Collections.EMPTY_LIST;
        }else{
            List<SortedElement<T>> list=new LinkedList<>();
            for(int i= (int) range.getStart();i<range.getEnd();i++){
                list.add(queue.get(i).clone());
            }
            return list;
        }
    }

    public List<SortedElement<T>> getRangeByRank(long start,long end){
        long min=queue.get(0).getScore();
        long max=queue.get(queue.size()-1).getScore();
        if(start>max||end<min){
            return Collections.EMPTY_LIST;
        }else{
            List<SortedElement<T>> list=new LinkedList<>();
           queue.forEach((SortedElement<T> e)->{
                   if(e.getScore()<end&&e.getScore()>=start){
                       list.add(e.clone());
                   }
           });
            return list;
        }
    }


    public void removeByRank(long start,long end){
        long min=queue.get(0).getScore();
        long max=queue.get(queue.size()).getScore();
        if(start>max||end<min){
            return;
        }else{
            List<SortedElement<T>> list=new LinkedList<>();
            queue.forEach((SortedElement<T> e)->{
                if(e.getScore()<end&&e.getScore()>=start){
                    table.remove(e.getKey());
                    queue.remove(e);

                }
            });
        }
    }

    public void removeByIndex(int start,int end){
        Range range=getRange(start, end);
        if(range.getStart()>queue.size()-1){
            return;
        }else{
            List<SortedElement<T>> list=new LinkedList<>();
            for(int i= (int) range.getStart();i<range.getEnd();i++){
                table.remove(queue.get(i).getKey());
                queue.remove(i);
            }
        }
    }



    private Range getRange(long start,long end){
        start=getIndex(start);
        end=getIndex(end);
        if(start<end){
            return new Range(start,end);
        }else{
            return new Range(end,start);
        }
    }

    private long getIndex(long num){
        if(num<0){
            num=queue.size()-Math.abs(num);
            if(num<0){
                num=0;
            }
        }
        return num;
    }


    static class Range{
        long start;
        long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }
    }

    static final class SortedElement<T> implements Comparable<SortedElement>,Cloneable{
        private long score;
        private String key;
        private T value;

        public SortedElement(long score, String key, T value) {
            this.score = score;
            this.key = key;
            this.value = value;
        }

        public static SortedElement KeyExample(String key){
            return new SortedElement(0,key,null);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SortedElement that = (SortedElement) o;

            if (!key.equals(that.key)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        public long getScore() {
            return score;
        }

        public void setScore(long score) {
            this.score = score;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(SortedElement o) {
            return (int) (this.getScore()-o.getScore());
        }

        @Override
        protected SortedElement<T> clone() {
            return new SortedElement<T>(this.score,this.key,this.value);
        }
    }


    public static void main(String[] args) {
        SortedList<String> list=new SortedList<>();
        list.addElement(4,"123","a");
        list.addElement(5,"123","b");
        list.addElement(1,"456","c");
        list.addElement(3,"789","d");
        list.addElement(6,"012","e");
        list.removeByKey("123");
        List<SortedElement<String>> list2=list.getRangeByIndex(0, -2);
        List<SortedElement<String>> list3=list.getRangeByRank(-2,4);
        System.out.println(1);

    }
}
