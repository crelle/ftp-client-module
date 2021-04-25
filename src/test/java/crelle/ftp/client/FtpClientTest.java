package crelle.ftp.client;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @author:crelle
 * @className:FtpClientTest
 * @version:1.0.0
 * @date:2021/4/25
 * @description:XX
 **/
public class FtpClientTest {

    @Test
    public void test1() throws IOException {
        FtpClient ftpClient = new FtpClient("192.168.74.4",21,"ftpadmin","123456");
        ftpClient.open();
      Collection<String> stringCollection =  ftpClient.listFiles("fes/picture/");
      System.out.println(stringCollection);

    }

    @Test
    public void test2() throws IOException, URISyntaxException {
        FtpClient ftpClient = new FtpClient("192.168.74.4",21,"ftpadmin","123456");
        ftpClient.open();
        File file = new File(getClass().getClassLoader().getResource("baz.txt").toURI());
        ftpClient.putFileToPath(file, "fes/picture/buz.txt");

    }

    @Test
    public void test3() throws IOException, URISyntaxException {
        FtpClient ftpClient = new FtpClient("192.168.74.4",21,"ftpadmin","123456");
        ftpClient.open();
        ftpClient.deleteFile("fes/picture/buz.txt");

    }

    @Test
    public void test4() throws IOException, URISyntaxException {
        FtpClient ftpClient = new FtpClient("192.168.74.4",21,"ftpadmin","123456");
        ftpClient.open();
        ftpClient.downloadFile("fes/picture/buz.txt", "downloaded_buz.txt");

    }
}
