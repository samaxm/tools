package online.decentworld.tools;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sammax on 2016/10/19.
 *
 * add / set operation won't change order immediately
 * get* method will sort list first
 *
 * */
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
    }

    public void setRank(String key,long score){
        SortedElement element=table.get(key);
        if(element!=null){
            element.setScore(score);
        }else{
            throw new IllegalArgumentException("key don't exist");
        }
    }

    public void addTimelineElement(String key,T t){
        addElement(System.currentTimeMillis(),key,t);
    }

    public T removeByKey(String key){
        SortedElement<T> element=table.remove(key);
        queue.remove(SortedElement.KeyExample(key));
        return element.getValue();
    }

    public T getByKey(String key){
        return table.get(key).getValue();
    }


    public T getByIndex(int index){
        sort();
        index=getIndex(index);
        return queue.get(index).getValue();
    }

    public List<T> getRangeByIndex(int start,int end){
        sort();
        Range range=getRange(start,end);
        if(range.getStart()>queue.size()-1){
            return Collections.EMPTY_LIST;
        }else{
            List<T> list=new LinkedList<>();
            for(int i= (int) range.getStart();i<range.getEnd();i++){
                list.add(queue.get(i).getValue());
            }
            return list;
        }
    }

    public List<T> getRangeByRank(long start,long end){
        sort();
        long min=queue.get(0).getScore();
        long max=queue.get(queue.size()-1).getScore();
        if(start>max||end<min){
            return Collections.EMPTY_LIST;
        }else{
            List<T> list=new LinkedList<>();
           queue.forEach((SortedElement<T> e)->{
                   if(e.getScore()<end&&e.getScore()>=start){
                       list.add(e.getValue());
                   }
           });
            return list;
        }
    }


    public List<T> removeByRank(long start,long end){
       long min=queue.get(0).getScore();
        long max=queue.get(queue.size()).getScore();
        if(start>max||end<min){
            return Collections.EMPTY_LIST;
        }else{
            List<T> list=new LinkedList<>();
            queue.forEach((SortedElement<T> e)->{
                if(e.getScore()<end&&e.getScore()>=start){
                    table.remove(e.getKey());
                    queue.remove(e);
                    list.add(e.getValue());
                }
            });
            return list;
        }
    }

    public List<T> removeByIndex(int start,int end){
        sort();
        Range range=getRange(start, end);
        if(range.getStart()>queue.size()-1){
            return Collections.EMPTY_LIST;
        }else{
            List<T> list=new LinkedList<>();
            for(int i= (int) range.getStart();i<range.getEnd();i++){
                table.remove(queue.get(i).getKey());
                SortedElement<T> element=queue.remove(i);
                list.add(element.getValue());
            }
            return list;
        }
    }



    private Range getRange(int start,int end){
        start=getIndex(start);
        end=getIndex(end);
        if(start<end){
            return new Range(start,end);
        }else{
            return new Range(end,start);
        }
    }

    private int getIndex(int num){
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

    }
}
