package kata.ex01.util

import java.io._
import java.net.URL
import java.nio.charset.StandardCharsets
import java.time._
import java.time.format.DateTimeFormatter
import java.util.Comparator
import java.util
import java.util.regex.Pattern


/**
 * @author erikaadno
 */
object HolidayUtils {
  private val DTSTART_PTN = Pattern.compile("^DTSTART;VALUE=DATE:(\\d{8})$")
  private val SUMMARY_PTN = Pattern.compile("^SUMMARY:(.+)$")
  private val HOLIDAYS_OF_WEEK = util.EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
  private val holidays = new util.HashMap[LocalDate, String]

  private def isCacheAvailable(cacheFile: File) = {
    val now = Instant.now
    cacheFile.exists && (LocalDateTime.ofEpochSecond(cacheFile.lastModified / 1000, ((cacheFile.lastModified % 1000) * 1000).toInt, ZoneId.systemDefault.getRules.getOffset(now)).getYear == LocalDateTime.ofInstant(now, ZoneId.systemDefault).getYear)
  }

  @throws[FileNotFoundException]
  private def createOutputStream(file: File) = if (isCacheAvailable(file)) new OutputStream() {
    @throws[IOException]
    override def write(b: Int): Unit = {
      // doNothing
    }
  }
  else {
    if (!file.getParentFile.exists) file.getParentFile.mkdirs
    new FileOutputStream(file)
  }

  def isHoliday(d: LocalDate): Boolean = HOLIDAYS_OF_WEEK.contains(d.getDayOfWeek) || holidays.containsKey(d)

  try {
    val cacheFile = new File("target/basic.ics")
    val url = if (cacheFile.exists) cacheFile.toURI.toURL
    else new URL("https://calendar.google.com/calendar/ical/ja.japanese%23holiday%40group.v.calendar.google.com/public/basic.ics")
    val in = url.openConnection.getInputStream
    val reader = new BufferedReader(new InputStreamReader(in))
    val writer = new BufferedWriter(new OutputStreamWriter(createOutputStream(cacheFile), StandardCharsets.UTF_8))

    try {
      var line: String = ""
      val ptn = DateTimeFormatter.ofPattern("yyyyMMdd")

      while ({ line = reader.readLine(); line ne null }) {

        val (name, date): (String, LocalDate) =
          if ("BEGIN:VEVENT" == line) {
          (null,  null)
        }
          else if (line.startsWith("DTSTART;VALUE=DATE")) {
          val dtstartMatcher = DTSTART_PTN.matcher(line)
          val hoge = if (dtstartMatcher.matches) {
            (null, LocalDate.parse(dtstartMatcher.group(1), ptn))
          } else (null, null)
          hoge
        } else if (line.startsWith("SUMMARY:")) {
          val summaryMatcher = SUMMARY_PTN.matcher(line)
          if (summaryMatcher.matches) (summaryMatcher.group(1), null)
            else (null, null)
        } else (null, null)

        holidays.put(date, name)
        writer.append(line).append("\n")
      }
    } finally {
      if (in != null) in.close()
      if (reader != null) reader.close()
      if (writer != null) writer.close()
    }
  } catch {
    case e: IOException =>
      throw new IllegalStateException(e)
  }
}
