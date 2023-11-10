package TimeOfJdk8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;


public class TimeTest {
   /*
   *
   * ava 8专门新增了一个java.time包，该包下包含了如下常用的类。

    Clock：该类用于获取指定时区的当前日期、时间。该类可取代System类的currentTimeMillis()方法，而且提供了更多方法来获取当前日期、时间，

    Duration：该类代表持续时间。该类可以很方便地获取一段时间。

    Instant：代表一个具体的时间，可以精确到纳秒。该类提供了静态的now()方法来获取当前日期，也提供了静态的now(Clock clock)方法了获取clock对应的日期。除此之外，它还提供了minusXxx()方法在当前年份上加上几年、几月、几周或几日等，也提供了plusXxx()方法在当前年份上加上几年、几月几周或几日。

    LocalDate：该类代表不带时区的日期，例如2007-12-03。

    LocalTime：该类代表不带时区的时间，例如10:15:30。

    LocalDateTime：该类代表不带时区的日期、时间。例如2007-12-03T10:15:30。

    ZonedDateTime：该类代表一个时区化的日期、时间。

    ZoneID：该类代表一个时区。
   * */

    public static void main(String[] args) throws ParseException {
        Date dt = new Date();
        Locale[] locales = {Locale.CHINA, Locale.US};
        DateFormat[] df = new DateFormat[16];
        for (int i = 0; i < locales.length; i++) {
            df[i * 8] = DateFormat.getDateInstance(DateFormat.SHORT, locales[i]);
            df[i * 8 + 1] = DateFormat.getDateInstance(DateFormat.MEDIUM, locales[i]);
            df[i * 8 + 2] = DateFormat.getDateInstance(DateFormat.LONG, locales[i]);
            df[i * 8 + 3] = DateFormat.getDateInstance(DateFormat.FULL, locales[i]);
            df[i * 8 + 4] = DateFormat.getTimeInstance(DateFormat.SHORT, locales[i]);
            df[i * 8 + 5] = DateFormat.getTimeInstance(DateFormat.MEDIUM, locales[i]);
            df[i * 8 + 6] = DateFormat.getTimeInstance(DateFormat.LONG, locales[i]);
            df[i * 8 + 7] = DateFormat.getTimeInstance(DateFormat.FULL, locales[i]);
        }

        Date d = new Date();
        //创建一个SimpleDateFormat对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        //将d格式化成日期，输出:公元2016年中第223天
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);

        //一个非常特殊的日期字符串
        String str = "16###八月##10";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        //将日期中字符串解析成日期输出:Wed Aug 10 00:00:00 CST 2016
        System.out.println(sdf2.parse(str));

        //定义一个任意格式的日期、时间字符串
        String str1 = "2016==08==10 01时17分10秒";
        //根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter formatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");
        //执行解析
        LocalDateTime dt1 = LocalDateTime.parse(str1,
                formatter1);
        System.out.println(dt1);//输出2016-08-10T01:17:10

        //下面代码再次解析另一个字符串
        String str2 = "2016$$$八月$$$10 20时";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy$$$MMM$$$dd HH时");
        LocalDateTime dt2 = LocalDateTime.parse(str2,
                formatter2);
        System.out.println(dt2);//输出2016-08-10T20:00

        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                //直接使用  常量创建DateTimeFormatter格式器
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,

                //使用本地化的不同风格来创建DateTimeFormatter格式器
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,
                        FormatStyle.MEDIUM),
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),

                //根据模式字符串来创建DateFormatter格式器
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
        };

        LocalDateTime date = LocalDateTime.now();

        //依次使用不同的格式器对LocalDateTime进行格式化
        for (int i = 0; i < formatters.length; i++) {
            //下面两行代码的作用相同
            System.out.println(date.format(formatters[i]));
            System.out.println(formatters[i].format(date));
        }
    }
}
