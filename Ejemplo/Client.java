package audio;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
class Client
{
         
         private String archivo = "";
         
         public Client( String nombreArchivo )
         {
              this.archivo = nombreArchivo;
         }
         
         public void enviar( )
         {
         
          try
          {
         
           
            InetAddress direccion = InetAddress.getByName( "127.0.0.1" );
         
            
            Socket socket = new Socket( direccion, 1777 );
            socket.setSoTimeout( 10000 );
            socket.setKeepAlive( true );
         
            
            File file = new File( this.archivo );
         
            // 
            int tamañoArchivo = ( int )file.length();
         
            
            DataOutputStream dos = new DataOutputStream( socket.getOutputStream() );
         
           // System.out.println( "Enviando Archivo: "+archivo.getName() );
            JOptionPane.showMessageDialog(null,  "Enviando Archivo: "+file.getName());
            // Enviamos el nombre del archivo 
            dos.writeUTF( file.getName() );
         
            // Enviamos el tamaño del archivo
            dos.writeInt( tamañoArchivo );
         
            // Creamos flujo de entrada para realizar la lectura del archivo en bytes
            FileInputStream fis = new FileInputStream( this.archivo );
            BufferedInputStream bis = new BufferedInputStream( fis );
         
            // envyar los bytes del archivo 
            BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream()          );
         
            // tamaño del archivo
            byte[] buffer = new byte[ tamañoArchivo ];
         
            
            bis.read( buffer ); 
         
           
            for( int i = 0; i < buffer.length; i++ )
            {
                bos.write( buffer[ i ] ); 
            } 
         
           
             JOptionPane.showMessageDialog(null,  "El archivo ya fue enviado: "+file.getName());
           
            bis.close();
            bos.close();
            socket.close(); 
          }
          catch( Exception e )
          {
          
               JOptionPane.showMessageDialog(null,   e.toString());
          }
         
         }
       
         public static void main( String args[] )
         {
            Client exe = new Client( "C:\\tarea\\coldplay.mp3" );
            exe.enviar();
         }
         
}