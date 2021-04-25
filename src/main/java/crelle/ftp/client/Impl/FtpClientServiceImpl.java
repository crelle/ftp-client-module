package crelle.ftp.client.Impl;

import crelle.ftp.client.FtpClientService;
import org.apache.commons.net.ftp.FTPFile;

import java.util.Collection;

/**
 * @author:crelle
 * @className:FtpClientServiceImpl
 * @version:1.0.0
 * @date:2021/4/25
 * @description:XX
 **/
public class FtpClientServiceImpl implements FtpClientService {
    @Override
    public Collection<String> query() {
        return null;
    }

    @Override
    public FTPFile[] queryByPath(String path) {
        return new FTPFile[0];
    }

    @Override
    public void upload(String fileName) {

    }

    @Override
    public void download(String fileName) {

    }
}
