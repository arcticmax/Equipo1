package audio;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


class Server
{
 
       private ServerSocket servidor = null;
 
       public Server( ) throws IOException
       {
         // creamos el servidor
          servidor = new ServerSocket( 1777 );

        
          JOptionPane.showMessageDialog(null,"Esperando el archivo :) " );
       }
 
       public void iniciarServidor()
       {
          while( true )
          {
 
            try
            {
               // Creamos el socket que atendera el servidor
               Socket cliente = servidor.accept(); 
 
               // datos de entrada 
               DataInputStream dis = new DataInputStream( cliente.getInputStream() );
        
               // nombre del archivo que nos envian
               String nombreArchivo = dis.readUTF().toString(); 
 
               // tamano del archivo
               int tam = dis.readInt(); 
 
            
               JOptionPane.showMessageDialog(null, "Recibiendo archivo "+nombreArchivo);
               //donde se guardara el archivo recibido
               FileOutputStream fos = new FileOutputStream( "C:\\tarea\\recibir\\"+nombreArchivo );
               BufferedOutputStream bout = new BufferedOutputStream( fos );
               BufferedInputStream bin = new BufferedInputStream( cliente.getInputStream() );
 
               // se hace arreglo para leer los bytes del archivo
               byte[] buffer = new byte[ tam ];
 
               // lee byte por byte el archivo
               for( int i = 0; i < buffer.length; i++ )
               {
                  buffer[ i ] = ( byte )bin.read( ); 
               }
 
               // archivo escrito en donde indicamos
               bout.write( buffer ); 
 
               bout.flush(); 
               bin.close();
               bout.close(); 
               cliente.close();
 
             
               JOptionPane.showMessageDialog(null, "El archivo se recibio correctamente :) "+nombreArchivo);
           }
           catch( Exception e )
           {
           
              JOptionPane.showMessageDialog(null, "Recibir: "+e.toString());
           }
         } 
       }
       
    
       public static void main( String args[] ) throws IOException
       {
           new Server().iniciarServidor(); 
       }
}