package com.zhaoliang.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * commons-cli 测试。
 * Created by zhaoliang on 2016/4/17.
 */
public class Main {


    public static void main(String[] args) {
//		String[] arg = { "-h", "-c", "config.xml" };
        testOptions(args);
    }

    private static void testOptions(String[] args) {
        Options options = new Options();

        // 参数false表示是否有参数。
        Option helpOptions = new Option("h", "help", false, "Print help");

        // false表示这个参数是否是必须的。
        helpOptions.setRequired(false);
        options.addOption(helpOptions);

        Option configFileOptions = new Option("c", "configFile", true,
                                              "Name server config properties file");
        configFileOptions.setRequired(false);
        options.addOption(configFileOptions);

        Option printConfigItemOptions = new Option("p", "printConfigItem",
                                                   false, "Print all config item");
        printConfigItemOptions.setRequired(false);
        options.addOption(printConfigItemOptions);

        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine;
        CommandLineParser parser = new DefaultParser();
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                // 打印使用帮助
                hf.printHelp("testApp", options, true);
            }

            // 打印opts的名称和值
            System.out.println("--------------------------------------");
            Option[] opts = commandLine.getOptions();
            if (opts != null) {
                for (Option opt1 : opts) {
                    String name = opt1.getLongOpt();
                    String value = commandLine.getOptionValue(name);
                    System.out.println(name + "=>" + value);
                }
            }
        } catch (ParseException e) {
            hf.printHelp("testApp", options, true);
        }
    }

}
