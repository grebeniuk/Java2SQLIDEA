import java.math.BigInteger;

/**
 * Test examples of string that must be parsed by the Plug In.
 * For the moment the class is used only in manual testing purpose.
 * TODO: it will be quite useful if some Unit Tests are implemented based on these examples.
 */
public class TestExamples
{
    BigInteger cnt = new BigInteger("1");
    final BigInteger cnt2 = BigInteger.valueOf(1L);
    final String cnt3 = "1";

    final String s = "select * from ui where id = " + cnt + "1";
    final String s2 = "select * from ui where id = " + cnt2;
    final String s3 = "select * from ui where id = " + cnt3;
    final String s4 = "select * from ui where id = " + cnt3.toString();
    final String s5 = "select * from ui where id = " + DBConstants.FILTER_SQL_VAR;
    final String s6 = DBConstants.executeQuerySingle("select 1 as m from dual", null, "m");
    //final String s7 = "select " + UNSPECIFIED_VAR + " from dual";
    final String s8 = new StringBuilder(870)
            .append("SELECT c.nm chemnm\n")
            .append(" ,c.nm_html chemnmhtml\n")
            .append("from test")
            .append("where c.t = ")
            .append(DBConstants.FILTER_SQL_VAR)
            .append("and c.a")
            .append(" = ")
            .append(cnt2)
            .toString();

    public String getSQL()
    {
        return "select * \n" +
                "from ui \n" +
                "where id = " + cnt;
    }
}
