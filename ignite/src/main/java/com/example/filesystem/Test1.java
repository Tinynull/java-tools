package com.example.filesystem;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteFileSystem;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.FileSystemConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.igfs.IgfsIpcEndpointConfiguration;
import org.apache.ignite.igfs.IgfsIpcEndpointType;
import org.apache.ignite.igfs.IgfsMode;
import org.apache.ignite.igfs.IgfsPath;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/26.
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {

        fileOperate();

    }

    /**
     * @throws InterruptedException
     */
    private static void fileOperate() throws InterruptedException {
        IgniteConfiguration cfg = new IgniteConfiguration();
        FileSystemConfiguration fileSystemCfg = new FileSystemConfiguration();
        fileSystemCfg.setDefaultMode(IgfsMode.PRIMARY);
        fileSystemCfg.setName("fileSystem1");
        fileSystemCfg.setMetaCacheName("myMetaCache");
        fileSystemCfg.setDataCacheName("myDataCache");

        IgfsIpcEndpointConfiguration endpointCfg = new IgfsIpcEndpointConfiguration();
        endpointCfg.setType(IgfsIpcEndpointType.TCP);
        fileSystemCfg.setIpcEndpointConfiguration(endpointCfg);

        cfg.setFileSystemConfiguration(fileSystemCfg);
        cfg.setClientMode(false);
        Ignite ignite = Ignition.getOrStart(cfg);

        IgniteFileSystem fs = ignite.fileSystem("fileSystem1");

        // Create directory.
        IgfsPath dir = new IgfsPath("E:\\filesystem\\test1");
        fs.mkdirs(dir);
        Thread.sleep(Integer.MAX_VALUE);
        // Create file and write some data to it.
//        IgfsPath file = new IgfsPath(dir, "myFile");
//        try (OutputStream out = fs.create(file, true)) {
//            out.write("hello".getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Read from file.
//        try (InputStream in = fs.open(file)) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            String s = bufferedReader.readLine();
//            System.out.println(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Delete directory.
//        fs.delete(dir, true);
    }


}
