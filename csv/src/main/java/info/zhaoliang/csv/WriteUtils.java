package info.zhaoliang.csv;

import info.zhaoliang.csv.pojo.CustomerBean;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.util.*;

public class WriteUtils {

    /**
     * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. All values
     * are converted to Strings before writing (there's no need to convert them), and null values will be written as
     * empty columns (no need to convert them to "").
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors() {

        return new CellProcessor[]{
                new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), // firstName
                new NotNull(), // lastName
                new FmtDate("dd/MM/yyyy"), // birthDate
                new NotNull(), // mailingAddress
                new Optional(new FmtBool("Y", "N")), // married
                new Optional(), // numberOfKids
                new NotNull(), // favouriteQuote
                new NotNull(), // email
                new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
        };
    }

    /**
     * An example of writing using CsvBeanWriter.
     */
    private static void writeWithCsvBeanWriter() throws Exception {

        // create the customer beans
        final CustomerBean john = new CustomerBean("1", "John", "Dunbar",
                new GregorianCalendar(1945, Calendar.JUNE, 13).getTime(),
                "1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States", null, null,
                "\"May the Force be with you.\" - Star Wars", "jdunbar@gmail.com", 0L);
        final CustomerBean bob = new CustomerBean("2", "Bob", "Down",
                new GregorianCalendar(1919, Calendar.FEBRUARY, 25).getTime(),
                "1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States", true, 0,
                "\"Frankly, my dear, I don't give a damn.\" - Gone With The Wind", "bobdown@hotmail.com", 123456L);
        final List<CustomerBean> customers = Arrays.asList(john, bob);

        ICsvBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvBeanWriter(new FileWriter("writeWithCsvBeanWriter.csv"),
                    CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[]{"customerNo", "firstName", "lastName", "birthDate",
                    "mailingAddress", "married", "numberOfKids", "favouriteQuote", "email", "loyaltyPoints"};
            final CellProcessor[] processors = getProcessors();

            // write the header
            beanWriter.writeHeader(header);

            // write the beans
            for (final CustomerBean customer : customers) {
                beanWriter.write(customer, header, processors);
            }

        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }
    }


    /**
     * An example of reading using CsvListWriter.
     */
    private static void writeWithCsvListWriter() throws Exception {

        // create the customer Lists (CsvListWriter also accepts arrays!)
        final List<Object> john = Arrays.asList(new Object[]{"1", "John", "Dunbar",
                new GregorianCalendar(1945, Calendar.JUNE, 13).getTime(),
                "1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States", null, null,
                "\"May the Force be with you.\" - Star Wars", "jdunbar@gmail.com", 0L});

        final List<Object> bob = Arrays.asList(new Object[]{"2", "Bob", "Down",
                new GregorianCalendar(1919, Calendar.FEBRUARY, 25).getTime(),
                "1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States", true, 0,
                "\"Frankly, my dear, I don't give a damn.\" - Gone With The Wind", "bobdown@hotmail.com", 123456L});

        ICsvListWriter listWriter = null;
        try {
            listWriter = new CsvListWriter(new FileWriter("writeWithCsvListWriter.csv"),
                    CsvPreference.STANDARD_PREFERENCE);

            final CellProcessor[] processors = getProcessors();
            final String[] header = new String[]{"customerNo", "firstName", "lastName", "birthDate",
                    "mailingAddress", "married", "numberOfKids", "favouriteQuote", "email", "loyaltyPoints"};

            // write the header
            listWriter.writeHeader(header);

            // write the customer lists
            listWriter.write(john, processors);
            listWriter.write(bob, processors);

        } finally {
            if (listWriter != null) {
                listWriter.close();
            }
        }
    }



    /**
     * An example of reading using CsvMapWriter.
     */
    private static void writeWithCsvMapWriter() throws Exception {

        final String[] header = new String[] { "customerNo", "firstName", "lastName", "birthDate", "mailingAddress",
                "married", "numberOfKids", "favouriteQuote", "email", "loyaltyPoints" };

        // create the customer Maps (using the header elements for the column keys)
        final Map<String, Object> john = new HashMap<String, Object>();
        john.put(header[0], "1");
        john.put(header[1], "John");
        john.put(header[2], "Dunbar");
        john.put(header[3], new GregorianCalendar(1945, Calendar.JUNE, 13).getTime());
        john.put(header[4], "1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States");
        john.put(header[5], null);
        john.put(header[6], null);
        john.put(header[7], "\"May the Force be with you.\" - Star Wars");
        john.put(header[8], "jdunbar@gmail.com");
        john.put(header[9], 0L);

        final Map<String, Object> bob = new HashMap<String, Object>();
        bob.put(header[0], "2");
        bob.put(header[1], "Bob");
        bob.put(header[2], "Down");
        bob.put(header[3], new GregorianCalendar(1919, Calendar.FEBRUARY, 25).getTime());
        bob.put(header[4], "1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States");
        bob.put(header[5], true);
        bob.put(header[6], 0);
        bob.put(header[7], "\"Frankly, my dear, I don't give a damn.\" - Gone With The Wind");
        bob.put(header[8], "bobdown@hotmail.com");
        bob.put(header[9], 123456L);

        ICsvMapWriter mapWriter = null;
        try {
            mapWriter = new CsvMapWriter(new FileWriter("writeWithCsvMapWriter.csv"),
                    CsvPreference.STANDARD_PREFERENCE);

            final CellProcessor[] processors = getProcessors();

            // write the header
            mapWriter.writeHeader(header);

            // write the customer maps
            mapWriter.write(john, header, processors);
            mapWriter.write(bob, header, processors);

        }
        finally {
            if( mapWriter != null ) {
                mapWriter.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        writeWithCsvBeanWriter();
        System.out.println("-----------------------");
        writeWithCsvListWriter();
        System.out.println("-----------------------");
        writeWithCsvMapWriter();
        System.out.println("-----------------------");
    }

}
