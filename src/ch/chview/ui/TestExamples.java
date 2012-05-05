package ch.chview.ui;

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

    
    public String getSQL()
    {
        return "select * \n" +
                "from ui \n" +
                "where id = " + cnt;
    }
}
