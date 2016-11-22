package online.decentworld.tools;

import java.util.Calendar;

/**
 * Created by Sammax on 2016/11/22.
 */
public class HbaseRowKeyHelper {

    public static byte[] getChatHistoryRowkey(String from,String to,String year_month){
        String rowKey=year_month;
        if(Long.parseLong(from)>Long.parseLong(to)){
            rowKey=to+from+rowKey;
        }else{
            rowKey=from+to+rowKey;
        }
        return MD5.getMD5(rowKey);
    }


    public static byte[] getCurrentChatHistoryRowkey(String from,String to){
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        String rowKey=String.valueOf(year)+String.valueOf(month);
        if(Long.parseLong(from)>Long.parseLong(to)){
            rowKey=to+from+rowKey;
        }else{
            rowKey=from+to+rowKey;
        }
        return MD5.getMD5(rowKey);
    }
}
