package objects;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class Led {
    public void connect( String portName ) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier( portName );

        if( portIdentifier.isCurrentlyOwned() ) {
            System.out.println( "Error: Port is currently in use" );
        } else {
            int timeout = 10000;
            CommPort commPort = portIdentifier.open( this.getClass().getName(), timeout );

            if( commPort instanceof SerialPort) {
                SerialPort serialPort = ( SerialPort )commPort;
                serialPort.setSerialPortParams( 9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE );

                InputStream in = serialPort.getInputStream();
                OutputStream outputStream = serialPort.getOutputStream();
                outputStream.write( 2 );
                Thread.sleep(10);
                outputStream.write( 2 );
                Thread.sleep(10);
                outputStream.write( 2 );
                Thread.sleep(10);
                outputStream.write( 2 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(1000);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(1000);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(1000);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                outputStream.write( 2000000000 );
                Thread.sleep(1000);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );
                Thread.sleep(10);
                outputStream.write( 2000000000 );

                CommPort port = serialPort;
                System.out.println( "Write done" );
                commPort.close();
                //new Thread( new SerialReader( in,port  ) ).start();
            } else {
                System.out.println( "Error: Only serial ports are handled by this example." );
            }
        }
    }
}

