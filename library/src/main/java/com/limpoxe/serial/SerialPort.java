package com.limpoxe.serial;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPort {

    private static final String TAG = "SerialPort";

    /*
     * Do not remove or rename the field mFd: it is used by native method close();
     */
    private FileDescriptor mFd;

    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    /**
     * @param path 串口设备地址
     * @param baudrate 波特率
     * @param stopBits 停止位，一般默认1
     * @param dataBits 数据位，一般默认8
     * @param parity 奇偶校验，一般默认0
     * @param flowCtrl 流控，0：不流控；1：硬流控；2：软流控
     * @param flags 打开方式，一般使用0x2(O_RDWR, 读写)，0x100(O_NOCTTY, 不允许进程管理串口), 0x800(O_NDELAY, 非阻塞)
     * @throws SecurityException
     * @throws IOException
     */
    public SerialPort(String path, int baudrate, int stopBits, int dataBits, int parity, int flowCtrl, int flags) throws SecurityException, IOException {
        File device = new File(path);
        /* Check access permission */
        if (!device.canRead() || !device.canWrite()) {
            //throw new SecurityException();
        }

        mFd = open(device.getAbsolutePath(), baudrate, stopBits, dataBits, parity, flowCtrl, flags);
        if (mFd == null) {
            Log.e(TAG, "native open returns null");
            throw new IOException();
        }
        mFileInputStream = new FileInputStream(mFd);
        mFileOutputStream = new FileOutputStream(mFd);
    }

    // Getters and setters
    public InputStream getInputStream() {
        return mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return mFileOutputStream;
    }

    private native static FileDescriptor open(String path, int baudrate, int stopBits, int dataBits, int parity, int flowCtrl, int flags);
    public native static void close();

    static {
        System.loadLibrary("serialport");
    }
}
