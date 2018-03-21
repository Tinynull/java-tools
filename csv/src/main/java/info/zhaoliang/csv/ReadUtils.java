package info.zhaoliang.csv;

import info.zhaoliang.csv.pojo.CustomerBean;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ReadUtils {

    private static String CSV_FILENAME = "test.csv";
    private static String VARIABLE_CSV_FILENAME = "test2.csv";

    /**
     * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. Empty
     * columns are read as null (hence the NotNull() for mandatory columns).
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors() {

        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
        StrRegEx.registerMessage(emailRegex, "must be a valid email address");

        final CellProcessor[] processors = new CellProcessor[]{
                new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new ParseDate("dd/MM/yyyy"), // birthDate
                new NotNull(), // mailingAddress
                new Optional(new ParseBool()), // married
                new Optional(new ParseInt()), // numberOfKids
                new NotNull(), // favouriteQuote
                new StrRegEx(emailRegex), // email
                new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
        };

        return processors;
    }

    /**
     * An example of reading using CsvBeanReader.
     */
    private static void readWithCsvBeanReader() throws Exception {

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new InputStreamReader(ReadUtils.class.getClassLoader().getResourceAsStream(CSV_FILENAME)), CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            CustomerBean customer;
            while ((customer = beanReader.read(CustomerBean.class, header, processors)) != null) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
                        beanReader.getRowNumber(), customer));
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }
    }

    /**
     * An example of reading using CsvListReader.
     */
    private static void readWithCsvListReader() throws Exception {

        ICsvListReader listReader = null;
        try {
            listReader = new CsvListReader(new InputStreamReader(ReadUtils.class.getClassLoader().getResourceAsStream(CSV_FILENAME)), CsvPreference.STANDARD_PREFERENCE);

            listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
            final CellProcessor[] processors = getProcessors();

            List<Object> customerList;
            while ((customerList = listReader.read(processors)) != null) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
                        listReader.getRowNumber(), customerList));
            }

        } finally {
            if (listReader != null) {
                listReader.close();
            }
        }
    }

    /**
     * An example of reading using CsvMapReader.
     */
    private static void readWithCsvMapReader() throws Exception {

        ICsvMapReader mapReader = null;
        try {
            mapReader = new CsvMapReader(new InputStreamReader(ReadUtils.class.getClassLoader().getResourceAsStream(CSV_FILENAME)), CsvPreference.STANDARD_PREFERENCE);

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Map<String, Object> customerMap;
            while ((customerMap = mapReader.read(header, processors)) != null) {
                System.out.println(String.format("lineNo=%s, rowNo=%s, customerMap=%s", mapReader.getLineNumber(),
                        mapReader.getRowNumber(), customerMap));
            }

        } finally {
            if (mapReader != null) {
                mapReader.close();
            }
        }
    }

    /**
     * An example of reading a file with a variable number of columns using CsvListReader. It demonstrates that you can
     * still use cell processors, but you must execute them by calling the executeProcessors() method on the reader,
     * instead of supplying processors to the read() method. In this scenario, the last column (birthDate) is sometimes
     * missing.
     */
    private static void readVariableColumnsWithCsvListReader() throws Exception {

        final CellProcessor[] allProcessors = new CellProcessor[]{new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new ParseDate("dd/MM/yyyy")}; // birthDate

        final CellProcessor[] noBirthDateProcessors = new CellProcessor[]{allProcessors[0], // customerNo
                allProcessors[1], // firstName
                allProcessors[2]}; // lastName

        ICsvListReader listReader = null;
        try {
            listReader = new CsvListReader(new InputStreamReader(WriteUtils.class.getClassLoader().getResourceAsStream(VARIABLE_CSV_FILENAME)), CsvPreference.STANDARD_PREFERENCE);

            listReader.getHeader(true); // skip the header (can't be used with CsvListReader)

            while ((listReader.read()) != null) {

                // use different processors depending on the number of columns
                final CellProcessor[] processors;
                if (listReader.length() == noBirthDateProcessors.length) {
                    processors = noBirthDateProcessors;
                } else {
                    processors = allProcessors;
                }

                final List<Object> customerList = listReader.executeProcessors(processors);
                System.out.println(String.format("lineNo=%s, rowNo=%s, columns=%s, customerList=%s",
                        listReader.getLineNumber(), listReader.getRowNumber(), customerList.size(), customerList));
            }

        } finally {
            if (listReader != null) {
                listReader.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        readWithCsvBeanReader();
//        System.out.println("-----------------");
        readWithCsvListReader();
//        System.out.println("-----------------");
//        readWithCsvMapReader();
//        System.out.println("-----------------");
//        readVariableColumnsWithCsvListReader();
    }
}
