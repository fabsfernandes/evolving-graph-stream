
package br.ufu.lsi.gephi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static BufferedWriter openOutputFile( String outputFile ) throws Exception {
        File file = new File( outputFile );
        FileWriter fw = new FileWriter( file.getAbsoluteFile() );
        BufferedWriter bw = new BufferedWriter( fw );
        return bw;
    }

    public static BufferedWriter openOutputFile( File outputFile ) throws Exception {
        FileWriter fw = new FileWriter( outputFile.getAbsoluteFile() );
        BufferedWriter bw = new BufferedWriter( fw );
        return bw;
    }

    public static BufferedReader openInputFile( String inputFile ) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader( inputFile ) );
        return br;
    }

    public static BufferedReader openInputFile( File inputFile ) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader( inputFile ) );
        return br;
    }

    public static void serializeObject( Object object, String file ) {
        

        try {
            File f = new File( file );

            FileOutputStream out = new FileOutputStream( f );

            ObjectOutputStream stream = new ObjectOutputStream( out );

            stream.writeObject( object );

            stream.close();
            out.close();

        } catch ( Exception e ) {

            e.printStackTrace();
        }
    }
    
    public static void serializeObject( Object object, String file, String dir ) {
        serializeObject( object, dir + "/" + file );
    }

    public static Object deserializeObject( String file, Class clazz ) {
        try {
            File f = new File( file );

            FileInputStream out = new FileInputStream( f );

            ObjectInputStream stream = new ObjectInputStream( out );

            Object obj = stream.readObject();

            stream.close();
            out.close();

            return obj;

        } catch ( Exception e ) {

            e.printStackTrace();
            return null;
        }
    }
    
    public static Object deserializeObject( String file, Class clazz, String dir ) {
        return deserializeObject( dir + "/" + file, clazz );
    }
    
    
    public static DirectoryStream<Path> listSourceFiles( String directory ) throws IOException {
        
        Path dir = FileSystems.getDefault().getPath( directory );
        
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*");
        
            return stream;
    }
    
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos); 
            oos.writeObject(obj);
            oos.flush(); 
            oos.close(); 
            bos.close();
            bytes = bos.toByteArray ();
        }
        catch (IOException ex) {
        //TODO: Handle the exception
        }
      return bytes;
    }
    
    public static Object fromByteArray(byte[] data) {
        
        ObjectInputStream is = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch( IOException e ) {
            e.printStackTrace();
            return null;
        } catch( ClassNotFoundException e )  {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
